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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ve.org.seguros.funeasistencia.formularios.GuardiasFormulario;
import ve.org.seguros.funeasistencia.pojos.Guardia;
import ve.org.seguros.funeasistencia.pojos.GuardiaIntegrantes;
import ve.org.seguros.funeasistencia.pojos.Persona;
import ve.org.seguros.funeasistencia.pojos.PeticionConsulta;
import ve.org.seguros.funeasistencia.repositorios.GuardiasIntegrantesRepo;
import ve.org.seguros.funeasistencia.repositorios.GuardiasRepo;
import ve.org.seguros.funeasistencia.repositorios.OficinaRepo;
import ve.org.seguros.funeasistencia.repositorios.PersonaRepo;
import ve.org.seguros.funeasistencia.repositorios.TipoPersonaRepo;
import ve.org.seguros.funeasistencia.servicios.CustomSQL;
import ve.org.seguros.funeasistencia.servicios.EnviarNotificacionesPush;


@Controller
@RequestMapping("front.funeasistencia")

public class GuardiasController {
	
	@Autowired
	private GuardiasFormulario guardiasFormulario;
	
	@Autowired
	private GuardiasRepo guardiasRepo;
	
	@Autowired
	private PersonaRepo personaRepo;
	
	@Autowired
	private OficinaRepo oficinaRepo;
	
	@Autowired
	private TipoPersonaRepo tipoPersonaRepo;
	
	@Autowired
	private GuardiasIntegrantesRepo guardiasIntegrantesRepo;

	@Autowired
	private CustomSQL customSQL;
	
	@Autowired
	private EnviarNotificacionesPush enviarNotificacionesPush;
	
	
	@GetMapping("/guardias.indice")
    public String fnIndice(HttpServletRequest request) {
		request.setAttribute("idPojo", "cdguardia");
		request.setAttribute("metodoDt", "fnConfiguracionGuardias");
		
		request.setAttribute("linkConsulta", "/front.funeasistencia/guardias.consulta");
		request.setAttribute("funcionConsulta", "fnGenerarConsultaConSweetAlert(1)");
		
		request.setAttribute("linkSubConsulta", "/front.funeasistencia/guardias.consulta.integrantes");
		request.setAttribute("metodoSubDt", "fnConfiguracionGuardiaIntegrantes");
		
		request.setAttribute("codigoSubMenu","23");
		request.setAttribute("codigoMenu","21");
		request.setAttribute("linkAgregar", "/front.funeasistencia/guardias.indice.agregar");
		request.setAttribute("tituloTarjeta", "Menú Principal de las Guardias");
		request.setAttribute("tituloBadge1", "Procesos Principales");
		request.setAttribute("tituloBadge2", "Guardias");
		request.setAttribute("nombreFormulario", "formGuardia");
		request.setAttribute("formularios", guardiasFormulario.fnConsulta());
		request.setAttribute("scripts",guardiasFormulario.fnRetornarScripts());
        return "procesos/guardias/index";
    }
	@GetMapping("/guardias.indice.agregar")
    public String fnIndiceAgregar(HttpServletRequest request) {
		request.setAttribute("funcionAgregar", "fnAgregarObjetos()");
		request.setAttribute("funcionRegistrar", "fnAgregar()");
		
		request.setAttribute("codigoSubMenu","23");
		request.setAttribute("codigoMenu","21");
		
		request.setAttribute("linkVolver", "/front.funeasistencia/guardias.indice");
		request.setAttribute("metodoDt", "fnConfiguracionGuardiasDT1");
		request.setAttribute("tituloTarjeta", "Registro de las Guardias");
		request.setAttribute("tituloTarjetaSinBadges", "Resumen de la Guardia");
		request.setAttribute("tituloBadge1", "Procesos Principales");
		request.setAttribute("tituloBadge2", "Guardias / Registro");
		request.setAttribute("nombreFormulario", "formGuardia");
		request.setAttribute("formularios", guardiasFormulario.fnRegistro(request.getSession().getAttribute("cdoficina")));
		request.setAttribute("scripts",guardiasFormulario.fnRetornarScriptsRegistro());
        return "procesos/guardias/agregar";
    }
	
	@PostMapping("/guardias.consulta")
	public ResponseEntity<List<Guardia>> fnBuscarGuardias(@RequestBody PeticionConsulta pPeticion,HttpServletRequest request ){
		List<Guardia> vListaRetorno=null;
		if(request.getSession().getAttribute("cdoficina")!=null) {
			vListaRetorno=guardiasRepo.fnBuscarPorCriterio(pPeticion.getParam1(),
					pPeticion.getParam2(),
					request.getSession().getAttribute("cdoficina").toString()
					);
			if(vListaRetorno.size()>0) {
				for(int a=0;a<vListaRetorno.size();a++) {
					vListaRetorno.get(a).setTxencargado(
							personaRepo.fnConsultaPorCodigo(vListaRetorno.get(a).getCdpersonaencargada()).getTxnombres());
					vListaRetorno.get(a).setTxoficina(
							oficinaRepo.fnConsultaPorCodigo(vListaRetorno.get(a).getCdoficina()).getTxcompania());
	
					SimpleDateFormat vFormatoFecha=new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
					vListaRetorno.get(a).setFeinicioaux(
							vFormatoFecha.format(vListaRetorno.get(a).getFeinicio())
							);
					vListaRetorno.get(a).setFefinalaux(
							vFormatoFecha.format(vListaRetorno.get(a).getFefinal())
							);
				}
			}
		}
		return ResponseEntity.ok(vListaRetorno);
	}
	@PostMapping(value="guardias.consulta.integrantes")
	public ResponseEntity<List<Persona>> fnBuscarIntegrantesGuardia(@RequestBody PeticionConsulta pGuardia ){
		List<Persona> vListaRetorno=null;
		if(pGuardia!=null) {
			vListaRetorno=personaRepo.fnConsultaPorGuardia(pGuardia.getParam1());
			if(vListaRetorno.size()>0) {
				for(int a=0;a<vListaRetorno.size();a++) {
					vListaRetorno.get(a).setTxtipopersona(
						tipoPersonaRepo.fnConsultaPorCodigo(vListaRetorno.get(a).getTppersona()).getTxtipopersona());
				}
			}
		}
		return ResponseEntity.ok(vListaRetorno);
	}
	
	@PostMapping(value="guardias.insercion")
	public ResponseEntity<Guardia> fnInsercion(@RequestBody Guardia pGuardia,HttpServletRequest request ){
		Guardia vListaRetorno=null;
		if(pGuardia!=null) {
			pGuardia.setFeinicio(new Date());
			pGuardia.setFefinal(new Date());
			pGuardia.setCdoficina( 
					Long.valueOf(request.getSession().getAttribute("cdoficina").toString())
					);
			pGuardia.setCdpersonaencargada(Long.valueOf(request.getSession().getAttribute("cdpersona").toString())
					);
			pGuardia.setCdusuario(request.getSession().getAttribute("cdusuario").toString());
			vListaRetorno=guardiasRepo.save(pGuardia);
			List<GuardiaIntegrantes> vPersonasDeGuardia= pGuardia.getGuardiaIntegrantes();
			for(int a=0; a<vPersonasDeGuardia.size();a++) {
				vPersonasDeGuardia.get(a).setCdguardia(vListaRetorno.getCdguardia());
				guardiasIntegrantesRepo.save(vPersonasDeGuardia.get(a));
			}
			enviarNotificacionesPush.fnNotificacionPushGuardia(vListaRetorno);
		}
		
		return ResponseEntity.ok(vListaRetorno);
	}
	@GetMapping(value="guardias.cerrarGuardia")
	public ResponseEntity<Guardia> fnCerrarGuardia(HttpServletRequest pRequest){
		Guardia vListaRetorno=new Guardia();
		if(pRequest.getSession().getAttribute("cdoficina")!=null) {
			vListaRetorno=guardiasRepo.fnMinimaGuardiaOficina(Long.valueOf(
					pRequest.getSession().getAttribute("cdoficina").toString()
					));
		}
		
		vListaRetorno.setStguardia("2");
		vListaRetorno.setFefinal(new Date());
		guardiasRepo.save(vListaRetorno);
		return ResponseEntity.ok(vListaRetorno);
	}

	@GetMapping(value="guardias.validarExistencia")
	public ResponseEntity<List<Object>> fnValidaExistenciaGuardia(HttpServletRequest pRequest){
		List<Object> vListaRetorno=new ArrayList<>();
		if(pRequest.getSession().getAttribute("cdoficina")!=null) {
			vListaRetorno=customSQL.fnValidarGuardia(pRequest.getSession().getAttribute("cdoficina").toString());
		}
		return ResponseEntity.ok(vListaRetorno);
	}
	
}
