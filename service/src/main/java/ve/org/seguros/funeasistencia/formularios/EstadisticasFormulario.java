package ve.org.seguros.funeasistencia.formularios;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import ve.org.seguros.funeasistencia.pojos.Formularios;



@Service
public class EstadisticasFormulario {
	
	public List<Formularios> fnConsulta(){
		List <Formularios> vListaFormularios=new ArrayList<>();
		Formularios vFormulario=new Formularios();
		vFormulario.setCols("3");
		vFormulario.setName("param1");
		vFormulario.setLabelName("Fecha desde");
		vFormulario.setType("date");
		vFormulario.setInputValue("");
		vFormulario.setDisabled("");
		vFormulario.setDisplay("yes");
		vFormulario.setPlaceholder(" ");

		Formularios vFormulario1=new Formularios();
		vFormulario1.setCols("3");
		vFormulario1.setName("param2");
		vFormulario1.setLabelName("Fecha hasta");
		vFormulario1.setType("date");
		vFormulario1.setInputValue("");
		vFormulario1.setDisabled("");
		vFormulario1.setDisplay("yes");
		vFormulario1.setPlaceholder("");
		
		vListaFormularios.add(vFormulario);
		vListaFormularios.add(vFormulario1);
			
		
		return vListaFormularios;
	}
	
	public List<String> fnRetornarScripts(){
		List<String> vScripts=new ArrayList<>();
		vScripts.add("core.js");
		vScripts.add("objetosDt.js");
		vScripts.add("estadisticas/core.js");
		return vScripts;
	}

}
