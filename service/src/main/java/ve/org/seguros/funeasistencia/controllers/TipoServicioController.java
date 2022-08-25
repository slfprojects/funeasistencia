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
import ve.org.seguros.funeasistencia.formularios.TipoServicioFormulario;
import ve.org.seguros.funeasistencia.pojos.PeticionConsulta;
import ve.org.seguros.funeasistencia.pojos.TipoServicio;
import ve.org.seguros.funeasistencia.repositorios.TipoServicioRepo;


@Controller
@RequestMapping("front.funeasistencia")
public class TipoServicioController {
	
	@Autowired
	private  TipoServicioRepo tipoServicioRepo;
	
	@Autowired
	private TipoServicioFormulario tipoServicioFormulario;
	
	
	@GetMapping("/tiposervicio.indice")
    public String fnIndice(HttpServletRequest request) {
		request.setAttribute("idPojo", "cdtiposervicio");
		request.setAttribute("metodoDt", "fnConfiguracionTipoServicio");
		
		request.setAttribute("linkConsulta", "/front.funeasistencia/tiposervicio.consulta");
		request.setAttribute("funcionConsulta", "fnConsultar(1)");
		request.setAttribute("linkEliminar", "/front.funeasistencia/tiposervicio.eliminar");
		request.setAttribute("linkActualizar", "/front.funeasistencia/tiposervicio.indice.actualizar");
		request.setAttribute("codigoSubMenu","5");
		request.setAttribute("codigoMenu","2");
		request.setAttribute("linkAgregar", "/front.funeasistencia/tiposervicio.indice.agregar");
		request.setAttribute("tituloTarjeta", "Menú Principal Tipo Servicio");
		request.setAttribute("tituloBadge1", "Procesos Principales");
		request.setAttribute("tituloBadge2", "Tipo Servicio");
		request.setAttribute("nombreFormulario", "formPersonas");
		request.setAttribute("formularios", tipoServicioFormulario.fnConsulta() );
		request.setAttribute("scripts",tipoServicioFormulario.fnRetornarScripts());
        return "procesos/crud/index";
    }
	@GetMapping("/tiposervicio.indice.agregar")
    public String fnIndiceAgregar(HttpServletRequest request) {		
		request.setAttribute("codigoSubMenu","5");
		request.setAttribute("codigoMenu","2");
		request.setAttribute("linkVolver", "/front.funeasistencia/tiposervicio.indice");
		request.setAttribute("linkAgregar", "/front.funeasistencia/tiposervicio.insercion");
		
		request.setAttribute("tituloTarjeta", "Registro de Tipo Servicio");
		request.setAttribute("tituloBadge1", "Procesos Principales");
		request.setAttribute("tituloBadge2", "Tipo Servicio / Registro");
		request.setAttribute("nombreFormulario", "formPersonas");
		request.setAttribute("formularios",tipoServicioFormulario.fnRegistroUpdate(0L));
		request.setAttribute("scripts",tipoServicioFormulario.fnRetornarScripts());
        return "procesos/crud/agregar";
    }
	@GetMapping("/tiposervicio.indice.actualizar/{cdpersona}")
    public String fnIndiceAgregar(@PathVariable(name = "cdpersona") String cdpersona, HttpServletRequest request ) {		
		request.setAttribute("codigoSubMenu","5");
		request.setAttribute("codigoMenu","2");
		request.setAttribute("linkAgregar", "/front.funeasistencia/tiposervicio.insercion");
		request.setAttribute("linkVolver", "/front.funeasistencia/tiposervicio.indice");
		request.setAttribute("tituloTarjeta", "Actualización de Tipo Servicio");
		request.setAttribute("tituloBadge1", "Procesos Principales");
		request.setAttribute("tituloBadge2", "Tipo Servicio / Actualización");
		request.setAttribute("nombreFormulario", "formPersonas");
		request.setAttribute("formularios",tipoServicioFormulario.fnRegistroUpdate(Long.valueOf(cdpersona)));
		request.setAttribute("scripts",tipoServicioFormulario.fnRetornarScripts());
        return "procesos/crud/update";
    }
	
	
	@PostMapping(value="tiposervicio.insercion")
	public ResponseEntity<TipoServicio> fnInsercion(@RequestBody TipoServicio pOficinas,HttpServletRequest request ){
		TipoServicio vListaRetorno=null;
		if(pOficinas!=null) {
			pOficinas.setSttiposervicio(1L);			
			vListaRetorno=tipoServicioRepo.save(pOficinas);
		}
		return ResponseEntity.ok(vListaRetorno);
	}
	
	@PostMapping(value="tiposervicio.eliminar")
	public ResponseEntity<TipoServicio> fnEliminar(@RequestBody TipoServicio pPersonas ){
		tipoServicioRepo.delete(pPersonas);
		return ResponseEntity.ok(pPersonas);
	}
	
	@PostMapping("/tiposervicio.consulta")
	public ResponseEntity<List<TipoServicio>> fnBuscarGuardias(@RequestBody PeticionConsulta pPeticion,HttpServletRequest request ){
		List<TipoServicio> vListaRetorno=null;
		vListaRetorno=tipoServicioRepo.fnBuscarPorCriterio(pPeticion.getParam1());
		return ResponseEntity.ok(vListaRetorno);
	}
}
