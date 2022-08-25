package ve.org.seguros.funeasistencia.formularios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ve.org.seguros.funeasistencia.pojos.Formularios;
import ve.org.seguros.funeasistencia.pojos.Persona;
import ve.org.seguros.funeasistencia.pojos.SelectValues;
import ve.org.seguros.funeasistencia.repositorios.PersonaRepo;
import ve.org.seguros.funeasistencia.repositorios.TipoPersonaRepo;


@Service
public class GuardiasFormulario {
	
	@Autowired
	private PersonaRepo personaRepo;
	
	@Autowired
	private TipoPersonaRepo tipoPersonaRepo;
	
	public List<Formularios> fnConsulta(){		
		List <Formularios> vListaFormularios=new ArrayList<>();
		
		Formularios vFormulario=new Formularios();
		vFormulario.setCols("3");
		vFormulario.setName("param1");
		vFormulario.setLabelName("Fecha Desde Guardia");
		vFormulario.setType("date");
		vFormulario.setInputValue("");
		vFormulario.setDisabled("");
		vFormulario.setDisplay("yes");
		vFormulario.setPlaceholder("Colocar Fecha Guardia");

		Formularios vFormulario1=new Formularios();
		vFormulario1.setCols("3");
		vFormulario1.setName("param2");
		vFormulario1.setLabelName("Fecha Desde Guardia");
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
		vScripts.add("guardias/coreGuardias.js");
		return vScripts;
	}
	public List<String> fnRetornarScriptsRegistro(){
		List<String> vScripts=new ArrayList<>();
		vScripts.add("core.js");
		vScripts.add("objetosDt.js");
		vScripts.add("guardias/coreGuardias.js");
		return vScripts;
	}
	public List<Formularios> fnRegistro(Object pCdOficina){		
		List <Formularios> vListaFormularios=new ArrayList<>();
		if(pCdOficina==null) {
			
		}else {
			Formularios vFormulario=new Formularios();
			vFormulario.setCols("12");
			vFormulario.setName("cdpersona");
			vFormulario.setLabelName("Trabajador de La Guardia");
			vFormulario.setType("select");
			vFormulario.setInputValue("");
			vFormulario.setDisabled("");
			vFormulario.setDisplay("yes");
			vFormulario.setPlaceholder("");
			List <SelectValues> vOptions=new ArrayList<>();
			for(Persona o:personaRepo.fnPersonaPorCargo(pCdOficina.toString())) {
				SelectValues option=new SelectValues();
				option.setValue(o.getCdpersona().toString());
				o.setTxtipopersona(tipoPersonaRepo.fnConsultaPorCodigo(o.getTppersona()).getTxtipopersona());
				option.setText(o.getTpdocumento()+"-"+o.getTxdocumento()+", "+o.getTxnombres()+", "+o.getTxtipopersona());
				vOptions.add(option);
			}
			vFormulario.setSelectValues(vOptions);
			
			vListaFormularios.add(vFormulario);
		}
		
		return vListaFormularios;
	}

}
