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
import ve.org.seguros.funeasistencia.formularios.TipoMonedaFormulario;
import ve.org.seguros.funeasistencia.pojos.PeticionConsulta;
import ve.org.seguros.funeasistencia.pojos.TipoMoneda;
import ve.org.seguros.funeasistencia.repositorios.TipoMonedaRepo;


@Controller
@RequestMapping("front.funeasistencia")
public class TipoMonedaController {
	
	@Autowired
	private  TipoMonedaRepo tipoMonedaRepo;
	
	@Autowired
	private TipoMonedaFormulario tipoMonedaFormulario;
	
	
	@GetMapping("/moneda.indice")
    public String fnIndice(HttpServletRequest request) {
		request.setAttribute("idPojo", "cdmoneda");
		request.setAttribute("metodoDt", "fnConfiguracionTipoMoneda");
		
		request.setAttribute("linkConsulta", "/front.funeasistencia/moneda.consulta");
		request.setAttribute("funcionConsulta", "fnConsultar(1)");
		request.setAttribute("linkEliminar", "/front.funeasistencia/moneda.eliminar");
		request.setAttribute("linkActualizar", "/front.funeasistencia/moneda.indice.actualizar");
		request.setAttribute("codigoSubMenu","8");
		request.setAttribute("codigoMenu","2");
		request.setAttribute("linkAgregar", "/front.funeasistencia/moneda.indice.agregar");
		request.setAttribute("tituloTarjeta", "Menú Principal Moneda");
		request.setAttribute("tituloBadge1", "Procesos Principales");
		request.setAttribute("tituloBadge2", "Moneda");
		request.setAttribute("nombreFormulario", "formPersonas");
		request.setAttribute("formularios", tipoMonedaFormulario.fnConsulta() );
		request.setAttribute("scripts",tipoMonedaFormulario.fnRetornarScripts());
        return "procesos/crud/index";
    }
	@GetMapping("/moneda.indice.agregar")
    public String fnIndiceAgregar(HttpServletRequest request) {		
		request.setAttribute("codigoSubMenu","8");
		request.setAttribute("codigoMenu","2");
		request.setAttribute("linkVolver", "/front.funeasistencia/moneda.indice");
		request.setAttribute("linkAgregar", "/front.funeasistencia/moneda.insercion");
		
		request.setAttribute("tituloTarjeta", "Registro de Tipo Persona");
		request.setAttribute("tituloBadge1", "Procesos Principales");
		request.setAttribute("tituloBadge2", "Moneda / Registro");
		request.setAttribute("nombreFormulario", "formPersonas");
		request.setAttribute("formularios",tipoMonedaFormulario.fnRegistroUpdate(0L));
		request.setAttribute("scripts",tipoMonedaFormulario.fnRetornarScripts());
        return "procesos/crud/agregar";
    }
	@GetMapping("/moneda.indice.actualizar/{cdpersona}")
    public String fnIndiceAgregar(@PathVariable(name = "cdpersona") String cdpersona, HttpServletRequest request ) {		
		request.setAttribute("codigoSubMenu","8");
		request.setAttribute("codigoMenu","2");
		request.setAttribute("linkAgregar", "/front.funeasistencia/moneda.insercion");
		request.setAttribute("linkVolver", "/front.funeasistencia/moneda.indice");
		request.setAttribute("tituloTarjeta", "Actualización de Tipo Persona");
		request.setAttribute("tituloBadge1", "Procesos Principales");
		request.setAttribute("tituloBadge2", "Moneda / Actualización");
		request.setAttribute("nombreFormulario", "formPersonas");
		request.setAttribute("formularios",tipoMonedaFormulario.fnRegistroUpdate(Long.valueOf(cdpersona)));
		request.setAttribute("scripts",tipoMonedaFormulario.fnRetornarScripts());
        return "procesos/crud/update";
    }
	
	
	@PostMapping(value="moneda.insercion")
	public ResponseEntity<TipoMoneda> fnInsercion(@RequestBody TipoMoneda pOficinas,HttpServletRequest request ){
		TipoMoneda vListaRetorno=null;
		if(pOficinas!=null) {
			vListaRetorno=tipoMonedaRepo.save(pOficinas);
		}
		return ResponseEntity.ok(vListaRetorno);
	}
	
	@PostMapping(value="moneda.eliminar")
	public ResponseEntity<TipoMoneda> fnEliminar(@RequestBody TipoMoneda pPersonas ){
		tipoMonedaRepo.delete(pPersonas);
		return ResponseEntity.ok(pPersonas);
	}
	
	@PostMapping("/moneda.consulta")
	public ResponseEntity<List<TipoMoneda>> fnBuscarGuardias(@RequestBody PeticionConsulta pPeticion,HttpServletRequest request ){
		List<TipoMoneda> vListaRetorno=null;
		vListaRetorno=tipoMonedaRepo.fnBuscarPorCriterio(pPeticion.getParam1());
		return ResponseEntity.ok(vListaRetorno);
	}
}
