package ve.org.seguros.funeasistencia.formularios;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ve.org.seguros.funeasistencia.pojos.Formularios;
import ve.org.seguros.funeasistencia.pojos.SelectValues;
import ve.org.seguros.funeasistencia.pojos.TipoMoneda;
import ve.org.seguros.funeasistencia.pojos.TipoPago;
import ve.org.seguros.funeasistencia.repositorios.TipoMonedaRepo;
import ve.org.seguros.funeasistencia.repositorios.TipoPagoRepo;


@Service
public class TipoPagoFormulario {
	
	@Autowired
	private TipoPagoRepo tipoPagoRepo;
	
	@Autowired
	private TipoMonedaRepo tipoMonedaRepo;
	
	public List<Formularios> fnConsulta(){
		List <Formularios> vListaFormularios=new ArrayList<>();
		Formularios vFormulario=new Formularios();
		vFormulario.setCols("3");
		vFormulario.setName("param1");
		vFormulario.setLabelName("Tipo de Pago");
		vFormulario.setType("text");
		vFormulario.setInputValue("");
		vFormulario.setDisabled("");
		vFormulario.setDisplay("yes");
		vFormulario.setPlaceholder("Colocar Tipo de Pago ");

		
		vListaFormularios.add(vFormulario);
			
		
		return vListaFormularios;
	}
	public List<Formularios> fnRegistroUpdate(Long pCdPersona){
		TipoPago vObjeto=null;
		
		List <Formularios> vListaFormularios=new ArrayList<>();
		Formularios vFormulario1=new Formularios();
		vFormulario1.setCols("3");
		vFormulario1.setName("txtipopago");
		vFormulario1.setLabelName("Colocar Tipo Pago");
		vFormulario1.setType("text");
		vFormulario1.setDisplay("yes");
		vFormulario1.setPlaceholder("");
		
		Formularios vFormulario2=new Formularios();
		vFormulario2.setCols("3");
		vFormulario2.setName("cdmoneda");
		vFormulario2.setLabelName("Moneda");
		vFormulario2.setType("select");
		vFormulario2.setDisplay("yes");
		vFormulario2.setPlaceholder("");
		
		List <SelectValues> vOptions1=new ArrayList<>();
		for(TipoMoneda o:tipoMonedaRepo.fnConsultaGlobal()) {
			SelectValues option=new SelectValues();
			option.setValue(o.getCdmoneda().toString());
			option.setText(o.getTxmoneda().toString());
			vOptions1.add(option);
		}
		vFormulario2.setSelectValues(vOptions1);
		
		
		if(pCdPersona!=0L) {
			vObjeto=tipoPagoRepo.fnConsultaConsulta(Long.valueOf(pCdPersona));
			vFormulario1.setInputValue(vObjeto.getTxtipopago());
			vFormulario2.setInputValue(vObjeto.getCdmoneda().toString());
			Formularios vFormulario5=new Formularios();
			vFormulario5.setCols("6");
			vFormulario5.setName("cdtipopago");
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
