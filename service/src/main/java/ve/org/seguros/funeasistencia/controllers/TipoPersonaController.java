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
import ve.org.seguros.funeasistencia.formularios.TipoPersonaFormulario;
import ve.org.seguros.funeasistencia.pojos.PeticionConsulta;
import ve.org.seguros.funeasistencia.pojos.TipoPersona;
import ve.org.seguros.funeasistencia.repositorios.TipoPersonaRepo;


@Controller
@RequestMapping("front.funeasistencia")
public class TipoPersonaController {
	
	@Autowired
	private  TipoPersonaRepo tipoPersonaRepo;
	
	@Autowired
	private TipoPersonaFormulario tipoPersonaFormulario;
	
	
	@GetMapping("/tipopersona.indice")
    public String fnIndice(HttpServletRequest request) {
		request.setAttribute("idPojo", "cdtipopersona");
		request.setAttribute("metodoDt", "fnConfiguracionTipoPersona");
		
		request.setAttribute("linkConsulta", "/front.funeasistencia/tipopersona.consulta");
		request.setAttribute("funcionConsulta", "fnConsultar(1)");
		request.setAttribute("linkEliminar", "/front.funeasistencia/tipopersona.eliminar");
		request.setAttribute("linkVolver", "/front.funeasistencia/tipopersona.indice");
		request.setAttribute("linkActualizar", "/front.funeasistencia/tipopersona.indice.actualizar");
		request.setAttribute("codigoSubMenu","4");
		request.setAttribute("codigoMenu","2");
		request.setAttribute("linkAgregar", "/front.funeasistencia/tipopersona.indice.agregar");
		request.setAttribute("tituloTarjeta", "Menú Principal Tipo Personas");
		request.setAttribute("tituloBadge1", "Procesos Principales");
		request.setAttribute("tituloBadge2", "Tipo Persona");
		request.setAttribute("nombreFormulario", "formPersonas");
		request.setAttribute("formularios", tipoPersonaFormulario.fnConsulta() );
		request.setAttribute("scripts",tipoPersonaFormulario.fnRetornarScripts());
        return "procesos/crud/index";
    }
	@GetMapping("/tipopersona.indice.agregar")
    public String fnIndiceAgregar(HttpServletRequest request) {		
		request.setAttribute("codigoSubMenu","4");
		request.setAttribute("codigoMenu","2");
		request.setAttribute("linkVolver", "/front.funeasistencia/tipopersona.indice");
		request.setAttribute("linkAgregar", "/front.funeasistencia/tipopersona.insercion");
		
		request.setAttribute("tituloTarjeta", "Registro de Tipo Persona");
		request.setAttribute("tituloBadge1", "Procesos Principales");
		request.setAttribute("tituloBadge2", "Tipo Persona / Registro");
		request.setAttribute("nombreFormulario", "formPersonas");
		request.setAttribute("formularios",tipoPersonaFormulario.fnRegistroUpdate(0L));
		request.setAttribute("scripts",tipoPersonaFormulario.fnRetornarScripts());
        return "procesos/crud/agregar";
    }
	@GetMapping("/tipopersona.indice.actualizar/{cdpersona}")
    public String fnIndiceAgregar(@PathVariable(name = "cdpersona") String cdpersona, HttpServletRequest request ) {		
		request.setAttribute("codigoSubMenu","4");
		request.setAttribute("codigoMenu","2");
		request.setAttribute("linkAgregar", "/front.funeasistencia/tipopersona.insercion");
		request.setAttribute("linkVolver", "/front.funeasistencia/tipopersona.indice");
		request.setAttribute("tituloTarjeta", "Actualización de Tipo Persona");
		request.setAttribute("tituloBadge1", "Procesos Principales");
		request.setAttribute("tituloBadge2", "Tipo Persona / Actualización");
		request.setAttribute("nombreFormulario", "formPersonas");
		request.setAttribute("formularios",tipoPersonaFormulario.fnRegistroUpdate(Long.valueOf(cdpersona)));
		request.setAttribute("scripts",tipoPersonaFormulario.fnRetornarScripts());
        return "procesos/crud/update";
    }
	
	
	@PostMapping(value="tipopersona.insercion")
	public ResponseEntity<TipoPersona> fnInsercion(@RequestBody TipoPersona pOficinas,HttpServletRequest request ){
		TipoPersona vListaRetorno=null;
		if(pOficinas!=null) {
			pOficinas.setSttipopersona(1L);			
			vListaRetorno=tipoPersonaRepo.save(pOficinas);
		}
		return ResponseEntity.ok(vListaRetorno);
	}
	
	@PostMapping(value="tipopersona.eliminar")
	public ResponseEntity<TipoPersona> fnEliminar(@RequestBody TipoPersona pPersonas ){
		tipoPersonaRepo.delete(pPersonas);
		return ResponseEntity.ok(pPersonas);
	}
	
	@PostMapping("/tipopersona.consulta")
	public ResponseEntity<List<TipoPersona>> fnBuscarGuardias(@RequestBody PeticionConsulta pPeticion,HttpServletRequest request ){
		List<TipoPersona> vListaRetorno=null;
		vListaRetorno=tipoPersonaRepo.fnBuscarPorCriterio(pPeticion.getParam1());
		return ResponseEntity.ok(vListaRetorno);
	}
}
