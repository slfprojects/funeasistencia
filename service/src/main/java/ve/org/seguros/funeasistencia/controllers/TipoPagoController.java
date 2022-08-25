package ve.org.seguros.funeasistencia.controllers;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ve.org.seguros.funeasistencia.formularios.TipoPagoFormulario;
import ve.org.seguros.funeasistencia.pojos.PeticionConsulta;
import ve.org.seguros.funeasistencia.pojos.TipoPago;
import ve.org.seguros.funeasistencia.repositorios.TipoMonedaRepo;
import ve.org.seguros.funeasistencia.repositorios.TipoPagoRepo;


@Controller
@RequestMapping("front.funeasistencia")
public class TipoPagoController {
	
	@Autowired
	private  TipoPagoRepo tipoPagoRepo;
	
	@Autowired
	private  TipoMonedaRepo tipoMonedaRepo;
	
	@Autowired
	private TipoPagoFormulario tipoPagoFormulario;
	
	
	@GetMapping("/tipopago.indice")
    public String fnIndice(HttpServletRequest request) {
		request.setAttribute("idPojo", "cdtipopago");
		request.setAttribute("metodoDt", "fnConfiguracionTipoPago");
		
		request.setAttribute("linkConsulta", "/front.funeasistencia/tipopago.consulta");
		request.setAttribute("funcionConsulta", "fnConsultar(1)");
		request.setAttribute("linkEliminar", "/front.funeasistencia/tipopago.eliminar");
		request.setAttribute("linkActualizar", "/front.funeasistencia/tipopago.indice.actualizar");
		request.setAttribute("codigoSubMenu","6");
		request.setAttribute("codigoMenu","2");
		request.setAttribute("linkAgregar", "/front.funeasistencia/tipopago.indice.agregar");
		request.setAttribute("tituloTarjeta", "Menú Principal Tipo Pago");
		request.setAttribute("tituloBadge1", "Procesos Principales");
		request.setAttribute("tituloBadge2", "Tipo Pago");
		request.setAttribute("nombreFormulario", "formPersonas");
		request.setAttribute("formularios", tipoPagoFormulario.fnConsulta() );
		request.setAttribute("scripts",tipoPagoFormulario.fnRetornarScripts());
        return "procesos/crud/index";
    }
	@GetMapping("/tipopago.indice.agregar")
    public String fnIndiceAgregar(HttpServletRequest request) {		
		request.setAttribute("codigoSubMenu","6");
		request.setAttribute("codigoMenu","2");
		request.setAttribute("linkVolver", "/front.funeasistencia/tipopago.indice");
		request.setAttribute("linkAgregar", "/front.funeasistencia/tipopago.insercion");
		
		request.setAttribute("tituloTarjeta", "Registro de Tipo Pago");
		request.setAttribute("tituloBadge1", "Procesos Principales");
		request.setAttribute("tituloBadge2", "Tipo Pago / Registro");
		request.setAttribute("nombreFormulario", "formPersonas");
		request.setAttribute("formularios",tipoPagoFormulario.fnRegistroUpdate(0L));
		request.setAttribute("scripts",tipoPagoFormulario.fnRetornarScripts());
        return "procesos/crud/agregar";
    }
	@GetMapping("/tipopago.indice.actualizar/{cdpersona}")
    public String fnIndiceAgregar(@PathVariable(name = "cdpersona") String cdpersona, HttpServletRequest request ) {		
		request.setAttribute("codigoSubMenu","6");
		request.setAttribute("codigoMenu","2");
		request.setAttribute("linkAgregar", "/front.funeasistencia/tipopago.insercion");
		request.setAttribute("linkVolver", "/front.funeasistencia/tipopago.indice");
		request.setAttribute("tituloTarjeta", "Actualización de Tipo Pago");
		request.setAttribute("tituloBadge1", "Procesos Principales");
		request.setAttribute("tituloBadge2", "Tipo Pago / Actualización");
		request.setAttribute("nombreFormulario", "formPersonas");
		request.setAttribute("formularios",tipoPagoFormulario.fnRegistroUpdate(Long.valueOf(cdpersona)));
		request.setAttribute("scripts",tipoPagoFormulario.fnRetornarScripts());
        return "procesos/crud/update";
    }
	
	
	@PostMapping(value="tipopago.insercion")
	public ResponseEntity<TipoPago> fnInsercion(@RequestBody TipoPago pOficinas,HttpServletRequest request ){
		TipoPago vListaRetorno=null;
		if(pOficinas!=null) {
						
			vListaRetorno=tipoPagoRepo.save(pOficinas);
		}
		return ResponseEntity.ok(vListaRetorno);
	}
	
	@PostMapping(value="tipopago.eliminar")
	public ResponseEntity<TipoPago> fnEliminar(@RequestBody TipoPago pPersonas ){
		tipoPagoRepo.delete(pPersonas);
		return ResponseEntity.ok(pPersonas);
	}
	
	@PostMapping("/tipopago.consulta")
	public ResponseEntity<List<TipoPago>> fnBuscarGuardias(@RequestBody PeticionConsulta pPeticion,HttpServletRequest request ){
		List<TipoPago> vListaRetorno=null;
		vListaRetorno=tipoPagoRepo.fnConsultaCriterio(pPeticion.getParam1());
		for(int indice=0;indice<vListaRetorno.size();indice++) {
			vListaRetorno.get(indice).setTxmoneda(
			tipoMonedaRepo.fnConsultaPorCodigo(
					vListaRetorno.get(indice).getCdmoneda()
			).getTxmoneda());
		}
		
		return ResponseEntity.ok(vListaRetorno);
	}
	@GetMapping("/tipopago.consulta.moneda/{cdmoneda}")
    public ResponseEntity<List<TipoPago>> fnConsultaMoeda(@PathVariable(name = "cdmoneda") String cdmoneda, HttpServletRequest request ) {		
		List<TipoPago> vRetorno=tipoPagoRepo.fnConsultaPorMoneda(cdmoneda);
        return ResponseEntity.ok(vRetorno);
    }
}
