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
import ve.org.seguros.funeasistencia.formularios.OficinasFormulario;
import ve.org.seguros.funeasistencia.pojos.Oficina;
import ve.org.seguros.funeasistencia.pojos.PeticionConsulta;
import ve.org.seguros.funeasistencia.repositorios.OficinaRepo;


@Controller
@RequestMapping("front.funeasistencia")
public class OficinasController {
	
	@Autowired
	private  OficinaRepo oficinaRepo;
	
	@Autowired
	private OficinasFormulario oficinasFormulario;
	
	
	@GetMapping("/oficinas.indice")
    public String fnIndice(HttpServletRequest request) {
		request.setAttribute("idPojo", "cdoficina");
		request.setAttribute("metodoDt", "fnConfiguracionOficina");
		
		request.setAttribute("linkConsulta", "/front.funeasistencia/oficinas.consulta");
		request.setAttribute("funcionConsulta", "fnConsultar(1)");
		request.setAttribute("linkEliminar", "/front.funeasistencia/oficinas.eliminar");
		request.setAttribute("linkActualizar", "/front.funeasistencia/oficinas.indice.actualizar");
		request.setAttribute("codigoSubMenu","3");
		request.setAttribute("codigoMenu","2");
		request.setAttribute("linkAgregar", "/front.funeasistencia/oficinas.indice.agregar");
		request.setAttribute("tituloTarjeta", "Menú Principal de las Oficinas");
		request.setAttribute("tituloBadge1", "Procesos Principales");
		request.setAttribute("tituloBadge2", "Oficinas");
		request.setAttribute("nombreFormulario", "formPersonas");
		request.setAttribute("formularios", oficinasFormulario.fnConsulta() );
		request.setAttribute("scripts",oficinasFormulario.fnRetornarScripts());
        return "procesos/crud/index";
    }
	@GetMapping("/oficinas.indice.agregar")
    public String fnIndiceAgregar(HttpServletRequest request) {		
		request.setAttribute("codigoSubMenu","3");
		request.setAttribute("codigoMenu","2");
		request.setAttribute("linkVolver", "/front.funeasistencia/oficinas.indice");
		request.setAttribute("linkAgregar", "/front.funeasistencia/oficinas.insercion");
		
		request.setAttribute("tituloTarjeta", "Registro de oficinas");
		request.setAttribute("tituloBadge1", "Procesos Principales");
		request.setAttribute("tituloBadge2", "Oficinas / Registro");
		request.setAttribute("nombreFormulario", "formPersonas");
		request.setAttribute("formularios",oficinasFormulario.fnRegistroUpdate(0L));
		request.setAttribute("scripts",oficinasFormulario.fnRetornarScripts());
        return "procesos/crud/agregar";
    }
	@GetMapping("/oficinas.indice.actualizar/{cdpersona}")
    public String fnIndiceAgregar(@PathVariable(name = "cdpersona") String cdpersona, HttpServletRequest request ) {		
		request.setAttribute("codigoSubMenu","3");
		request.setAttribute("codigoMenu","2");
		request.setAttribute("linkAgregar", "/front.funeasistencia/oficinas.insercion");
		request.setAttribute("linkVolver", "/front.funeasistencia/oficinas.indice");
		request.setAttribute("tituloTarjeta", "Actualización de oficinas");
		request.setAttribute("tituloBadge1", "Procesos Principales");
		request.setAttribute("tituloBadge2", "Oficinas / Actualización");
		request.setAttribute("nombreFormulario", "formPersonas");
		request.setAttribute("formularios",oficinasFormulario.fnRegistroUpdate(Long.valueOf(cdpersona)));
		request.setAttribute("scripts",oficinasFormulario.fnRetornarScripts());
        return "procesos/crud/update";
    }
	
	
	@PostMapping(value="oficinas.insercion")
	public ResponseEntity<Oficina> fnInsercion(@RequestBody Oficina pOficinas,HttpServletRequest request ){
		Oficina vListaRetorno=null;
		if(pOficinas!=null) {
			pOficinas.setStcompania(1L);			
			vListaRetorno=oficinaRepo.save(pOficinas);
		}
		return ResponseEntity.ok(vListaRetorno);
	}
	
	@PostMapping(value="oficinas.eliminar")
	public ResponseEntity<Oficina> fnEliminar(@RequestBody Oficina pPersonas ){
		oficinaRepo.delete(pPersonas);
		return ResponseEntity.ok(pPersonas);
	}
	
	@PostMapping("/oficinas.consulta")
	public ResponseEntity<List<Oficina>> fnBuscarGuardias(@RequestBody PeticionConsulta pPeticion,HttpServletRequest request ){
		List<Oficina> vListaRetorno=null;
		vListaRetorno=oficinaRepo.fnBuscarPorCriterio(pPeticion.getParam1());
		return ResponseEntity.ok(vListaRetorno);
	}
}
