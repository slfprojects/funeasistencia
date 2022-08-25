package ve.org.seguros.funeasistencia.formularios;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ve.org.seguros.funeasistencia.pojos.Formularios;
import ve.org.seguros.funeasistencia.pojos.SelectValues;
import ve.org.seguros.funeasistencia.pojos.TipoServicio;
import ve.org.seguros.funeasistencia.repositorios.TipoServicioRepo;


@Service
public class TipoServicioFormulario {
	
	@Autowired
	private TipoServicioRepo tipoServicioRepo;
	
	public List<Formularios> fnConsulta(){
		List <Formularios> vListaFormularios=new ArrayList<>();
		Formularios vFormulario=new Formularios();
		vFormulario.setCols("3");
		vFormulario.setName("param1");
		vFormulario.setLabelName("Nombre de Servicio");
		vFormulario.setType("text");
		vFormulario.setInputValue("");
		vFormulario.setDisabled("");
		vFormulario.setDisplay("yes");
		vFormulario.setPlaceholder("Colocar Nombre de Servicio ");
		
		
		
		vListaFormularios.add(vFormulario);
			
		
		return vListaFormularios;
	}
	public List<Formularios> fnRegistroUpdate(Long pCdPersona){
		TipoServicio vObjeto=null;
		
		List <Formularios> vListaFormularios=new ArrayList<>();
		Formularios vFormulario1=new Formularios();
		vFormulario1.setCols("3");
		vFormulario1.setName("txtiposervicio");
		vFormulario1.setLabelName("Colocar Tipo Servicio");
		vFormulario1.setType("text");
		vFormulario1.setDisplay("yes");
		vFormulario1.setPlaceholder("");
		
		Formularios vFormulario2=new Formularios();
		vFormulario2.setCols("3");
		vFormulario2.setName("inaplicavelacion");
		vFormulario2.setLabelName("¿Uso de Ataud y Capilla?");
		vFormulario2.setType("select");
		vFormulario2.setDisplay("yes");
		vFormulario2.setPlaceholder("");
		
		List <SelectValues> vOptions3=new ArrayList<>();
			SelectValues option1=new SelectValues();
			SelectValues option2=new SelectValues();
			option1.setValue("0");
			option1.setText("No");
			option2.setValue("1");
			option2.setText("Si");
			vOptions3.add(option1);
			vOptions3.add(option2);
		vFormulario2.setSelectValues(vOptions3);
		
		if(pCdPersona!=0L) {
			vObjeto=tipoServicioRepo.fnConsultaPorCodigo(Long.valueOf(pCdPersona));
			vFormulario1.setInputValue(vObjeto.getTxtiposervicio());
			vFormulario2.setInputValue(vObjeto.getInaplicavelacion().toString());
			
			Formularios vFormulario5=new Formularios();
			vFormulario5.setCols("6");
			vFormulario5.setName("cdtiposervicio");
			vFormulario5.setLabelName("Dirección Oficina");
			vFormulario5.setType("text");
			vFormulario5.setInputValue("");
			vFormulario5.setInputValue(pCdPersona.toString());
			vFormulario5.setDisplay("none");
			vFormulario5.setPlaceholder("Colocar Direccíon ");
			vListaFormularios.add(vFormulario5);
		}
		
		vListaFormularios.add(vFormulario1);
		vListaFormularios.add(vFormulario2);

		
		return vListaFormularios;
	}
	
	public List<String> fnRetornarScripts(){
		List<String> vScripts=new ArrayList<>();
		vScripts.add("core.js");
		vScripts.add("objetosDt.js");
		vScripts.add("CrudGlobal.js");
		return vScripts;
	}

}
