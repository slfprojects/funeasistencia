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

import ve.org.seguros.funeasistencia.formularios.MenuRolFormulario;
import ve.org.seguros.funeasistencia.pojos.MenuRol;
import ve.org.seguros.funeasistencia.pojos.PeticionConsulta;
import ve.org.seguros.funeasistencia.repositorios.MenuRolRepo;
import ve.org.seguros.funeasistencia.repositorios.MenusRepo;
import ve.org.seguros.funeasistencia.repositorios.RolesRepo;


@Controller
@RequestMapping("front.funeasistencia")
public class MenusController {
	
	@Autowired
	private  MenuRolRepo menuRolRepo;
	
	@Autowired
	private MenuRolFormulario menuRolFormulario;
	
	@Autowired
	private MenusRepo menusRepo;
	
	@Autowired
	private RolesRepo rolesRepo;
	
	
	@GetMapping("/menurol.indice")
    public String fnIndice(HttpServletRequest request) {
		request.setAttribute("idPojo", "cdmenurol");
		request.setAttribute("metodoDt", "fnMenuRol");
		
		request.setAttribute("linkConsulta", "/front.funeasistencia/menurol.consulta");
		request.setAttribute("funcionConsulta", "fnConsultar(1)");
		request.setAttribute("linkEliminar", "/front.funeasistencia/menurol.eliminar");
		request.setAttribute("linkActualizar", "/front.funeasistencia/menurol.indice.actualizar");
		request.setAttribute("codigoSubMenu","43");
		request.setAttribute("codigoMenu","41");
		request.setAttribute("linkAgregar", "/front.funeasistencia/menurol.indice.agregar");
		request.setAttribute("tituloTarjeta", "Menú Principal de Menús por Rol");
		request.setAttribute("tituloBadge1", "Procesos Principales");
		request.setAttribute("tituloBadge2", "Menús por Rol");
		request.setAttribute("nombreFormulario", "formPersonas");
		request.setAttribute("formularios", menuRolFormulario.fnConsulta() );
		request.setAttribute("scripts",menuRolFormulario.fnRetornarScripts());
        return "procesos/crud/index";
    }
	@GetMapping("/menurol.indice.agregar")
    public String fnIndiceAgregar(HttpServletRequest request) {		
		request.setAttribute("codigoSubMenu","43");
		request.setAttribute("codigoMenu","41");
		request.setAttribute("linkVolver", "/front.funeasistencia/menurol.indice");
		request.setAttribute("linkAgregar", "/front.funeasistencia/menurol.insercion");
		
		request.setAttribute("tituloTarjeta", "Registro de Tipo Persona");
		request.setAttribute("tituloBadge1", "Procesos Principales");
		request.setAttribute("tituloBadge2", "Menus por Rol/ Registro");
		request.setAttribute("nombreFormulario", "formPersonas");
		request.setAttribute("formularios",menuRolFormulario.fnRegistroUpdate(0L));
		request.setAttribute("scripts",menuRolFormulario.fnRetornarScripts());
        return "procesos/crud/agregar";
    }
	@GetMapping("/menurol.indice.actualizar/{cdpersona}")
    public String fnIndiceAgregar(@PathVariable(name = "cdpersona") String cdpersona, HttpServletRequest request ) {		
		request.setAttribute("codigoSubMenu","43");
		request.setAttribute("codigoMenu","41");
		request.setAttribute("linkAgregar", "/front.funeasistencia/menurol.insercion");
		request.setAttribute("linkVolver", "/front.funeasistencia/menurol.indice");
		request.setAttribute("tituloTarjeta", "Actualización de Menús por Rol");
		request.setAttribute("tituloBadge1", "Procesos Principales");
		request.setAttribute("tituloBadge2", "Menus por Rol/ Actualización");
		request.setAttribute("nombreFormulario", "formPersonas");
		request.setAttribute("formularios",menuRolFormulario.fnRegistroUpdate(Long.valueOf(cdpersona)));
		request.setAttribute("scripts",menuRolFormulario.fnRetornarScripts());
        return "procesos/crud/update";
    }
	
	
	@PostMapping(value="menurol.insercion")
	public ResponseEntity<MenuRol> fnInsercion(@RequestBody MenuRol pOficinas,HttpServletRequest request ){
		MenuRol vListaRetorno=null;
		if(pOficinas!=null) {		
			vListaRetorno=menuRolRepo.save(pOficinas);
		}
		return ResponseEntity.ok(vListaRetorno);
	}
	
	@PostMapping(value="menurol.eliminar")
	public ResponseEntity<MenuRol> fnEliminar(@RequestBody MenuRol pPersonas ){
		menuRolRepo.delete(pPersonas);
		return ResponseEntity.ok(pPersonas);
	}
	
	@PostMapping("/menurol.consulta")
	public ResponseEntity<List<MenuRol>> fnBuscarGuardias(@RequestBody PeticionConsulta pPeticion,HttpServletRequest request ){
		List<MenuRol> vListaRetorno=null;
		vListaRetorno=menuRolRepo.fnBusquedaPorCriterio(pPeticion.getParam1());
		for(int indice=0;indice<vListaRetorno.size();indice++) {
			vListaRetorno.get(indice).setTxmenu(
					menusRepo.fnConsultaPorCodigo(vListaRetorno.get(indice).getCdmenu()).getTxmenu()
					);
			vListaRetorno.get(indice).setTxrol(
					rolesRepo.fnConsultaPorCodigo(
							vListaRetorno.get(indice).getCdrol()
							).getTxrol()
					);
		}
		return ResponseEntity.ok(vListaRetorno);
	}
}
