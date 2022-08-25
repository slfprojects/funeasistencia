package ve.org.seguros.funeasistencia.formularios;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ve.org.seguros.funeasistencia.pojos.Formularios;
import ve.org.seguros.funeasistencia.pojos.SelectValues;
import ve.org.seguros.funeasistencia.pojos.TipoContratacion;
import ve.org.seguros.funeasistencia.pojos.TipoContratacionDetalle;
import ve.org.seguros.funeasistencia.repositorios.TipoContratacionDetalleRepo;
import ve.org.seguros.funeasistencia.repositorios.TipoContratacionRepo;


@Service
public class TipoContratacionFormulario {
	
	@Autowired
	private TipoContratacionRepo tipoServicioRepo;
	
	@Autowired
	private TipoContratacionDetalleRepo  TipoContratacionDetalleRepo;
	
	public List<Formularios> fnConsulta(){
		List <Formularios> vListaFormularios=new ArrayList<>();
		Formularios vFormulario=new Formularios();
		vFormulario.setCols("3");
		vFormulario.setName("param1");
		vFormulario.setLabelName("Tipo de Contratación");
		vFormulario.setType("text");
		vFormulario.setInputValue("");
		vFormulario.setDisabled("");
		vFormulario.setDisplay("yes");
		vFormulario.setPlaceholder("Colocar Tipo Contratación");

		
		vListaFormularios.add(vFormulario);
			
		
		return vListaFormularios;
	}
	public List<Formularios> fnRegistroUpdate(Long pCdPersona){
		TipoContratacion vObjeto=null;
		
		List <Formularios> vListaFormularios=new ArrayList<>();
		Formularios vFormulario1=new Formularios();
		vFormulario1.setCols("3");
		vFormulario1.setName("txtipocontratacion");
		vFormulario1.setLabelName("Tipo Contratación");
		vFormulario1.setType("text");
		vFormulario1.setDisplay("yes");
		vFormulario1.setPlaceholder("Colocar Tipo Contratación");

		
		if(pCdPersona!=0L) {
			vObjeto=tipoServicioRepo.fnConsultaPorCodigo(Long.valueOf(pCdPersona));
			vFormulario1.setInputValue(vObjeto.getTxtipocontratacion());
			
			Formularios vFormulario5=new Formularios();
			vFormulario5.setCols("6");
			vFormulario5.setName("cdtipocontratacion");
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
	
	//Contratacion Detalle
	
	public List<Formularios> fnConsulta2(){
		List <Formularios> vListaFormularios=new ArrayList<>();
		Formularios vFormulario1=new Formularios();
		vFormulario1.setCols("3");
		vFormulario1.setName("param1");
		vFormulario1.setLabelName("Codigo de Contratación");
		vFormulario1.setType("select");
		vFormulario1.setDisplay("yes");
		vFormulario1.setPlaceholder("");
		
		List <SelectValues> vOptions1=new ArrayList<>();
		for(TipoContratacion o:tipoServicioRepo.fnConsultaGlobal()) {
			SelectValues option=new SelectValues();
			option.setValue(o.getCdtipocontratacion().toString());
			option.setText(o.getTxtipocontratacion().toString());
			vOptions1.add(option);
		}
		vFormulario1.setSelectValues(vOptions1);
		vListaFormularios.add(vFormulario1);
			
		return vListaFormularios;
	}
	
	public List<Formularios> fnRegistroUpdate2(Long pCdPersona){
		TipoContratacionDetalle vObjeto=null;
		
		List <Formularios> vListaFormularios=new ArrayList<>();
		Formularios vFormulario1=new Formularios();
		vFormulario1.setCols("3");
		vFormulario1.setName("cdtipocontratacion");
		vFormulario1.setLabelName("Código de Contratación");
		vFormulario1.setType("select");
		vFormulario1.setDisplay("yes");
		vFormulario1.setPlaceholder("");
		
		List <SelectValues> vOptions1=new ArrayList<>();
		for(TipoContratacion o: tipoServicioRepo.fnConsultaGlobal()) {
			SelectValues option=new SelectValues();
			option.setValue(o.getCdtipocontratacion().toString());
			option.setText(o.getTxtipocontratacion().toString());
			vOptions1.add(option);
		}
		vFormulario1.setSelectValues(vOptions1);
		
		
		Formularios vFormulario2=new Formularios();
		vFormulario2.setCols("3");
		vFormulario2.setName("txcontrataciondetalle");
		vFormulario2.setLabelName("Detalle de Contratación");
		vFormulario2.setType("text");
		vFormulario2.setDisplay("yes");
		vFormulario2.setPlaceholder("");
		
		if(pCdPersona!=0L) {
			vObjeto=TipoContratacionDetalleRepo.fnConsultaPorCodigo(pCdPersona.toString());
			vFormulario1.setInputValue(vObjeto.getCdtipocontratacion().toString());
			vFormulario2.setInputValue(vObjeto.getTxcontrataciondetalle().toString());
			Formularios vFormulario5=new Formularios();
			vFormulario5.setCols("6");
			vFormulario5.setName("cdcontrataciondetalle");
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


}
