package ve.org.seguros.funeasistencia.controllers;



import java.util.ArrayList;
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

import ve.org.seguros.funeasistencia.formularios.PersonasFormulario;
import ve.org.seguros.funeasistencia.pojos.Persona;
import ve.org.seguros.funeasistencia.pojos.PeticionConsulta;
import ve.org.seguros.funeasistencia.repositorios.PersonaRepo;
import ve.org.seguros.funeasistencia.repositorios.TipoPersonaRepo;


@Controller
@RequestMapping("front.funeasistencia")
public class PersonasController {
	
	@Autowired
	private PersonasFormulario personasFormulario;
	
	@Autowired
	private TipoPersonaRepo tipoPersonaRepo;
	
	@Autowired
	private PersonaRepo personaRepo;
	
	@GetMapping("/personas.indice")
    public String fnIndice(HttpServletRequest request) {
		request.setAttribute("idPojo", "cdpersona");
		request.setAttribute("metodoDt", "fnConfiguracionPersonas");
		
		request.setAttribute("linkConsulta", "/front.funeasistencia/personas.consulta");
		request.setAttribute("funcionConsulta", "fnConsultar(1)");
		request.setAttribute("linkEliminar", "/front.funeasistencia/personas.eliminar");
		request.setAttribute("linkActualizar", "/front.funeasistencia/personas.indice.actualizar");
		request.setAttribute("codigoSubMenu","22");
		request.setAttribute("codigoMenu","21");
		request.setAttribute("linkAgregar", "/front.funeasistencia/personas.indice.agregar");
		request.setAttribute("tituloTarjeta", "Menú Principal de las Personas");
		request.setAttribute("tituloBadge1", "Procesos Principales");
		request.setAttribute("tituloBadge2", "Personas");
		request.setAttribute("nombreFormulario", "formPersonas");
		request.setAttribute("formularios", personasFormulario.fnConsulta() );
		request.setAttribute("scripts",personasFormulario.fnRetornarScripts());
        return "procesos/crud/index";
    }
	@GetMapping("/personas.indice.agregar")
    public String fnIndiceAgregar(HttpServletRequest request) {		
		request.setAttribute("codigoSubMenu","22");
		request.setAttribute("codigoMenu","21");
		request.setAttribute("linkVolver", "/front.funeasistencia/personas.indice");
		request.setAttribute("linkAgregar", "/front.funeasistencia/personas.insercion");
		
		request.setAttribute("tituloTarjeta", "Registro de Personas");
		request.setAttribute("tituloBadge1", "Procesos Principales");
		request.setAttribute("tituloBadge2", "Personas / Registro");
		request.setAttribute("nombreFormulario", "formPersonas");
		if(request.getSession().getAttribute("cdrol")==null) {
			
		}else {
			if(request.getSession().getAttribute("cdrol").toString().equalsIgnoreCase("1")) {
				request.setAttribute("formularios",personasFormulario.fnRegistroUpdate(0L,1));
			}else {
				request.setAttribute("formularios",personasFormulario.fnRegistroUpdate(0L,0));
			}
		}
		
		request.setAttribute("scripts",personasFormulario.fnRetornarScripts());
        return "procesos/crud/agregar";
    }
	@GetMapping("/personas.indice.actualizar/{cdpersona}")
    public String fnIndiceAgregar(@PathVariable(name = "cdpersona") String cdpersona, HttpServletRequest request ) {		
		request.setAttribute("codigoSubMenu","22");
		request.setAttribute("codigoMenu","21");
		request.setAttribute("linkAgregar", "/front.funeasistencia/personas.insercion");
		request.setAttribute("linkVolver", "/front.funeasistencia/personas.indice");
		request.setAttribute("tituloTarjeta", "Actualización de Personas");
		request.setAttribute("tituloBadge1", "Procesos Principales");
		request.setAttribute("tituloBadge2", "Personas / Actualización");
		request.setAttribute("nombreFormulario", "formPersonas");
		if(request.getSession().getAttribute("cdrol")==null) {
			
		}else {
			if(request.getSession().getAttribute("cdrol").toString().equalsIgnoreCase("1")) {
				request.setAttribute("formularios",personasFormulario.fnRegistroUpdate(0L,1));
			}else {
				request.setAttribute("formularios",personasFormulario.fnRegistroUpdate(0L,0));
			}
		}
		
		request.setAttribute("scripts",personasFormulario.fnRetornarScripts());
        return "procesos/crud/update";
    }
	
	@GetMapping(value="personas.verficarClienteTitular/{pCedulaCliente}")
	public ResponseEntity<List<Persona>> fnVerficarClienteTitular(
			@PathVariable(value = "pCedulaCliente") String pCedulaCliente
			){
		List<Persona> vRetorno=new ArrayList<>();
		if(pCedulaCliente!=null) {
			vRetorno=personaRepo.fnBuscarPorCedula(pCedulaCliente);
		}
		return ResponseEntity.ok(vRetorno);
	}
	
	@PostMapping(value="personas.insercion")
	public ResponseEntity<Persona> fnInsercion(@RequestBody Persona pPersonas,HttpServletRequest request ){
		Persona vListaRetorno=null;
		if(pPersonas!=null) {
			pPersonas.setStpersona(1L);
			if(request.getSession().getAttribute("cdpersona")!=null) {
				pPersonas.setCdoficina(Long.valueOf(request.getSession().getAttribute("cdoficina").toString()));
			}
			
			vListaRetorno=personaRepo.save(pPersonas);
		}
		return ResponseEntity.ok(vListaRetorno);
	}
	
	@PostMapping(value="personas.eliminar")
	public ResponseEntity<Persona> fnEliminar(@RequestBody Persona pPersonas ){
		personaRepo.delete(pPersonas);
		return ResponseEntity.ok(pPersonas);
	}
	
	@PostMapping("/personas.consulta")
	public ResponseEntity<List<Persona>> fnBuscarGuardias(@RequestBody PeticionConsulta pPeticion,HttpServletRequest request ){
		List<Persona> vListaRetorno=null;
		if(request.getSession().getAttribute("cdoficina")!=null) {
			if(request.getSession().getAttribute("cdrol")==null) {
				
			}else {
				if(request.getSession().getAttribute("cdrol").toString().equalsIgnoreCase("1")) {
					vListaRetorno=personaRepo.fnBusquedaConsultaAdmin(pPeticion.getParam1(),
							pPeticion.getParam2()
							);
				}else {
					vListaRetorno=personaRepo.fnBusquedaConsultaVendedor(pPeticion.getParam1(),
							pPeticion.getParam2(), request.getSession().getAttribute("cdoficina").toString()
							);
				}
			}
			
		
			if(vListaRetorno.size()>0) {
				for(int a=0;a<vListaRetorno.size();a++) {
					vListaRetorno.get(a).setTxtipopersona(
						tipoPersonaRepo.fnConsultaPorCodigo(
							vListaRetorno.get(a).getTppersona()
						).getTxtipopersona()
					);
				}
			}
		}
		return ResponseEntity.ok(vListaRetorno);
	}
}
