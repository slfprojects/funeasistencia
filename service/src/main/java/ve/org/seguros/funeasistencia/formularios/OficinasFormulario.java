package ve.org.seguros.funeasistencia.formularios;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ve.org.seguros.funeasistencia.pojos.Formularios;
import ve.org.seguros.funeasistencia.pojos.Oficina;
import ve.org.seguros.funeasistencia.repositorios.OficinaRepo;


@Service
public class OficinasFormulario {
	
	@Autowired
	private OficinaRepo OficinaRepo;
	
	public List<Formularios> fnConsulta(){
		List <Formularios> vListaFormularios=new ArrayList<>();
		Formularios vFormulario=new Formularios();
		vFormulario.setCols("3");
		vFormulario.setName("param1");
		vFormulario.setLabelName("Nombre de Oficina");
		vFormulario.setType("text");
		vFormulario.setInputValue("");
		vFormulario.setDisabled("");
		vFormulario.setDisplay("yes");
		vFormulario.setPlaceholder("Colocar Nombre de Oficina ");

		
		vListaFormularios.add(vFormulario);
			
		
		return vListaFormularios;
	}
	public List<Formularios> fnRegistroUpdate(Long pCdPersona){
		Oficina vObjeto=null;
		
		List <Formularios> vListaFormularios=new ArrayList<>();
		Formularios vFormulario1=new Formularios();
		vFormulario1.setCols("3");
		vFormulario1.setName("txcompania");
		vFormulario1.setLabelName("Nombre de la Oficina");
		vFormulario1.setType("text");
		vFormulario1.setDisplay("yes");
		vFormulario1.setPlaceholder("");

		Formularios vFormulario=new Formularios();
		vFormulario.setCols("3");
		vFormulario.setName("txtelefono1");
		vFormulario.setLabelName("Telefono Primario Oficina");
		vFormulario.setType("text");
		vFormulario.setInputValue("");
		vFormulario.setDisabled("");
		vFormulario.setDisplay("yes");
		vFormulario.setPlaceholder("Colocar Número de Telefono ");
		
		Formularios vFormulario3=new Formularios();
		vFormulario3.setCols("3");
		vFormulario3.setName("txtelefono2");
		vFormulario3.setLabelName("Telefono Secundario Oficina");
		vFormulario3.setType("text");
		vFormulario3.setInputValue("");
		vFormulario3.setDisabled("");
		vFormulario3.setDisplay("yes");
		vFormulario3.setPlaceholder("Colocar Número de Telefono");
		
		Formularios vFormulario4=new Formularios();
		vFormulario4.setCols("6");
		vFormulario4.setName("txdireccion");
		vFormulario4.setLabelName("Dirección Oficina");
		vFormulario4.setType("text");
		vFormulario4.setInputValue("");
		vFormulario4.setDisabled("");
		vFormulario4.setDisplay("yes");
		vFormulario4.setPlaceholder("Colocar Direccíon ");
		
		
		
		if(pCdPersona!=0L) {
			
			vObjeto=OficinaRepo.fnConsultaPorCodigo(pCdPersona);
			vFormulario1.setInputValue(vObjeto.getTxcompania());
			vFormulario.setInputValue(vObjeto.getTxtelefono1().toString());
			vFormulario3.setInputValue(vObjeto.getTxtelefono2().toString());
			vFormulario4.setInputValue(vObjeto.getTxdireccion().toString());
			Formularios vFormulario5=new Formularios();
			vFormulario5.setCols("6");
			vFormulario5.setName("cdoficina");
			vFormulario5.setLabelName("Dirección Oficina");
			vFormulario5.setType("text");
			vFormulario5.setInputValue("");
			vFormulario5.setInputValue(pCdPersona.toString());
			vFormulario5.setDisplay("none");
			vFormulario5.setPlaceholder("Colocar Direccíon ");
			vListaFormularios.add(vFormulario5);
			

		}
		
		vListaFormularios.add(vFormulario1);
		vListaFormularios.add(vFormulario);
		vListaFormularios.add(vFormulario3);
		vListaFormularios.add(vFormulario4);

		
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
