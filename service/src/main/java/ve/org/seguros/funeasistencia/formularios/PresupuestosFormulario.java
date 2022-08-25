package ve.org.seguros.funeasistencia.formularios;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ve.org.seguros.funeasistencia.pojos.Ataudes;
import ve.org.seguros.funeasistencia.pojos.Capilla;
import ve.org.seguros.funeasistencia.pojos.Formularios;
import ve.org.seguros.funeasistencia.pojos.PresupuestoServicio;
import ve.org.seguros.funeasistencia.pojos.Presupuestos;
import ve.org.seguros.funeasistencia.pojos.SelectValues;
import ve.org.seguros.funeasistencia.pojos.TipoContratacion;
import ve.org.seguros.funeasistencia.pojos.TipoDocumento;
import ve.org.seguros.funeasistencia.pojos.TipoMoneda;
import ve.org.seguros.funeasistencia.pojos.TipoPago;
import ve.org.seguros.funeasistencia.pojos.TipoServicio;
import ve.org.seguros.funeasistencia.repositorios.AtaudesRepo;
import ve.org.seguros.funeasistencia.repositorios.CapillaRepo;
import ve.org.seguros.funeasistencia.repositorios.GuardiasRepo;
import ve.org.seguros.funeasistencia.repositorios.PresupuestoServicioRepo;
import ve.org.seguros.funeasistencia.repositorios.PresupuestosRepo;
import ve.org.seguros.funeasistencia.repositorios.TipoContratacionRepo;
import ve.org.seguros.funeasistencia.repositorios.TipoDocumentoRepo;
import ve.org.seguros.funeasistencia.repositorios.TipoMonedaRepo;
import ve.org.seguros.funeasistencia.repositorios.TipoPagoRepo;
import ve.org.seguros.funeasistencia.repositorios.TipoServicioRepo;


@Service
public class PresupuestosFormulario {
	
	@Autowired
	private TipoDocumentoRepo tipoDocumentoRepo;
	
	@Autowired
	private TipoContratacionRepo tipoContratacionRepo;
	
	@Autowired
	private GuardiasRepo gaurdiasRepo; 
	
	@Autowired
	private TipoServicioRepo tipoServiciRepo;
	
	@Autowired
	private TipoMonedaRepo tipoMonedaRepo;
	
	@Autowired 
	private AtaudesRepo ataudesRepo;
	
	@Autowired 
	private CapillaRepo capillaRepo;
	
	
	@Autowired
	private PresupuestosRepo presupuestosRepo;
	
	@Autowired
	private PresupuestoServicioRepo presupuestoServicioRepo;
	
	@Autowired
	private TipoPagoRepo tipoPagoRepo;
	
	
	
	public List<Formularios> fnConsulta(){		
		List <Formularios> vListaFormularios=new ArrayList<>();
		
		Formularios vFormulario=new Formularios();
		vFormulario.setCols("3");
		vFormulario.setName("param1");
		vFormulario.setLabelName("Fecha Desde Presupuesto");
		vFormulario.setType("date");
		vFormulario.setInputValue("");
		vFormulario.setDisabled("");
		vFormulario.setDisplay("yes");
		vFormulario.setPlaceholder("Colocar Fecha Presupuesto");

		Formularios vFormulario1=new Formularios();
		vFormulario1.setCols("3");
		vFormulario1.setName("param2");
		vFormulario1.setLabelName("Fecha Desde Presupuesto");
		vFormulario1.setType("date");
		vFormulario1.setInputValue("");
		vFormulario1.setDisabled("");
		vFormulario1.setDisplay("yes");
		vFormulario1.setPlaceholder("Colocar Fecha Guardia");
		
		vListaFormularios.add(vFormulario);
		vListaFormularios.add(vFormulario1);
			
		
		return vListaFormularios;
	}
	public List<String> fnRetornarScripts(){
		List<String> vScripts=new ArrayList<>();
		vScripts.add("core.js");
		vScripts.add("objetosDt.js");
		vScripts.add("presupuestos/corePresupuestos.js");
		return vScripts;
	}
	public List<String> fnScriptsUpdate(){
		List<String> vScripts=new ArrayList<>();
		vScripts.add("core.js");
		vScripts.add("objetosDt.js");
		vScripts.add("presupuestos/corePresupuestosUPD.js");
		return vScripts;
	}
	
	public List<String> fnRetornarScriptsRegistro(){
		List<String> vScripts=new ArrayList<>();
		vScripts.add("core.js");
		vScripts.add("objetosDt.js");
		vScripts.add("presupuestos/corePresupuestos.js");
		return vScripts;
	}
	
	
	public List<Formularios> fnUpdate(Object pCdOficina,String pCdPresupuesto){		
		List <Formularios> vListaFormularios=new ArrayList<>();
		if(pCdOficina==null ) {
			
		}else {
			Presupuestos vPresupuesto=presupuestosRepo.fnBuscarPorCodigo(Long.valueOf(pCdPresupuesto));
			
			Formularios vFormulario=new Formularios();
			vFormulario.setCols("3");
			vFormulario.setName("cdpresupuesto");
			vFormulario.setLabelName("Nombre Titular");
			vFormulario.setType("text");
			vFormulario.setInputValue("");
			
			vFormulario.setDisplay("none");
			vFormulario.setPlaceholder("Colocar Documento Titular");
			vFormulario.setInputValue(vPresupuesto.getCdpresupuesto().toString());
			
			Formularios vFormulario2=new Formularios();
			vFormulario2.setCols("3");
			vFormulario2.setName("cdataud");
			vFormulario2.setLabelName("Tipo Ataud");
			vFormulario2.setType("select");
			vFormulario2.setInputValue("");
			vFormulario2.setDisplay("yes");
			vFormulario2.setPlaceholder("");
			vFormulario2.setInputValue(vPresupuesto.getCdataud().toString());
			List <SelectValues> vOptions3=new ArrayList<>();
			for(Ataudes o:ataudesRepo.fnPorOficina(pCdOficina.toString())) {
				SelectValues option=new SelectValues();
				option.setValue(o.getCdataud().toString());
				option.setText(o.getTxataud().toString());
				vOptions3.add(option);
			}
			vFormulario2.setSelectValues(vOptions3);
			
			Formularios vFormulario3=new Formularios();
			vFormulario3.setCols("3");
			vFormulario3.setName("cdcapilla");
			vFormulario3.setLabelName("Capilla");
			vFormulario3.setType("select");
			vFormulario3.setInputValue("");
			vFormulario3.setDisplay("yes");
			vFormulario3.setPlaceholder("");
			vFormulario3.setInputValue(vPresupuesto.getCdcapilla().toString());
			List <SelectValues> vOptions4=new ArrayList<>();
			SelectValues option2=new SelectValues();
			option2.setValue("0");
			option2.setText("No Aplica");
			vOptions4.add(option2);
			for(Capilla o:capillaRepo.fnPorOficina(pCdOficina.toString())) {
				SelectValues option=new SelectValues();
				option.setValue(o.getCdcapilla().toString());
				option.setText(o.getTxcapilla().toString());
				vOptions4.add(option);
			}
			vFormulario3.setSelectValues(vOptions4);
			
			vListaFormularios.add(vFormulario);
			vListaFormularios.add(vFormulario2);
			vListaFormularios.add(vFormulario3);
			
			
			
			
		}
		return vListaFormularios;
	}
	
	public List<Formularios> fnRegistro(Object pCdOficina,Object pCdUsuario){		
		List <Formularios> vListaFormularios=new ArrayList<>();
		if(pCdOficina==null && pCdUsuario==null ) {
			
		}else {
			Formularios vFormulario=new Formularios();
			vFormulario.setCols("3");
			vFormulario.setName("txdocumentotitular");
			vFormulario.setLabelName("Cliente Titular");
			vFormulario.setType("text");
			vFormulario.setInputValue("");
			vFormulario.setDisplay("yes");
			vFormulario.setPlaceholder("Colocar Documento Titular");
			vFormulario.setFocusout("fnBuscarCliente()");
			
			Formularios vFormulario2=new Formularios();
			vFormulario2.setCols("3");
			vFormulario2.setName("cdtipodocumento");
			vFormulario2.setLabelName("Tipo Doc. Cliente Fallecido");
			vFormulario2.setType("select");
			vFormulario2.setDisplay("yes");
			vFormulario2.setPlaceholder("");
			
			List <SelectValues> vOptions=new ArrayList<>();
			for(TipoDocumento o:tipoDocumentoRepo.fnConsultaGlobal()) {
				SelectValues option=new SelectValues();
				option.setValue(o.getCdtipodocumento().toString());
				option.setText(o.getTxtipodocumento().toString());
				vOptions.add(option);
			}
			vFormulario2.setSelectValues(vOptions);
			
			Formularios vFormulario3=new Formularios();
			vFormulario3.setCols("3");
			vFormulario3.setName("txdocumentofallecido");
			vFormulario3.setLabelName("Documento Fallecido");
			vFormulario3.setType("text");
			vFormulario3.setInputValue("");
			vFormulario3.setDisplay("yes");
			vFormulario3.setPlaceholder("Colocar Documento Fallecido");
			vFormulario3.setFocusout("fnBuscarClienteFallecido()");
			
			Formularios vFormulario4=new Formularios();
			vFormulario4.setCols("3");
			vFormulario4.setName("txnombrefallecido");
			vFormulario4.setLabelName("Nombre CLiente Fallecido");
			vFormulario4.setType("text");
			vFormulario4.setInputValue("");
			vFormulario4.setDisplay("yes");
			vFormulario4.setPlaceholder("Colocar Nombre Fallecido");
			
			Formularios vFormulario5=new Formularios();
			vFormulario5.setCols("6");
			vFormulario5.setName("txlugartraslado");
			vFormulario5.setLabelName("Lugar de Traslado");
			vFormulario5.setType("text");
			vFormulario5.setInputValue("");
			vFormulario5.setDisplay("yes");
			vFormulario5.setPlaceholder("Colocar Lugar de Traslado");
			
			Formularios vFormulario6=new Formularios();
			vFormulario6.setCols("6");
			vFormulario6.setName("txlugarretiro");
			vFormulario6.setLabelName("Lugar de Retiro");
			vFormulario6.setType("text");
			vFormulario6.setInputValue("");
			vFormulario6.setDisplay("yes");
			vFormulario6.setPlaceholder("Colocar Lugar de Retiro");
			
			Formularios vFormulario7=new Formularios();
			vFormulario7.setCols("3");
			vFormulario7.setName("cdguardia");
			vFormulario7.setLabelName("$$");
			vFormulario7.setType("text");
			vFormulario7.setInputValue(gaurdiasRepo.fnBuscarUltimaGuardiaOficina(pCdOficina.toString()).getCdguardia().toString());
			vFormulario7.setDisplay("none");
			vFormulario7.setPlaceholder("");
			
			Formularios vFormulario9=new Formularios();
			vFormulario9.setCols("3");
			vFormulario9.setName("cdoficina");
			vFormulario9.setLabelName("$$");
			vFormulario9.setType("text");
			vFormulario9.setInputValue(pCdOficina.toString());
			vFormulario9.setDisplay("none");
			vFormulario9.setPlaceholder("");
			
			
			Formularios vFormulario10=new Formularios();
			vFormulario10.setCols("3");
			vFormulario10.setName("cdtipocontratacion");
			vFormulario10.setLabelName("Tipo Contratacion");
			vFormulario10.setType("select");
			vFormulario10.setInputValue("$$");
			vFormulario10.setDisplay("yes");
			vFormulario10.setPlaceholder("");
			vFormulario10.setOnchange("fnBuscarContatratacion()");
			List <SelectValues> vOptions2=new ArrayList<>();
			for(TipoContratacion o:tipoContratacionRepo.fnConsultaGlobal()) {
				SelectValues option=new SelectValues();
				option.setValue(o.getCdtipocontratacion().toString());
				option.setText(o.getTxtipocontratacion().toString());
				vOptions2.add(option);
			}
			vFormulario10.setSelectValues(vOptions2);
			
			Formularios vFormulario11=new Formularios();
			vFormulario11.setCols("3");
			vFormulario11.setName("cdtipocontrataciondetalle");
			vFormulario11.setType("alterno");
			vFormulario11.setDisplay("yes");
			
			
			
			Formularios vFormulario12=new Formularios();
			vFormulario12.setCols("3");
			vFormulario12.setName("cdpersonatitular");
			vFormulario12.setLabelName("$$");
			vFormulario12.setType("text");
			vFormulario12.setDisplay("none");
			vFormulario12.setPlaceholder("");
			
			
			Formularios vFormulario13=new Formularios();
			vFormulario13.setCols("3");
			vFormulario13.setName("cdataud");
			vFormulario13.setLabelName("Tipo Ataud");
			vFormulario13.setType("select");
			vFormulario13.setInputValue("");
			vFormulario13.setDisplay("yes");
			vFormulario13.setPlaceholder("");
			List <SelectValues> vOptions3=new ArrayList<>();
			SelectValues option1=new SelectValues();
			option1.setValue("0");
			option1.setText("No Aplica");
			vOptions3.add(option1);
			for(Ataudes o:ataudesRepo.fnPorOficina(pCdOficina.toString())) {
				SelectValues option=new SelectValues();
				option.setValue(o.getCdataud().toString());
				option.setText(o.getTxataud().toString());
				vOptions3.add(option);
			}
			vFormulario13.setSelectValues(vOptions3);
			
			Formularios vFormulario14=new Formularios();
			vFormulario14.setCols("3");
			vFormulario14.setName("cdcapilla");
			vFormulario14.setLabelName("Capilla");
			vFormulario14.setType("select");
			vFormulario14.setInputValue("");
			vFormulario14.setDisplay("yes");
			vFormulario14.setPlaceholder("");
			List <SelectValues> vOptions4=new ArrayList<>();
			SelectValues option2=new SelectValues();
			option2.setValue("0");
			option2.setText("No Aplica");
			vOptions4.add(option2);
			for(Capilla o:capillaRepo.fnPorOficina(pCdOficina.toString())) {
				SelectValues option=new SelectValues();
				option.setValue(o.getCdcapilla().toString());
				option.setText(o.getTxcapilla().toString());
				vOptions4.add(option);
			}
			vFormulario14.setSelectValues(vOptions4);
			
			Formularios vFormulario15=new Formularios();
			vFormulario15.setCols("3");
			vFormulario15.setName("cdmonedapresupuesto");
			vFormulario15.setLabelName("Moneda del Presupuesto");
			vFormulario15.setType("select");
			vFormulario15.setInputValue("");
			vFormulario15.setDisplay("yes");
			vFormulario15.setPlaceholder("");
			vFormulario15.setFocusout("fnLlenarMtTasa()");
			List <SelectValues> vOptions5=new ArrayList<>();
			for(TipoMoneda o:tipoMonedaRepo.fnConsultaGlobalPresupuesto()) {
				SelectValues option=new SelectValues();
				option.setValue(o.getCdmoneda().toString());
				option.setText(o.getTxmoneda().toString());
				vOptions5.add(option);
			}
			vFormulario15.setSelectValues(vOptions5);
			
			vListaFormularios.add(vFormulario);
			vListaFormularios.add(vFormulario2);
			vListaFormularios.add(vFormulario3);
			vListaFormularios.add(vFormulario4);
			vListaFormularios.add(vFormulario5);
			vListaFormularios.add(vFormulario6);
			vListaFormularios.add(vFormulario7);
			vListaFormularios.add(vFormulario9);
			vListaFormularios.add(vFormulario10);
			vListaFormularios.add(vFormulario11);
			vListaFormularios.add(vFormulario12);
			vListaFormularios.add(vFormulario13);
			vListaFormularios.add(vFormulario14);
			vListaFormularios.add(vFormulario15);
			
			
		}
		
		return vListaFormularios;
	}
	public  List<Formularios> fnUpdate2(String pCdOficina,String pCdPresupuesto) {
		List <Formularios> vListaFormularios=new ArrayList<>();
		if(pCdOficina==null ) {
			
		}else {
			List <PresupuestoServicio> vPresupuestoServicio=presupuestoServicioRepo.fnConsultaPorCodigo(pCdPresupuesto);
		
			for(PresupuestoServicio a : vPresupuestoServicio) {
	
				Formularios vFormulario10=new Formularios();
				vFormulario10.setCols("3");
				vFormulario10.setName("cdtiposervicio");
				vFormulario10.setLabelName("Tipo Servicio");
				vFormulario10.setType("select");
				vFormulario10.setDisplay("yes");
				vFormulario10.setPlaceholder("");
				vFormulario10.setInputValue(a.getCdtiposervicio().toString());
				
				List <SelectValues> vOptions=new ArrayList<>();
				for(TipoServicio o:tipoServiciRepo.fnConsultaServicioOficina(Long.valueOf(pCdOficina.toString()))) {
					SelectValues option=new SelectValues();
					option.setValue(o.getCdtiposervicio().toString());
					option.setText(o.getTxtiposervicio().toString());
					vOptions.add(option);
				}
				vFormulario10.setSelectValues(vOptions);
				
				
				Formularios vFormulario11=new Formularios();
				vFormulario11.setCols("3");
				vFormulario11.setName("cdmoneda");
				vFormulario11.setLabelName("Moneda Pago");
				vFormulario11.setType("select");
				vFormulario11.setDisplay("yes");
				vFormulario11.setPlaceholder("");
				vFormulario11.setFocusout("fnBuscarMoneda()");
				vFormulario11.setInputValue(a.getCdmoneda().toString());
				
				List <SelectValues> vOptions2=new ArrayList<>();
				for(TipoMoneda o:tipoMonedaRepo.fnConsultaGlobal()) {
					SelectValues option=new SelectValues();
					option.setValue(o.getCdmoneda().toString());
					option.setText(o.getTxmoneda());
					vOptions2.add(option);
				}
				vFormulario11.setSelectValues(vOptions2);
				
				Formularios vFormulario12=new Formularios();
				vFormulario12.setCols("3");
				vFormulario12.setName("cdtipopago");
				vFormulario12.setLabelName("Tipo Pago");
				vFormulario12.setType("select");
				vFormulario12.setDisplay("yes");
				vFormulario12.setPlaceholder("prueba");
				vFormulario12.setInputValue(a.getCdtipopago().toString());
				List <SelectValues> vOptions3=new ArrayList<>();
				for(TipoPago o:tipoPagoRepo.fnConsultaGlobal()) {
					SelectValues option=new SelectValues();
					option.setValue(o.getCdtipopago().toString());
					option.setText(o.getTxtipopago());
					vOptions3.add(option);
				}
				vFormulario12.setSelectValues(vOptions3);
				
				
				Formularios vFormulario13=new Formularios();
				vFormulario13.setCols("3");
				vFormulario13.setName("mtpresupuesto");
				vFormulario13.setLabelName("Monto del Presupuesto");
				vFormulario13.setType("number");
				vFormulario13.setPlaceholder("");
				vFormulario13.setInputValue(a.getMtpresupuesto().toString());
				
				
				vListaFormularios.add(vFormulario10);
				vListaFormularios.add(vFormulario11);
				vListaFormularios.add(vFormulario12);
				vListaFormularios.add(vFormulario13);
				
			
			}
		}
		return vListaFormularios;
	}
	public List<Formularios> fnRegistro2(Object pCdOficina,Object pCdUsuario){		
		List <Formularios> vListaFormularios=new ArrayList<>();
		if(pCdOficina==null && pCdUsuario==null ) {
			
		}else {

			Formularios vFormulario=new Formularios();
			vFormulario.setCols("3");
			vFormulario.setName("cdtiposervicio");
			vFormulario.setLabelName("Tipo Servicio");
			vFormulario.setType("select");
			vFormulario.setDisplay("yes");
			vFormulario.setPlaceholder("");
			List <SelectValues> vOptions=new ArrayList<>();
			for(TipoServicio o:tipoServiciRepo.fnConsultaServicioOficina(Long.valueOf(pCdOficina.toString()))) {
				SelectValues option=new SelectValues();
				option.setValue(o.getCdtiposervicio().toString());
				option.setText(o.getTxtiposervicio().toString());
				vOptions.add(option);
			}
			vFormulario.setSelectValues(vOptions);
			
			Formularios vFormulario2=new Formularios();
			vFormulario2.setCols("3");
			vFormulario2.setName("cdmoneda");
			vFormulario2.setLabelName("Moneda de Pago");
			vFormulario2.setType("select");
			vFormulario2.setDisplay("yes");
			vFormulario2.setPlaceholder("");
			vFormulario2.setFocusout("fnBuscarMoneda()");
			
			List <SelectValues> vOptions2=new ArrayList<>();
			for(TipoMoneda o:tipoMonedaRepo.fnConsultaGlobal()) {
				SelectValues option=new SelectValues();
				option.setValue(o.getCdmoneda().toString());
				option.setText(o.getTxmoneda());
				vOptions2.add(option);
			}
			vFormulario2.setSelectValues(vOptions2);
			
			Formularios vFormulario3=new Formularios();
			vFormulario3.setCols("3");
			vFormulario3.setName("cdtipopago");
			vFormulario3.setLabelName("Instrumento de Pago");
			vFormulario3.setType("select");
			vFormulario3.setDisplay("yes");
			vFormulario3.setPlaceholder("");
			
			
			Formularios vFormulario4=new Formularios();
			vFormulario4.setCols("3");
			vFormulario4.setName("mtpresupuesto");
			vFormulario4.setLabelName("Monto del Presupuesto");
			vFormulario4.setType("number");
			vFormulario4.setPlaceholder("");
			
			vListaFormularios.add(vFormulario);
			vListaFormularios.add(vFormulario2);
			vListaFormularios.add(vFormulario3);
			vListaFormularios.add(vFormulario4);
		}
		
		return vListaFormularios;
	}

}
