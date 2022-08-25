package ve.org.seguros.funeasistencia.formularios;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ve.org.seguros.funeasistencia.pojos.Formularios;
import ve.org.seguros.funeasistencia.pojos.MenuRol;
import ve.org.seguros.funeasistencia.pojos.Menus;
import ve.org.seguros.funeasistencia.pojos.Roles;
import ve.org.seguros.funeasistencia.pojos.SelectValues;
import ve.org.seguros.funeasistencia.repositorios.MenuRolRepo;
import ve.org.seguros.funeasistencia.repositorios.MenusRepo;
import ve.org.seguros.funeasistencia.repositorios.RolesRepo;


@Service
public class MenuRolFormulario {
	
	@Autowired
	private RolesRepo rolesRepo;
	
	@Autowired
	private MenusRepo menusRepo;
	
	@Autowired
	private MenuRolRepo menuRolRepo;
	
	public List<Formularios> fnConsulta(){
		List <Formularios> vListaFormularios=new ArrayList<>();
		Formularios vFormulario1=new Formularios();
		vFormulario1.setCols("3");
		vFormulario1.setName("param1");
		vFormulario1.setLabelName("Rol");
		vFormulario1.setType("select");
		vFormulario1.setDisplay("yes");
		vFormulario1.setPlaceholder("");
		
		List <SelectValues> vOptions1=new ArrayList<>();
		for(Roles o:rolesRepo.fnConsultaGlobal()) {
			SelectValues option=new SelectValues();
			option.setValue(o.getCdrol().toString());
			option.setText(o.getTxrol().toString());
			vOptions1.add(option);
		}
		vFormulario1.setSelectValues(vOptions1);
		vListaFormularios.add(vFormulario1);
			
		return vListaFormularios;
	}
	public List<Formularios> fnRegistroUpdate(Long pCdPersona){
		MenuRol vObjeto=null;
		
		List <Formularios> vListaFormularios=new ArrayList<>();
		Formularios vFormulario1=new Formularios();
		vFormulario1.setCols("3");
		vFormulario1.setName("cdrol");
		vFormulario1.setLabelName("Rol");
		vFormulario1.setType("select");
		vFormulario1.setDisplay("yes");
		vFormulario1.setPlaceholder("");
		
		List <SelectValues> vOptions1=new ArrayList<>();
		for(Roles o:rolesRepo.fnConsultaGlobal()) {
			SelectValues option=new SelectValues();
			option.setValue(o.getCdrol().toString());
			option.setText(o.getTxrol().toString());
			vOptions1.add(option);
		}
		vFormulario1.setSelectValues(vOptions1);
		
		
		Formularios vFormulario2=new Formularios();
		vFormulario2.setCols("3");
		vFormulario2.setName("cdmenu");
		vFormulario2.setLabelName("Menú");
		vFormulario2.setType("select");
		vFormulario2.setDisplay("yes");
		vFormulario2.setPlaceholder("");
		
		List <SelectValues> vOptions2=new ArrayList<>();
		for(Menus o:menusRepo.fnRetornaSubMenus()) {
			SelectValues option=new SelectValues();
			option.setValue(o.getCdmenu().toString());
			option.setText(o.getTxmenu().toString());
			vOptions2.add(option);
		}
		
		vFormulario2.setSelectValues(vOptions2);
		
		if(pCdPersona!=0L) {
			vObjeto=menuRolRepo.fnBusquedaDt(Long.valueOf(pCdPersona));
			vFormulario1.setInputValue(vObjeto.getCdrol().toString());
			vFormulario2.setInputValue(vObjeto.getCdrol().toString());
			Formularios vFormulario5=new Formularios();
			vFormulario5.setCols("6");
			vFormulario5.setName("cdmenurol");
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
