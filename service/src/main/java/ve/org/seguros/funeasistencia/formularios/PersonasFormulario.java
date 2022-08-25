package ve.org.seguros.funeasistencia.formularios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ve.org.seguros.funeasistencia.pojos.Formularios;
import ve.org.seguros.funeasistencia.pojos.Persona;
import ve.org.seguros.funeasistencia.pojos.SelectValues;
import ve.org.seguros.funeasistencia.pojos.TipoDocumento;
import ve.org.seguros.funeasistencia.pojos.TipoPersona;
import ve.org.seguros.funeasistencia.repositorios.PersonaRepo;
import ve.org.seguros.funeasistencia.repositorios.TipoDocumentoRepo;
import ve.org.seguros.funeasistencia.repositorios.TipoPersonaRepo;


@Service
public class PersonasFormulario {
	
	@Autowired
	private TipoPersonaRepo tipoPersonaRepo;
	@Autowired
	private TipoDocumentoRepo tipoDocumentoRepo;
	@Autowired
	private PersonaRepo personaRepo;
	
	public List<Formularios> fnConsulta(){
		List <Formularios> vListaFormularios=new ArrayList<>();
		Formularios vFormulario=new Formularios();
		vFormulario.setCols("3");
		vFormulario.setName("param1");
		vFormulario.setLabelName("Número de Documento");
		vFormulario.setType("text");
		vFormulario.setInputValue("");
		vFormulario.setDisabled("");
		vFormulario.setDisplay("yes");
		vFormulario.setPlaceholder("Colocar Número de Documento ");

		Formularios vFormulario1=new Formularios();
		vFormulario1.setCols("3");
		vFormulario1.setName("param2");
		vFormulario1.setLabelName("Nombre de la Persona");
		vFormulario1.setType("text");
		vFormulario1.setInputValue("");
		vFormulario1.setDisabled("");
		vFormulario1.setDisplay("yes");
		vFormulario1.setPlaceholder("Colocar Nombre de la Personaa");
		
		vListaFormularios.add(vFormulario);
		vListaFormularios.add(vFormulario1);
			
		
		return vListaFormularios;
	}
	public List<Formularios> fnRegistroUpdate(Long pCdPersona, int pValidacion){
		Persona vPersona=null;
		
		List <Formularios> vListaFormularios=new ArrayList<>();
		Formularios vFormulario1=new Formularios();
		vFormulario1.setCols("3");
		vFormulario1.setName("tpdocumento");
		vFormulario1.setLabelName("Tipo de Documento");
		vFormulario1.setType("select");
		vFormulario1.setDisplay("yes");
		vFormulario1.setPlaceholder("");
		
		
		Formularios vFormulario2=new Formularios();
		vFormulario2.setCols("3");
		vFormulario2.setName("tppersona");
		vFormulario2.setLabelName("Tipo Persona");
		vFormulario2.setType("select");
		vFormulario2.setDisplay("yes");
		vFormulario2.setPlaceholder("");
		
		List <SelectValues> vOptions2=new ArrayList<>();
		for(TipoDocumento o:tipoDocumentoRepo.fnConsultaGlobal()) {
			SelectValues option=new SelectValues();
			option.setValue(o.getCdtipodocumento().toString());
			option.setText(o.getTxtipodocumento().toString());
			vOptions2.add(option);
		}
		if(pValidacion==1) {
			List <SelectValues> vOptions1=new ArrayList<>();
			for(TipoPersona o:tipoPersonaRepo.fnConsultaGlobalAdmin()) {
				SelectValues option=new SelectValues();
				option.setValue(o.getCdtipopersona().toString());
				option.setText(o.getTxtipopersona().toString());
				vOptions1.add(option);
			}
			vFormulario2.setSelectValues(vOptions1);
		}
		if(pValidacion==0) {
			List <SelectValues> vOptions1=new ArrayList<>();
			for(TipoPersona o:tipoPersonaRepo.fnConsultaGlobalVendedor()) {
				SelectValues option=new SelectValues();
				option.setValue(o.getCdtipopersona().toString());
				option.setText(o.getTxtipopersona().toString());
				vOptions1.add(option);
			}
			vFormulario2.setSelectValues(vOptions1);
		}
		
		vFormulario1.setSelectValues(vOptions2);
		
		
		Formularios vFormulario=new Formularios();
		vFormulario.setCols("3");
		vFormulario.setName("txdocumento");
		vFormulario.setLabelName("Número de Documento");
		vFormulario.setType("text");
		vFormulario.setInputValue("");
		vFormulario.setDisabled("");
		vFormulario.setDisplay("yes");
		vFormulario.setPlaceholder("Colocar Número de Documento ");
		vFormulario.setFocusout("fnValidaPersona()");
		
		Formularios vFormulario3=new Formularios();
		vFormulario3.setCols("3");
		vFormulario3.setName("txnombres");
		vFormulario3.setLabelName("Nombre de Persona");
		vFormulario3.setType("text");
		vFormulario3.setInputValue("");
		vFormulario3.setDisabled("");
		vFormulario3.setDisplay("yes");
		vFormulario3.setPlaceholder("Colocar Nombre de Persona ");
		
		Formularios vFormulario4=new Formularios();
		vFormulario4.setCols("3");
		vFormulario4.setName("txtelefono1");
		vFormulario4.setLabelName("Telefono Primario");
		vFormulario4.setType("text");
		vFormulario4.setInputValue("");
		vFormulario4.setDisabled("");
		vFormulario4.setDisplay("yes");
		vFormulario4.setPlaceholder("Colocar Telefono ");
		
		Formularios vFormulario5=new Formularios();
		vFormulario5.setCols("3");
		vFormulario5.setName("txtelefono2");
		vFormulario5.setLabelName("Telefono Secundario");
		vFormulario5.setType("text");
		vFormulario5.setInputValue("");
		vFormulario5.setDisabled("");
		vFormulario5.setDisplay("yes");
		vFormulario5.setPlaceholder("Colocar Telefono ");
		
		Formularios vFormulario6=new Formularios();
		vFormulario6.setCols("6");
		vFormulario6.setName("txdireccion");
		vFormulario6.setLabelName("Dirección");
		vFormulario6.setType("text");
		vFormulario6.setInputValue("");
		vFormulario6.setDisabled("");
		vFormulario6.setDisplay("yes");
		vFormulario6.setPlaceholder("Colocar Direccion ");
		
		Formularios vFormulario7=new Formularios();
		vFormulario7.setCols("4");
		vFormulario7.setName("txcorreo");
		vFormulario7.setLabelName("Correo");
		vFormulario7.setType("text");
		vFormulario7.setInputValue("");
		vFormulario7.setDisabled("");
		vFormulario7.setDisplay("yes");
		vFormulario7.setPlaceholder("Colocar Correo ");
		
		
		
		if(pCdPersona!=0L) {
			vPersona=
			personaRepo.fnConsultaPorCodigo(pCdPersona);
			vFormulario1.setInputValue(vPersona.getTpdocumento());
			vFormulario2.setInputValue(vPersona.getTppersona().toString());
			vFormulario.setInputValue(vPersona.getTxdocumento().toString());
			vFormulario3.setInputValue(vPersona.getTxnombres().toString());
			vFormulario4.setInputValue(vPersona.getTxtelefono1().toString());
			vFormulario5.setInputValue(vPersona.getTxtelefono2().toString());
			vFormulario6.setInputValue(vPersona.getTxdireccion().toString());
			vFormulario7.setInputValue(vPersona.getTxcorreo().toString());
			
			Formularios vFormulario8=new Formularios();
			vFormulario8.setCols("4");
			vFormulario8.setName("cdpersona");
			vFormulario8.setLabelName("Correo");
			vFormulario8.setType("text");
			vFormulario8.setInputValue(pCdPersona.toString());
			vFormulario8.setDisabled("");
			vFormulario8.setDisplay("none");
			vFormulario8.setPlaceholder("Colocar Correo ");
			vListaFormularios.add(vFormulario8);
		}
		
		vListaFormularios.add(vFormulario1);
		vListaFormularios.add(vFormulario);
		vListaFormularios.add(vFormulario2);
		vListaFormularios.add(vFormulario3);
		vListaFormularios.add(vFormulario4);
		vListaFormularios.add(vFormulario5);
		vListaFormularios.add(vFormulario6);
		vListaFormularios.add(vFormulario7);
		
		return vListaFormularios;
	}
	
	public List<String> fnRetornarScripts(){
		List<String> vScripts=new ArrayList<>();
		vScripts.add("core.js");
		vScripts.add("objetosDt.js");
		vScripts.add("CrudGlobal.js");
		vScripts.add("presupuestos/corePresupuestos.js");
		return vScripts;
	}

}
