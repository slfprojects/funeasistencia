package ve.org.seguros.funeasistencia.formularios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ve.org.seguros.funeasistencia.pojos.Formularios;
import ve.org.seguros.funeasistencia.pojos.ProveedorAtaud;
import ve.org.seguros.funeasistencia.pojos.SelectValues;
import ve.org.seguros.funeasistencia.pojos.TipoServicio;
import ve.org.seguros.funeasistencia.repositorios.ProveedorAtaudRepo;
import ve.org.seguros.funeasistencia.repositorios.TipoServicioRepo;


@Service
public class AjusteOficinaFormulario {
	
	@Autowired
	private TipoServicioRepo tipoServicioRepo;
	
	@Autowired
	private ProveedorAtaudRepo proveedorAtaudRepo;

	public List<Formularios> fnRegistroServicio(String pCdOficina){
		List <Formularios> vListaFormularios=new ArrayList<>();
		Formularios vFormulario1=new Formularios();
		vFormulario1.setCols("3");
		vFormulario1.setName("cdtiposervicio");
		vFormulario1.setLabelName("Servicio");
		vFormulario1.setType("select");
		vFormulario1.setDisplay("yes");
		vFormulario1.setPlaceholder("");
		
		List <SelectValues> vOptions1=new ArrayList<>();
		for(TipoServicio o:tipoServicioRepo.fnConsultaGlobal(
				)) {
			SelectValues option=new SelectValues();
			option.setValue(o.getCdtiposervicio().toString());
			option.setText(o.getTxtiposervicio().toString());
			vOptions1.add(option);
		}
		
		vFormulario1.setSelectValues(vOptions1);
		
		vListaFormularios.add(vFormulario1);
		return vListaFormularios;
	}
	public List<Formularios> fnRegistroAtaud(String pCdOficina){
		
		List <Formularios> vListaFormularios=new ArrayList<>();
		
		
		Formularios vFormulario=new Formularios();
		vFormulario.setCols("3");
		vFormulario.setName("txataud");
		vFormulario.setLabelName("Nombre Ataud");
		vFormulario.setType("text");
		vFormulario.setInputValue("");
		vFormulario.setDisabled("");
		vFormulario.setDisplay("yes");
		vFormulario.setPlaceholder("Colocar Nombre Ataud ");
		
		Formularios vFormulario2=new Formularios();
		vFormulario2.setCols("3");
		vFormulario2.setName("cdproveedor");
		vFormulario2.setLabelName("Nombre de Proveedor");
		vFormulario2.setType("select");
		vFormulario2.setDisplay("yes");
		vFormulario2.setPlaceholder("");
		
		if(pCdOficina!=null) {
			List <SelectValues> vOptions2=new ArrayList<>();
			for(ProveedorAtaud o:proveedorAtaudRepo.fnPorOficina(pCdOficina)) {
				SelectValues option=new SelectValues();
				option.setValue(o.getCdproveedor().toString());
				option.setText(o.getTxproveedor().toString());
				vOptions2.add(option);
			}
			vFormulario2.setSelectValues(vOptions2);
		}else {
			
		}
		
	
		vListaFormularios.add(vFormulario);
		vListaFormularios.add(vFormulario2);
		return vListaFormularios;
	}
	public List<Formularios> fnRegistroCapilla(String pCdOficina){
		List <Formularios> vListaFormularios=new ArrayList<>();
		Formularios vFormulario1=new Formularios();
		vFormulario1.setCols("3");
		vFormulario1.setName("txcapilla");
		vFormulario1.setLabelName("Nombre Ataud");
		vFormulario1.setType("text");
		vFormulario1.setInputValue("");
		vFormulario1.setDisabled("");
		vFormulario1.setDisplay("yes");
		vFormulario1.setPlaceholder("Colocar Nombre Capilla "); 
		
	
		vListaFormularios.add(vFormulario1);
		return vListaFormularios;
	}
	public List<Formularios> fnRegistroProveedor(String pCdOficina){
		List <Formularios> vListaFormularios=new ArrayList<>();
		Formularios vFormulario1=new Formularios();
		vFormulario1.setCols("3");
		vFormulario1.setName("txproveedor");
		vFormulario1.setLabelName("Nombre Proveedor");
		vFormulario1.setType("text");
		vFormulario1.setInputValue("");
		vFormulario1.setDisabled("");
		vFormulario1.setDisplay("yes");
		vFormulario1.setPlaceholder("Colocar Nombre Proveedor "); 
		
	
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
