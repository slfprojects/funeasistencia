package ve.org.seguros.funeasistencia.formularios;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ve.org.seguros.funeasistencia.pojos.Formularios;
import ve.org.seguros.funeasistencia.pojos.Roles;
import ve.org.seguros.funeasistencia.repositorios.RolesRepo;


@Service
public class RolesFormulario {
	
	@Autowired
	private RolesRepo rolesRepo;
	
	public List<Formularios> fnConsulta(){
		List <Formularios> vListaFormularios=new ArrayList<>();
		Formularios vFormulario=new Formularios();
		vFormulario.setCols("3");
		vFormulario.setName("param1");
		vFormulario.setLabelName("Nombre del Rol");
		vFormulario.setType("text");
		vFormulario.setInputValue("");
		vFormulario.setDisabled("");
		vFormulario.setDisplay("yes");
		vFormulario.setPlaceholder("Colocar Nombre del Rol ");

		
		vListaFormularios.add(vFormulario);
			
		
		return vListaFormularios;
	}
	public List<Formularios> fnRegistroUpdate(Long pCdPersona){
		Roles vObjeto=null;
		
		List <Formularios> vListaFormularios=new ArrayList<>();
		Formularios vFormulario1=new Formularios();
		vFormulario1.setCols("3");
		vFormulario1.setName("txrol");
		vFormulario1.setLabelName("Colocar Nombre Rol");
		vFormulario1.setType("text");
		vFormulario1.setDisplay("yes");
		vFormulario1.setPlaceholder("");

		
		if(pCdPersona!=0L) {
			vObjeto=rolesRepo.fnConsultaPorCodigo(Long.valueOf(pCdPersona));
			vFormulario1.setInputValue(vObjeto.getTxrol());
			
			Formularios vFormulario5=new Formularios();
			vFormulario5.setCols("6");
			vFormulario5.setName("cdrol");
			vFormulario5.setLabelName("Dirección Oficina");
			vFormulario5.setType("text");
			vFormulario5.setInputValue("");
			vFormulario5.setInputValue(pCdPersona.toString());
			vFormulario5.setDisplay("none");
			vFormulario5.setPlaceholder("Colocar Direccíon ");
			vListaFormularios.add(vFormulario5);
		}
		
		vListaFormularios.add(vFormulario1);

		
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
