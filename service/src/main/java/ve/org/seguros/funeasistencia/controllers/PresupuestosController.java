package ve.org.seguros.funeasistencia.controllers;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ve.org.seguros.funeasistencia.formularios.PresupuestosFormulario;
import ve.org.seguros.funeasistencia.pojos.Persona;
import ve.org.seguros.funeasistencia.pojos.PeticionConsulta;
import ve.org.seguros.funeasistencia.pojos.PresupuestoServicio;
import ve.org.seguros.funeasistencia.pojos.Presupuestos;
import ve.org.seguros.funeasistencia.pojos.TasaCambio;
import ve.org.seguros.funeasistencia.pojos.TipoContratacionDetalle;
import ve.org.seguros.funeasistencia.pojos.TipoServicio;
import ve.org.seguros.funeasistencia.repositorios.AtaudesRepo;
import ve.org.seguros.funeasistencia.repositorios.CapillaRepo;
import ve.org.seguros.funeasistencia.repositorios.OficinaRepo;
import ve.org.seguros.funeasistencia.repositorios.PersonaRepo;
import ve.org.seguros.funeasistencia.repositorios.PresupuestoServicioRepo;
import ve.org.seguros.funeasistencia.repositorios.PresupuestosRepo;
import ve.org.seguros.funeasistencia.repositorios.TasaCambioRepo;
import ve.org.seguros.funeasistencia.repositorios.TipoContratacionDetalleRepo;
import ve.org.seguros.funeasistencia.repositorios.TipoMonedaRepo;
import ve.org.seguros.funeasistencia.repositorios.TipoPagoRepo;
import ve.org.seguros.funeasistencia.repositorios.TipoServicioRepo;
import ve.org.seguros.funeasistencia.servicios.CustomSQL;
import ve.org.seguros.funeasistencia.servicios.EnviarNotificacionesPush;


@Controller
@RequestMapping("front.funeasistencia")
public class PresupuestosController {
	
	@Autowired
	private PresupuestosFormulario presupuestosFormulario;
	
	@Autowired
	private PersonaRepo personaRepo;
	
	@Autowired
	private TipoContratacionDetalleRepo tipoContratacionDetalleRepo;
	
	@Autowired
	private PresupuestosRepo presupuestosRepo;
	
	@Autowired
	private PresupuestoServicioRepo presupuestoServicioRepo;
	
	@Autowired
	private AtaudesRepo ataudesRepo;
	
	@Autowired
	private CapillaRepo capillaRepo;
	
	@Autowired
	private TipoMonedaRepo tipoMonedaRepo;
	
	@Autowired
	private TipoServicioRepo tipoServicioRepo;
	
	@Autowired
	private TipoPagoRepo tipoPagoRepo;
	
	@Autowired
	private OficinaRepo oficinaRepo;
	
	@Autowired
	private CustomSQL customSQL;
	
	@Autowired
	private EnviarNotificacionesPush enviarNotificacionesPush;
	
	@Autowired
	private TasaCambioRepo tasaCambioRepo;
	
	
	@GetMapping("/presupuestos.indice")
    public String fnIndice(HttpServletRequest request) {
		request.setAttribute("idPojo", "cdpresupuesto");
		request.setAttribute("metodoDt", "fnConfiguracionPresupuesto");
		
		request.setAttribute("linkConsulta", "/front.funeasistencia/presupuestos.consulta");
		request.setAttribute("linkSegundaFase", "/front.funeasistencia/servicios.consulta.segundafase");
		request.setAttribute("funcionConsulta", "fnGenerarConsultaDatatableBasica(1)");
		request.setAttribute("linkSegundaFase", "/front.funeasistencia/servicios.consulta.segundafase");
		
		request.setAttribute("linkSubConsulta", "/front.funeasistencia/presupuestos.consulta.servicios");
		request.setAttribute("metodoSubDt", "fnConfiguracionServicios");
		
		request.setAttribute("codigoSubMenu","25");
		request.setAttribute("codigoMenu","21");
		request.setAttribute("linkAgregar", "/front.funeasistencia/presupuestos.indice.agregar");
		request.setAttribute("tituloTarjeta", "Menú Principal de los Presupuestos");
		request.setAttribute("tituloBadge1", "Procesos Principales");
		request.setAttribute("tituloBadge2", "Presupuesto");
		request.setAttribute("nombreFormulario", "formPresupuestos");
		request.setAttribute("formularios", presupuestosFormulario.fnConsulta());
		request.setAttribute("scripts",presupuestosFormulario.fnRetornarScripts());
        return "procesos/presupuestos/index";
    }
	@GetMapping("/presupuestos.indice.agregar")
    public String fnIndiceAgregar(HttpServletRequest request) {	
		String vURL="";
		request.setAttribute("codigoSubMenu","25");
		request.setAttribute("codigoMenu","21");
		request.setAttribute("linkVolver", "/front.funeasistencia/presupuestos.indice");
		request.setAttribute("metodoDt", "fnConfiguracionGuardiasIntegrantes");
		request.setAttribute("linkAgregar", "/front.funeasistencia/presupuestos.guardar");
		request.setAttribute("tituloTarjeta", "Registrar Presupuestos");
		request.setAttribute("tituloBadge1", "Procesos Principales");
		request.setAttribute("tituloBadge2", "Presupuestos / Registro");
		request.setAttribute("nombreFormulario", "formPresupuestos");
		request.setAttribute("nombreFormulario2", "formPresupuestos2");
		if(request.getSession().getAttribute("cdoficina")!=null) {
			String vCdOficina=request.getSession().getAttribute("cdoficina").toString();
			String vCdUsuario=request.getSession().getAttribute("cdpersona").toString();
			List<Object> vLista=customSQL.fnValidarQueExistaGuardiaPorOficina(vCdOficina,vCdUsuario);
			Object [] objLista=(Object[]) vLista.get(0);
			if(objLista[0].toString().equalsIgnoreCase("0")) {
				vURL="redirect:/front.funeasistencia/guardias.indice.agregar";
			}else {
				request.setAttribute("formularios", presupuestosFormulario.fnRegistro(
						request.getSession().getAttribute("cdoficina"),
						request.getSession().getAttribute("cdusuario")
						)
						);
				request.setAttribute("formularios2", presupuestosFormulario.fnRegistro2(
						request.getSession().getAttribute("cdoficina"),
						request.getSession().getAttribute("cdusuario")
						)
						);
				vURL="procesos/presupuestos/agregar";
				
			}
		}else {
			vURL="procesos/presupuestos/agregar";
		}
		
		
		request.setAttribute("scripts",presupuestosFormulario.fnRetornarScriptsRegistro());
        return vURL;
    }
	
	@GetMapping(value="presupuestos.verficarClienteTitular/{pCedulaCliente}")
	public ResponseEntity<List<Persona>> fnVerficarClienteTitular(
			@PathVariable(value = "pCedulaCliente") String pCedulaCliente
			){
		List<Persona> vRetorno=new ArrayList<>();
		if(pCedulaCliente!=null) {
			vRetorno=personaRepo.fnBuscarPorCedula(pCedulaCliente);
		}
		return ResponseEntity.ok(vRetorno);
	}
	@GetMapping(value="presupuestos.tasacambio/{pCdMoneda}")
	public ResponseEntity<List<TasaCambio>> fnRetornaTasaDia(
			@PathVariable(value = "pCdMoneda") String pCdMoneda
			){
		List<TasaCambio> vRetorno=new ArrayList<>();
		if(pCdMoneda!=null) {
			vRetorno=tasaCambioRepo.fnBuscarPorCriterio(Long.valueOf(pCdMoneda));
			if(vRetorno.size()<1) {
				vRetorno=tasaCambioRepo.fnBuscarUltimaFecha(Long.valueOf(pCdMoneda));
			}
		}
		return ResponseEntity.ok(vRetorno);
	}
	
	@GetMapping(value="presupuestos.verficarClienteFallecido/{pCedulaCliente}")
	public ResponseEntity<List<Presupuestos>> fnVerificaClienteFallecido(
			@PathVariable(value = "pCedulaCliente") String pCedulaCliente
			){
		List<Presupuestos> vRetorno=new ArrayList<>();
		if(pCedulaCliente!=null) {
			vRetorno=presupuestosRepo.fnBuscarPorClienteFallecido(pCedulaCliente);
		}
		return ResponseEntity.ok(vRetorno);
	}
	@GetMapping(value="presupuestos.buscarContratacion/{pCdTipoContratacion}")
	public ResponseEntity<List<TipoContratacionDetalle>> fnBuscarContratacion(
			@PathVariable(value = "pCdTipoContratacion") String pCdTipoContratacion
			){
		List<TipoContratacionDetalle> vRetorno=new ArrayList<>();
		if(pCdTipoContratacion!=null) {
			vRetorno=tipoContratacionDetalleRepo.fnConsultaGlobal(pCdTipoContratacion);
		}
		return ResponseEntity.ok(vRetorno);
	}
	
	@PostMapping(value="presupuestos.insercion")
	public ResponseEntity<Presupuestos> fnInsercion(@RequestBody Presupuestos pPresupuestos,HttpServletRequest request ){
		Presupuestos vListaRetorno=null;
		List<TasaCambio> vTasaCambio=new ArrayList<>();
		if(pPresupuestos!=null) {
			pPresupuestos.setFepresupuesto(new Date());
			if(request.getSession().getAttribute("cdpersona")!=null) {
				pPresupuestos.setCdpersonaelaborador(Long.valueOf(request.getSession().getAttribute("cdpersona").toString()));
				pPresupuestos.setCdusuarioelaborador(request.getSession().getAttribute("cdusuario").toString());
			}
			vTasaCambio=tasaCambioRepo.fnBuscarPorCriterio(pPresupuestos.getCdmonedapresupuesto());
			if(vTasaCambio.size()>0) {
				pPresupuestos.setMttasa(vTasaCambio.get(0).getMttasa());
			}else {
				vTasaCambio=tasaCambioRepo.fnBuscarUltimaFecha(pPresupuestos.getCdmonedapresupuesto());
				pPresupuestos.setMttasa(vTasaCambio.get(0).getMttasa());
			}
			pPresupuestos.setStpresupuesto(1L);
			pPresupuestos.setTxcausaanulacion("N/A");
			vListaRetorno=presupuestosRepo.save(pPresupuestos);
			List<PresupuestoServicio> vPresupuestosServicios= pPresupuestos.getPresupuestoservicio();
			for(int a=0; a<vPresupuestosServicios.size();a++) {
				
				vPresupuestosServicios.get(a).setCdpresupuesto(vListaRetorno.getCdpresupuesto());
				presupuestoServicioRepo.save(vPresupuestosServicios.get(a));
			}
		}
		enviarNotificacionesPush.fnNotificacionPushPresupuesto(vListaRetorno);
		return ResponseEntity.ok(vListaRetorno);
	}
	
	@PostMapping("/presupuestos.consulta")
	public ResponseEntity<List<Presupuestos>> fnBuscarGuardias(@RequestBody PeticionConsulta pPeticion,HttpServletRequest request ){
		List<Presupuestos> vListaRetorno=null;
		if(request.getSession().getAttribute("cdoficina")!=null) {
			vListaRetorno=presupuestosRepo.fnBuscarPorCriterio(pPeticion.getParam1(),
					pPeticion.getParam2(),
					request.getSession().getAttribute("cdoficina").toString()
					);
		
			if(vListaRetorno.size()>0) {
				for(int a=0;a<vListaRetorno.size();a++) {
					vListaRetorno.get(a).setTxataud(
							ataudesRepo.fnConsultaPorCodigo(vListaRetorno.get(a).getCdataud()).getTxataud()
							);
					vListaRetorno.get(a).setTxcapilla(
							capillaRepo.fnConsultaPorCodigo(vListaRetorno.get(a).getCdcapilla()).getTxcapilla()
							);
					vListaRetorno.get(a).setTxnombretitular(
							personaRepo.fnConsultaPorCodigo(
								vListaRetorno.get(a).getCdpersonatitular()
								).getTxnombres()
							);
					vListaRetorno.get(a).setTxpersonaencargada
					(
							personaRepo.fnConsultaPorCodigo(
								vListaRetorno.get(a).getCdpersonaelaborador()
								).getTxnombres()
					);
					
					
					vListaRetorno.get(a).setTxcontrataciondetalle
					(
							tipoContratacionDetalleRepo.fnConsultaPorCodigo(
								vListaRetorno.get(a).getCdcontrataciondetalle().toString()
								).getTxcontrataciondetalle()
					);
					String vStatus="";
					if(vListaRetorno.get(a).getStpresupuesto()==1L) {
						vStatus="Generado";
					}
					if(vListaRetorno.get(a).getStpresupuesto()==2L) {
						vStatus="Cerrado";
					}
					if(vListaRetorno.get(a).getStpresupuesto()==3L) {
						vStatus="Rechazado";
					}
					vListaRetorno.get(a).setTxestatus(vStatus);
					
				}
			}
		}
		return ResponseEntity.ok(vListaRetorno);
	}
	
	
	@PostMapping(value="presupuestos.consulta.servicios")
	public ResponseEntity<List<PresupuestoServicio>> fnBuscarIntegrantesGuardia(@RequestBody PeticionConsulta pPresupuesto ){
		List<PresupuestoServicio> vListaRetorno=null;
		if(pPresupuesto!=null) {
			vListaRetorno=presupuestoServicioRepo.fnConsultaPorCodigo(pPresupuesto.getParam1());
			if(vListaRetorno.size()>0) {
				for(int a=0;a<vListaRetorno.size();a++) {
					vListaRetorno.get(a).setTxservicio(
							tipoServicioRepo.fnConsultaPorCodigo(
									vListaRetorno.get(a).getCdtiposervicio()
							).getTxtiposervicio()	
					);
					
					vListaRetorno.get(a).setTxmoneda
					(
							tipoMonedaRepo.fnConsultaPorCodigo(
									vListaRetorno.get(a).getCdmoneda()
							).getTxmoneda()	
					);
					
					vListaRetorno.get(a).setTxtipopago
					(
							tipoPagoRepo.fnConsultaConsulta(
								vListaRetorno.get(a).getCdtipopago()
							).getTxtipopago()	
					);
					vListaRetorno.get(a).setCdmonedapresupuesto
					(
							presupuestosRepo.fnBuscarPorCodigo(
								Long.valueOf(pPresupuesto.getParam1())
							).getCdmonedapresupuesto()	
					);

				}
			}
		}
		return ResponseEntity.ok(vListaRetorno);
	}
	@PostMapping(value="presupuestos.generarServicio")
	public ResponseEntity<Presupuestos> fnGenerarServicio(@RequestBody Presupuestos pPresupuesto ){
		if(pPresupuesto!=null) {
			presupuestosRepo.fnModificarPresupuesto(pPresupuesto.getStpresupuesto(),pPresupuesto.getTxcausaanulacion(),
					pPresupuesto.getCdpresupuesto());
			
		}
		return ResponseEntity.ok(pPresupuesto);
	}
	
	@GetMapping("/servicios.indice")
    public String fnIndiceServicio(HttpServletRequest request) {
		request.setAttribute("idPojo", "cdpresupuesto");
		request.setAttribute("metodoDt", "fnConfiguracionPresupuesto");
		
		request.setAttribute("linkConsulta", "/front.funeasistencia/servicios.consulta");
		request.setAttribute("funcionConsulta", "fnGenerarConsultaDatatableBasica(1)");
		request.setAttribute("linkSegundaFase", "/front.funeasistencia/servicios.consulta.segundafase");
		request.setAttribute("linkSubConsulta", "/front.funeasistencia/presupuestos.consulta.servicios");
		request.setAttribute("metodoSubDt", "fnConfiguracionServicios");
		
		request.setAttribute("codigoSubMenu","26");
		request.setAttribute("codigoMenu","21");
		request.setAttribute("tituloTarjeta", "Menú Principal de los Servicios");
		request.setAttribute("tituloBadge1", "Procesos Principales");
		request.setAttribute("tituloBadge2", "Servicios");
		request.setAttribute("nombreFormulario", "formPresupuestos");
		request.setAttribute("formularios", presupuestosFormulario.fnConsulta());
		request.setAttribute("scripts",presupuestosFormulario.fnRetornarScripts());
        return "procesos/presupuestos/index";
    }
	@PostMapping("/servicios.consulta")
	public ResponseEntity<List<Presupuestos>> fnBuscarServicios(@RequestBody PeticionConsulta pPeticion,HttpServletRequest request ){
		List<Presupuestos> vListaRetorno=null;
		if(request.getSession().getAttribute("cdoficina")!=null) {
			vListaRetorno=presupuestosRepo.fnBuscarServiciosPorCriterio(pPeticion.getParam1(),
					pPeticion.getParam2(),
					request.getSession().getAttribute("cdoficina").toString()
					);
		
			if(vListaRetorno.size()>0) {
				for(int a=0;a<vListaRetorno.size();a++) {
					vListaRetorno.get(a).setTxataud(
							ataudesRepo.fnConsultaPorCodigo(vListaRetorno.get(a).getCdataud()).getTxataud()
							);
					vListaRetorno.get(a).setTxcapilla(
							capillaRepo.fnConsultaPorCodigo(vListaRetorno.get(a).getCdcapilla()).getTxcapilla()
							);
					vListaRetorno.get(a).setTxnombretitular(
							personaRepo.fnConsultaPorCodigo(
								vListaRetorno.get(a).getCdpersonatitular()
								).getTxnombres()
							);
					vListaRetorno.get(a).setTxpersonaencargada
					(
							personaRepo.fnConsultaPorCodigo(
								vListaRetorno.get(a).getCdpersonaelaborador()
								).getTxnombres()
					);
					
					
					vListaRetorno.get(a).setTxcontrataciondetalle
					(
							tipoContratacionDetalleRepo.fnConsultaPorCodigo(
								vListaRetorno.get(a).getCdcontrataciondetalle().toString()
								).getTxcontrataciondetalle()
					);
					String vStatus="";
					if(vListaRetorno.get(a).getStpresupuesto()==1L) {
						vStatus="Generado";
					}
					if(vListaRetorno.get(a).getStpresupuesto()==2L) {
						vStatus="En Curso";
					}
					if(vListaRetorno.get(a).getStpresupuesto()==3L) {
						vStatus="Rechazado";
					}
					if(vListaRetorno.get(a).getStpresupuesto()==4L) {
						vStatus="Cerrado";
					}
					vListaRetorno.get(a).setTxestatus(vStatus);

				}
			}
		}
		return ResponseEntity.ok(vListaRetorno);
	}
	@PostMapping("/servicios.consulta.segundafase")
	public ResponseEntity<Presupuestos> fnBuscarServiciosSegundaFase(@RequestBody PeticionConsulta pPeticion,HttpServletRequest request ){
		Presupuestos vListaRetorno=null;
		if(request.getSession().getAttribute("cdoficina")!=null) {
			vListaRetorno=presupuestosRepo.fnBuscarPorCodigo(
					Long.valueOf(pPeticion.getParam1())
					);
					vListaRetorno.setTxataud(
							ataudesRepo.fnConsultaPorCodigo(vListaRetorno.getCdataud()).getTxataud()
							);
					vListaRetorno.setTxcapilla(
							capillaRepo.fnConsultaPorCodigo(vListaRetorno.getCdcapilla()).getTxcapilla()
							);
					vListaRetorno.setTxnombretitular(
							personaRepo.fnConsultaPorCodigo(
								vListaRetorno.getCdpersonatitular()
								).getTxnombres()
								
							);
					vListaRetorno.setTxdocumentotitular(
							personaRepo.fnConsultaPorCodigo(
									vListaRetorno.getCdpersonatitular()
							).getTpdocumento()+"-"+
							personaRepo.fnConsultaPorCodigo(
									vListaRetorno.getCdpersonatitular()
							).getTxdocumento()
					);
					
					
					SimpleDateFormat vFormatoFecha=new SimpleDateFormat("dd/MM/yyyy hh:mm");
					vListaRetorno.setTxfepresupuesto(
							vFormatoFecha.format(vListaRetorno.getFepresupuesto())
							);

					vListaRetorno.setTxoficina(
							oficinaRepo.fnConsultaPorCodigo(
									vListaRetorno.getCdoficina()
									).getTxcompania()
							);
					
					vListaRetorno.setTxtelefonotitular(
							personaRepo.fnConsultaPorCodigo(
									vListaRetorno.getCdpersonatitular()
							).getTxtelefono1()
							);
					
					vListaRetorno.setTxpersonaencargada
					(
							personaRepo.fnConsultaPorCodigo(
								vListaRetorno.getCdpersonaelaborador()
								).getTxnombres()+"/ "+
								personaRepo.fnConsultaPorCodigo(
										vListaRetorno.getCdpersonaelaborador()
								).getTpdocumento()+"-"+
								personaRepo.fnConsultaPorCodigo(
										vListaRetorno.getCdpersonaelaborador()
								).getTxdocumento()
					);
					
					vListaRetorno.setTxcontrataciondetalle
					(
							tipoContratacionDetalleRepo.fnConsultaPorCodigo(
								vListaRetorno.getCdcontrataciondetalle().toString()
								).getTxcontrataciondetalle()
					);
					String vStatus="";
					if(vListaRetorno.getStpresupuesto()==1L) {
						vStatus="Generado";
					}
					if(vListaRetorno.getStpresupuesto()==2L) {
						vStatus="En Curso";
					}
					if(vListaRetorno.getStpresupuesto()==3L) {
						vStatus="Rechazado";
					}
					if(vListaRetorno.getStpresupuesto()==4L) {
						vStatus="Cerrado";
					}
					vListaRetorno.setTxestatus(vStatus);
					
					
				}
			
		
		return ResponseEntity.ok(vListaRetorno);
	}
	@PostMapping(value="presupuestos.reversarServicio")
	public ResponseEntity<Presupuestos> fnReversarSevicio(@RequestBody Presupuestos pPresupuesto ){
		Presupuestos vListaRetorno=null;
		if(pPresupuesto!=null) {
			presupuestosRepo.fnModificarPresupuesto(pPresupuesto.getStpresupuesto(),pPresupuesto.getTxcausaanulacion(),
					pPresupuesto.getCdpresupuesto());
		}
		return ResponseEntity.ok(vListaRetorno);
	}
	@GetMapping("/presupuestos.consultar.moneda/{pcdpresupuesto}")
    public ResponseEntity<Presupuestos> fnConsultarMonedaPresupuesto(HttpServletRequest request,@PathVariable(value = "pcdpresupuesto") String pcdpresupuesto) {
		Presupuestos vBusquedaPresupuesto=presupuestosRepo.fnBuscarPorCodigo(Long.valueOf(pcdpresupuesto));
        return ResponseEntity.ok(vBusquedaPresupuesto);
    }
	
	@GetMapping("/presupuestos.indice.actualizar/{pcdpresupuesto}")
    public String fnIndiceActualizar(HttpServletRequest request,@PathVariable(value = "pcdpresupuesto") String pcdpresupuesto) {
		List<PresupuestoServicio> vValores=presupuestoServicioRepo.fnConsultaPorCodigo(pcdpresupuesto);
		Presupuestos vBusquedaPresupuesto=presupuestosRepo.fnBuscarPorCodigo(Long.valueOf(pcdpresupuesto));
		//<div style="text-align:left;"><span class="badge bg-dark col-md-12">Presupuestos</span></div>
		request.setAttribute("idPojo", "cdpresupuesto");
		request.setAttribute("metodoDt", "fnConfiguracionPresupuesto");
		request.setAttribute("codigoSubMenu","25");
		request.setAttribute("codigoMenu","21");
		request.setAttribute("linkAgregar", "/front.funeasistencia/presupuestos.indice.agregar");
		request.setAttribute("tituloTarjeta", "Actualizar Presupuesto");
		request.setAttribute("tituloBadge1", "Procesos Principales");
		request.setAttribute("tituloBadge2", "Presupuesto");
		request.setAttribute("nombreFormulario", "formPresupuestos");
		request.setAttribute("nombreFormulario2", "formPresupuestos2");
		request.setAttribute("nombreFormulario3", "formPresupuestos3");
		request.setAttribute("tasaPresupuesto", vBusquedaPresupuesto.getMttasa());
		request.setAttribute("CdMoneda", vBusquedaPresupuesto.getCdmonedapresupuesto());
		request.setAttribute("pCurrencySymbol", (vBusquedaPresupuesto.getCdmonedapresupuesto()==3)?"€" : "$");
		request.setAttribute("presupuestoServicios", vValores);
		if(request.getSession().getAttribute("cdoficina")!=null) {
			
			request.setAttribute("formularios", presupuestosFormulario.fnUpdate(
					request.getSession().getAttribute("cdoficina"),pcdpresupuesto
					));
			request.setAttribute("servicios", tipoServicioRepo.fnConsultaServicioOficina(
					Long.valueOf(request.getSession().getAttribute("cdoficina").toString()))
			);
			request.setAttribute("moneda", tipoMonedaRepo.fnConsultaGlobal()
			);
			if(vValores.size()>0) {
				request.setAttribute("tipopago", tipoPagoRepo.fnConsultaPorMoneda(vValores.get(0).getCdmoneda().toString())
						);
			}

			request.setAttribute("formularios2", presupuestosFormulario.fnRegistro2(
					request.getSession().getAttribute("cdoficina"),pcdpresupuesto
					));
		}
		
		request.setAttribute("scripts",presupuestosFormulario.fnScriptsUpdate());
        return "procesos/presupuestos/update";
    }
	@PostMapping(value="presupuestos.actualizar")
	public ResponseEntity<Presupuestos> fnActualizar(@RequestBody Presupuestos pPresupuestos,HttpServletRequest request ){
		
		Presupuestos vBusquedaPresupuesto=presupuestosRepo.fnBuscarPorCodigo(pPresupuestos.getCdpresupuesto());
		Presupuestos vListaRetorno=null;
		if(pPresupuestos!=null) {
			vBusquedaPresupuesto.setCdataud(pPresupuestos.getCdataud());
			vBusquedaPresupuesto.setCdcapilla(pPresupuestos.getCdcapilla());
			vListaRetorno=presupuestosRepo.save(vBusquedaPresupuesto);
			List<PresupuestoServicio> vPresupuestosServicios= pPresupuestos.getPresupuestoservicio();
			for(int a=0; a<vPresupuestosServicios.size();a++) {
				
				vPresupuestosServicios.get(a).setCdpresupuesto(pPresupuestos.getCdpresupuesto());
				presupuestoServicioRepo.save(vPresupuestosServicios.get(a));
			}
		}
		enviarNotificacionesPush.fnNotificacionPushPresupuesto(vListaRetorno);
		return ResponseEntity.ok(vListaRetorno);
	}
	@PostMapping(value="presupuestosServicios.eliminar")
	public ResponseEntity<PresupuestoServicio> fnEliminar(@RequestBody PresupuestoServicio pPersonas ){
		presupuestoServicioRepo.delete(pPersonas);
		return ResponseEntity.ok(pPersonas);
	}
	
	@GetMapping("/presupuestos.consultar.aplicaparaservicio/{pCdTipoServicio}")
    public ResponseEntity<List<TipoServicio>> fnConsultarMonedaPresupuesto(@PathVariable(value = "pCdTipoServicio") String pCdTipoServicio) {
		List<TipoServicio> vBusquedaPresupuesto=tipoServicioRepo.fnConsultaUsaCapillaAtaud(pCdTipoServicio);
        return ResponseEntity.ok(vBusquedaPresupuesto);
    }
	
	
	
}
