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
import ve.org.seguros.funeasistencia.formularios.TipoContratacionFormulario;
import ve.org.seguros.funeasistencia.pojos.PeticionConsulta;
import ve.org.seguros.funeasistencia.pojos.TipoContratacion;
import ve.org.seguros.funeasistencia.pojos.TipoContratacionDetalle;
import ve.org.seguros.funeasistencia.repositorios.TipoContratacionDetalleRepo;
import ve.org.seguros.funeasistencia.repositorios.TipoContratacionRepo;


@Controller
@RequestMapping("front.funeasistencia")
public class TipoContratacionController {
	
	@Autowired
	private  TipoContratacionRepo tipoContratacionRepo;
	
	@Autowired
	private  TipoContratacionDetalleRepo tipoContratacionDetalleRepo;
	
	@Autowired
	private TipoContratacionFormulario tipoContratacionFormulario;
	
	@GetMapping("/tipocontratacion.indice")
    public String fnIndice(HttpServletRequest request) {
		request.setAttribute("idPojo", "cdtipocontratacion");
		request.setAttribute("metodoDt", "fnConfiguracionTipoContratacion");
		
		request.setAttribute("linkConsulta", "/front.funeasistencia/tipocontratacion.consulta");
		request.setAttribute("funcionConsulta", "fnConsultar(1)");
		request.setAttribute("linkEliminar", "/front.funeasistencia/tipocontratacion.eliminar");
		request.setAttribute("linkActualizar", "/front.funeasistencia/tipocontratacion.indice.actualizar");
		request.setAttribute("codigoSubMenu","7");
		request.setAttribute("codigoMenu","2");
		request.setAttribute("linkAgregar", "/front.funeasistencia/tipocontratacion.indice.agregar");
		request.setAttribute("tituloTarjeta", "Menú Principal Tipo Contrataciones");
		request.setAttribute("tituloBadge1", "Procesos Principales");
		request.setAttribute("tituloBadge2", "Tipo Contrataciones");
		request.setAttribute("nombreFormulario", "formPersonas");
		request.setAttribute("formularios", tipoContratacionFormulario.fnConsulta() );
		request.setAttribute("scripts",tipoContratacionFormulario.fnRetornarScripts());
        return "procesos/crud/index";
    }
	@GetMapping("/tipocontratacion.indice.agregar")
    public String fnIndiceAgregar(HttpServletRequest request) {		
		request.setAttribute("codigoSubMenu","10");
		request.setAttribute("codigoMenu","2");
		request.setAttribute("linkVolver", "/front.funeasistencia/tipocontratacion.indice");
		request.setAttribute("linkAgregar", "/front.funeasistencia/tipocontratacion.insercion");
		
		request.setAttribute("tituloTarjeta", "Registro de Tipo Contrataciones");
		request.setAttribute("tituloBadge1", "Procesos Principales");
		request.setAttribute("tituloBadge2", "Tipo Contrataciones / Registro");
		request.setAttribute("nombreFormulario", "formPersonas");
		request.setAttribute("formularios",tipoContratacionFormulario.fnRegistroUpdate(0L));
		request.setAttribute("scripts",tipoContratacionFormulario.fnRetornarScripts());
        return "procesos/crud/agregar";
    }
	@GetMapping("/tipocontratacion.indice.actualizar/{cdpersona}")
    public String fnIndiceAgregar(@PathVariable(name = "cdpersona") String cdpersona, HttpServletRequest request ) {		
		request.setAttribute("codigoSubMenu","10");
		request.setAttribute("codigoMenu","2");
		request.setAttribute("linkAgregar", "/front.funeasistencia/tipocontratacion.insercion");
		request.setAttribute("linkVolver", "/front.funeasistencia/tipocontratacion.indice");
		request.setAttribute("tituloTarjeta", "Actualización de Tipo Contrataciones");
		request.setAttribute("tituloBadge1", "Procesos Principales");
		request.setAttribute("tituloBadge2", "Tipo Contrataciones / Actualización");
		request.setAttribute("nombreFormulario", "formPersonas");
		request.setAttribute("formularios",tipoContratacionFormulario.fnRegistroUpdate(Long.valueOf(cdpersona)));
		request.setAttribute("scripts",tipoContratacionFormulario.fnRetornarScripts());
        return "procesos/crud/update";
    }
	
	
	@PostMapping(value="tipocontratacion.insercion")
	public ResponseEntity<TipoContratacion> fnInsercion(@RequestBody TipoContratacion pOficinas,HttpServletRequest request ){
		TipoContratacion vListaRetorno=null;
		if(pOficinas!=null) {
						
			vListaRetorno=tipoContratacionRepo.save(pOficinas);
		}
		return ResponseEntity.ok(vListaRetorno);
	}
	
	@PostMapping(value="tipocontratacion.eliminar")
	public ResponseEntity<TipoContratacion> fnEliminar(@RequestBody TipoContratacion pPersonas ){
		tipoContratacionRepo.delete(pPersonas);
		return ResponseEntity.ok(pPersonas);
	}
	
	@PostMapping("/tipocontratacion.consulta")
	public ResponseEntity<List<TipoContratacion>> fnBuscarGuardias(@RequestBody PeticionConsulta pPeticion,HttpServletRequest request ){
		List<TipoContratacion> vListaRetorno=null;
		vListaRetorno=tipoContratacionRepo.fnConsultaCriterio(pPeticion.getParam1());
		return ResponseEntity.ok(vListaRetorno);
	}
	
	//Detalle
	
	
	
	@GetMapping("/tipocontrataciondetalle.indice")
    public String fnIndice2(HttpServletRequest request) {
		request.setAttribute("idPojo", "cdcontrataciondetalle");
		request.setAttribute("metodoDt", "fnContratacionDetalle");
		
		request.setAttribute("linkConsulta", "/front.funeasistencia/tipocontrataciondetalle.consulta");
		request.setAttribute("funcionConsulta", "fnConsultar(1)");
		request.setAttribute("linkEliminar", "/front.funeasistencia/tipocontrataciondetalle.eliminar");
		request.setAttribute("linkActualizar", "/front.funeasistencia/tipocontrataciondetalle.indice.actualizar");
		request.setAttribute("codigoSubMenu","9");
		request.setAttribute("codigoMenu","2");
		request.setAttribute("linkAgregar", "/front.funeasistencia/tipocontrataciondetalle.indice.agregar");
		request.setAttribute("tituloTarjeta", "Menú Principal Detalle Contratacion");
		request.setAttribute("tituloBadge1", "Procesos Principales");
		request.setAttribute("tituloBadge2", "Tipo Contrataciones");
		request.setAttribute("nombreFormulario", "formPersonas");
		request.setAttribute("formularios", tipoContratacionFormulario.fnConsulta2() );
		request.setAttribute("scripts",tipoContratacionFormulario.fnRetornarScripts());
        return "procesos/crud/index";
    }
	@GetMapping("/tipocontrataciondetalle.indice.agregar")
    public String fnIndiceAgregar2(HttpServletRequest request) {		
		request.setAttribute("codigoSubMenu","9");
		request.setAttribute("codigoMenu","2");
		request.setAttribute("linkVolver", "/front.funeasistencia/tipocontrataciondetalle.indice");
		request.setAttribute("linkAgregar", "/front.funeasistencia/tipocontrataciondetalle.insercion");
		
		request.setAttribute("tituloTarjeta", "Registro de Detalle Contrataciones");
		request.setAttribute("tituloBadge1", "Procesos Principales");
		request.setAttribute("tituloBadge2", "Tipo Contrataciones / Registro");
		request.setAttribute("nombreFormulario", "formPersonas");
		request.setAttribute("formularios",tipoContratacionFormulario.fnRegistroUpdate2(0L));
		request.setAttribute("scripts",tipoContratacionFormulario.fnRetornarScripts());
        return "procesos/crud/agregar";
    }
	@GetMapping("/tipocontrataciondetalle.indice.actualizar/{cdpersona}")
    public String fnIndiceAgregar2(@PathVariable(name = "cdpersona") String cdpersona, HttpServletRequest request ) {		
		request.setAttribute("codigoSubMenu","9");
		request.setAttribute("codigoMenu","2");
		request.setAttribute("linkAgregar", "/front.funeasistencia/tipocontrataciondetalle.insercion");
		request.setAttribute("linkVolver", "/front.funeasistencia/tipocontrataciondetalle.indice");
		request.setAttribute("tituloTarjeta", "Actualización de Tipo Contrataciones");
		request.setAttribute("tituloBadge1", "Procesos Principales");
		request.setAttribute("tituloBadge2", "Detalle Contrataciones / Actualización");
		request.setAttribute("nombreFormulario", "formPersonas");
		request.setAttribute("formularios",tipoContratacionFormulario.fnRegistroUpdate2(Long.valueOf(cdpersona)));
		request.setAttribute("scripts",tipoContratacionFormulario.fnRetornarScripts());
        return "procesos/crud/update";
    }
	
	
	@PostMapping(value="tipocontrataciondetalle.insercion")
	public ResponseEntity<TipoContratacionDetalle> fnInsercion2(@RequestBody TipoContratacionDetalle pOficinas,HttpServletRequest request ){
		TipoContratacionDetalle vListaRetorno=null;
		if(pOficinas!=null) {
						
			vListaRetorno=tipoContratacionDetalleRepo.save(pOficinas);
		}
		return ResponseEntity.ok(vListaRetorno);
	}
	
	@PostMapping(value="tipocontrataciondetalle.eliminar")
	public ResponseEntity<TipoContratacionDetalle> fnEliminar2(@RequestBody TipoContratacionDetalle pPersonas ){
		tipoContratacionDetalleRepo.delete(pPersonas);
		return ResponseEntity.ok(pPersonas);
	}
	
	@PostMapping("/tipocontrataciondetalle.consulta")
	public ResponseEntity<List<TipoContratacionDetalle>> fnBuscarGuardias2(@RequestBody PeticionConsulta pPeticion,HttpServletRequest request ){
		List<TipoContratacionDetalle> vListaRetorno=null;
		vListaRetorno=tipoContratacionDetalleRepo.fnConsultaGlobal(pPeticion.getParam1());
		for(int indice=0;indice<vListaRetorno.size();indice++) {
			vListaRetorno.get(indice).setTxtipocontratacion(
					tipoContratacionRepo.fnConsultaPorCodigo(
							vListaRetorno.get(indice).getCdtipocontratacion()
							).getTxtipocontratacion()
					);
		}
		return ResponseEntity.ok(vListaRetorno);
	}
	
}
