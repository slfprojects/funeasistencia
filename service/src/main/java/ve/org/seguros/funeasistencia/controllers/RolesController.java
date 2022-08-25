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
import ve.org.seguros.funeasistencia.formularios.RolesFormulario;
import ve.org.seguros.funeasistencia.pojos.PeticionConsulta;
import ve.org.seguros.funeasistencia.pojos.Roles;
import ve.org.seguros.funeasistencia.repositorios.RolesRepo;


@Controller
@RequestMapping("front.funeasistencia")
public class RolesController {
	
	@Autowired
	private  RolesRepo rolesRepo;
	
	@Autowired
	private RolesFormulario rolesFormulario;
	
	
	@GetMapping("/roles.indice")
    public String fnIndice(HttpServletRequest request) {
		request.setAttribute("idPojo", "cdrol");
		request.setAttribute("metodoDt", "fnConfiguracionRol");
		
		request.setAttribute("linkConsulta", "/front.funeasistencia/roles.consulta");
		request.setAttribute("funcionConsulta", "fnConsultar(1)");
		request.setAttribute("linkEliminar", "/front.funeasistencia/roles.eliminar");
		request.setAttribute("linkActualizar", "/front.funeasistencia/roles.indice.actualizar");
		request.setAttribute("codigoSubMenu","12");
		request.setAttribute("codigoMenu","6");
		request.setAttribute("linkAgregar", "/front.funeasistencia/roles.indice.agregar");
		request.setAttribute("tituloTarjeta", "Menú Principal Roles");
		request.setAttribute("tituloBadge1", "Procesos Principales");
		request.setAttribute("tituloBadge2", "Rols");
		request.setAttribute("nombreFormulario", "formPersonas");
		request.setAttribute("formularios", rolesFormulario.fnConsulta() );
		request.setAttribute("scripts",rolesFormulario.fnRetornarScripts());
        return "procesos/crud/index";
    }
	@GetMapping("/roles.indice.agregar")
    public String fnIndiceAgregar(HttpServletRequest request) {		
		request.setAttribute("codigoSubMenu","12");
		request.setAttribute("codigoMenu","6");
		request.setAttribute("linkVolver", "/front.funeasistencia/roles.indice");
		request.setAttribute("linkAgregar", "/front.funeasistencia/roles.insercion");
		
		request.setAttribute("tituloTarjeta", "Registro de Roles");
		request.setAttribute("tituloBadge1", "Procesos Principales");
		request.setAttribute("tituloBadge2", "Roles/ Registro");
		request.setAttribute("nombreFormulario", "formPersonas");
		request.setAttribute("formularios",rolesFormulario.fnRegistroUpdate(0L));
		request.setAttribute("scripts",rolesFormulario.fnRetornarScripts());
        return "procesos/crud/agregar";
    }
	@GetMapping("/roles.indice.actualizar/{cdpersona}")
    public String fnIndiceAgregar(@PathVariable(name = "cdpersona") String cdpersona, HttpServletRequest request ) {		
		request.setAttribute("codigoSubMenu","12");
		request.setAttribute("codigoMenu","6");
		request.setAttribute("linkAgregar", "/front.funeasistencia/roles.insercion");
		request.setAttribute("linkVolver", "/front.funeasistencia/roles.indice");
		request.setAttribute("tituloTarjeta", "Actualización de Roles");
		request.setAttribute("tituloBadge1", "Procesos Principales");
		request.setAttribute("tituloBadge2", "Roles / Actualización");
		request.setAttribute("nombreFormulario", "formPersonas");
		request.setAttribute("formularios",rolesFormulario.fnRegistroUpdate(Long.valueOf(cdpersona)));
		request.setAttribute("scripts",rolesFormulario.fnRetornarScripts());
        return "procesos/crud/update";
    }
	
	
	@PostMapping(value="roles.insercion")
	public ResponseEntity<Roles> fnInsercion(@RequestBody Roles pOficinas,HttpServletRequest request ){
		Roles vListaRetorno=null;
		if(pOficinas!=null) {
			pOficinas.setStrol(1L);			
			vListaRetorno=rolesRepo.save(pOficinas);
		}
		return ResponseEntity.ok(vListaRetorno);
	}
	
	@PostMapping(value="roles.eliminar")
	public ResponseEntity<Roles> fnEliminar(@RequestBody Roles pPersonas ){
		rolesRepo.delete(pPersonas);
		return ResponseEntity.ok(pPersonas);
	}
	
	@PostMapping("/roles.consulta")
	public ResponseEntity<List<Roles>> fnBuscarGuardias(@RequestBody PeticionConsulta pPeticion,HttpServletRequest request ){
		List<Roles> vListaRetorno=null;
		vListaRetorno=rolesRepo.fnBuscarPorCriterio(pPeticion.getParam1());
		return ResponseEntity.ok(vListaRetorno);
	}
}
