package ve.org.seguros.funeasistencia.formularios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ve.org.seguros.funeasistencia.pojos.Formularios;
import ve.org.seguros.funeasistencia.pojos.Oficina;
import ve.org.seguros.funeasistencia.pojos.Persona;
import ve.org.seguros.funeasistencia.pojos.Roles;
import ve.org.seguros.funeasistencia.pojos.SelectValues;
import ve.org.seguros.funeasistencia.pojos.Usuarios;
import ve.org.seguros.funeasistencia.pojos.UsuariosRol;
import ve.org.seguros.funeasistencia.repositorios.OficinaRepo;
import ve.org.seguros.funeasistencia.repositorios.PersonaRepo;
import ve.org.seguros.funeasistencia.repositorios.RolesRepo;
import ve.org.seguros.funeasistencia.repositorios.UsuariosRepo;
import ve.org.seguros.funeasistencia.repositorios.UsuariosRolRepo;

@Service
public class UsuariosFormulario {
	
	@Autowired
	private PersonaRepo personaRepo;
	@Autowired
	private RolesRepo rolesRepo;
	@Autowired
	private OficinaRepo oficinaRepo;
	@Autowired
    private UsuariosRolRepo usuariosRolRepo;
	@Autowired
	private UsuariosRepo usuariosRepo;
	
	public List<Formularios> fnConsulta(){
		List <Formularios> vListaFormularios=new ArrayList<>();
		Formularios vFormulario=new Formularios();
		vFormulario.setCols("3");
		vFormulario.setName("param1");
		vFormulario.setLabelName("Nombre de Usuario");
		vFormulario.setType("text");
		vFormulario.setInputValue("");
		vFormulario.setDisabled("");
		vFormulario.setDisplay("yes");
		vFormulario.setPlaceholder("Colocar Nombre Usuario ");

		vListaFormularios.add(vFormulario);
			
		
		return vListaFormularios;
	}
	public List<Formularios> fnRegistroUpdate(String validacion,String pCdUsuario){
		List <Formularios> vListaFormularios=new ArrayList<>();
		Formularios vFormulario1=new Formularios();
		vFormulario1.setCols("4");
		vFormulario1.setName("cdpersona");
		vFormulario1.setLabelName("Persona Relacionada al Usuario");
		vFormulario1.setType("select");
		vFormulario1.setDisplay("yes");
		vFormulario1.setPlaceholder("");
		vFormulario1.setDisabled("");
		
		List <SelectValues> vOptions1=new ArrayList<>();
		for(Persona o:personaRepo.fnPorTipoPersona()) {
			SelectValues option=new SelectValues();
			option.setValue(o.getCdpersona().toString());
			option.setText(o.getTpdocumento()+"-"+o.getTxdocumento()+" / "+o.getTxnombres().toString());
			vOptions1.add(option);
		}
		vFormulario1.setSelectValues(vOptions1);
		
		Formularios vFormulario2=new Formularios();
		vFormulario2.setCols("3");
		vFormulario2.setName("cdrol");
		vFormulario2.setLabelName("Rol de la Persona");
		vFormulario2.setType("select");
		vFormulario2.setDisabled("");
		vFormulario2.setDisplay("yes");
		vFormulario2.setPlaceholder("");
		
		List <SelectValues> vOptions2=new ArrayList<>();
		for(Roles o:rolesRepo.fnConsultaGlobal()) {
			SelectValues option=new SelectValues();
			option.setValue(o.getCdrol().toString());
			option.setText(o.getTxrol().toString());
			vOptions2.add(option);
		}
		vFormulario2.setSelectValues(vOptions2);
		
		Formularios vFormulario3=new Formularios();
		vFormulario3.setCols("4");
		vFormulario3.setName("cdcompania");
		vFormulario3.setLabelName("Oficina Relacionada al Usuario");
		vFormulario3.setType("select");
		vFormulario3.setDisplay("yes");
		vFormulario3.setDisabled("");
		vFormulario3.setPlaceholder("");
		
		List <SelectValues> vOptions5=new ArrayList<>();
		for(Oficina o:oficinaRepo.fnConsultaGlobal()) {
			SelectValues option=new SelectValues();
			option.setValue(o.getCdoficina().toString());
			option.setText(o.getTxcompania());
			vOptions5.add(option);
		}
		vFormulario3.setSelectValues(vOptions5);
		
		
		Formularios vFormulario4=new Formularios();
		vFormulario4.setCols("4");
		vFormulario4.setName("cdusuario");
		vFormulario4.setLabelName("Nombre de Usuario");
		vFormulario4.setType("text");
		vFormulario4.setInputValue("");
		vFormulario4.setDisabled("");
		vFormulario4.setDisplay("yes");
		vFormulario4.setPlaceholder("Nombre del Usuario ");
		
		
		
		Formularios vFormulario5=new Formularios();
		vFormulario5.setCols("3");
		vFormulario5.setName("staccesoappmovil");
		vFormulario5.setLabelName("Acceso al app");
		vFormulario5.setType("select");
		vFormulario5.setDisplay("yes");
		vFormulario5.setPlaceholder("");
		
		List <SelectValues> vOptions3=new ArrayList<>();
			SelectValues option1=new SelectValues();
			SelectValues option2=new SelectValues();
			option1.setValue("0");
			option1.setText("No");
			option2.setValue("1");
			option2.setText("Si");
			vOptions3.add(option1);
			vOptions3.add(option2);
		vFormulario5.setSelectValues(vOptions3);
		
		vListaFormularios.add(vFormulario1);
		vListaFormularios.add(vFormulario2);
		vListaFormularios.add(vFormulario3);
		vListaFormularios.add(vFormulario4);
		vListaFormularios.add(vFormulario5);
		
		return vListaFormularios;
	}
	public List<Formularios> fnUpdate(String validacion,String pCdUsuario){
		
		Usuarios vUsuario= usuariosRepo.fnPorCodigo(pCdUsuario);
		UsuariosRol vRol=usuariosRolRepo.fnBusquedaDt(pCdUsuario);

		List <Formularios> vListaFormularios=new ArrayList<>();
		Formularios vFormulario2=new Formularios();
		vFormulario2.setCols("3");
		vFormulario2.setName("cdrol");
		vFormulario2.setLabelName("Rol de la Persona");
		vFormulario2.setType("select");
		vFormulario2.setDisabled("");
		vFormulario2.setDisplay("yes");
		vFormulario2.setPlaceholder("");
		vFormulario2.setInputValue(vRol.getCdrol().toString());
		
		Formularios vFormulario4=new Formularios();
		vFormulario4.setCols("4");
		vFormulario4.setName("cdusuario");
		vFormulario4.setLabelName("Nombre de Usuario");
		vFormulario4.setType("text");
		vFormulario4.setInputValue("");
		vFormulario4.setDisplay("none");
		vFormulario4.setPlaceholder("Nombre del Usuario ");
		vFormulario4.setInputValue(vUsuario.getCdusuario());
		
		
		Formularios vFormulario1=new Formularios();
		vFormulario1.setCols("4");
		vFormulario1.setName("cdpersona");
		vFormulario1.setLabelName("Nombre de Usuario");
		vFormulario1.setType("text");
		vFormulario1.setInputValue("");
		vFormulario1.setDisplay("none");
		vFormulario1.setPlaceholder("Nombre del Usuario ");
		vFormulario1.setInputValue(vUsuario.getCdpersona().toString());
		
		List <SelectValues> vOptions2=new ArrayList<>();
		for(Roles o:rolesRepo.fnConsultaGlobal()) {
			SelectValues option=new SelectValues();
			option.setValue(o.getCdrol().toString());
			option.setText(o.getTxrol().toString());
			vOptions2.add(option);
		}
		vFormulario2.setSelectValues(vOptions2);
		
		Formularios vFormulario3=new Formularios();
		vFormulario3.setCols("4");
		vFormulario3.setName("cdcompania");
		vFormulario3.setLabelName("Oficina Relacionada al Usuario");
		vFormulario3.setType("select");
		vFormulario3.setDisplay("yes");
		vFormulario3.setDisabled("");
		vFormulario3.setPlaceholder("");
		vFormulario3.setInputValue(vUsuario.getCdcompania().toString());
		
		List <SelectValues> vOptions5=new ArrayList<>();
		for(Oficina o:oficinaRepo.fnConsultaGlobal()) {
			SelectValues option=new SelectValues();
			option.setValue(o.getCdoficina().toString());
			option.setText(o.getTxcompania());
			vOptions5.add(option);
		}
		vFormulario3.setSelectValues(vOptions5);
		
		
		
		Formularios vFormulario5=new Formularios();
		vFormulario5.setCols("3");
		vFormulario5.setName("staccesoappmovil");
		vFormulario5.setLabelName("Acceso al app");
		vFormulario5.setType("select");
		vFormulario5.setDisplay("yes");
		vFormulario5.setPlaceholder("");
		vFormulario5.setInputValue(vUsuario.getStaccesoappmovil().toString());
		List <SelectValues> vOptions3=new ArrayList<>();
			SelectValues option1=new SelectValues();
			SelectValues option2=new SelectValues();
			option1.setValue("0");
			option1.setText("No");
			option2.setValue("1");
			option2.setText("Si");
			vOptions3.add(option1);
			vOptions3.add(option2);
		vFormulario5.setSelectValues(vOptions3);
		vListaFormularios.add(vFormulario1);
		vListaFormularios.add(vFormulario2);
		vListaFormularios.add(vFormulario3);
		vListaFormularios.add(vFormulario4);
		vListaFormularios.add(vFormulario5);
		
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
