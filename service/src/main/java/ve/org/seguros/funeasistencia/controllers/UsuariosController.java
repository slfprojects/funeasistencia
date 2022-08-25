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
import ve.org.seguros.funeasistencia.formularios.UsuariosFormulario;
import ve.org.seguros.funeasistencia.pojos.PeticionConsulta;
import ve.org.seguros.funeasistencia.pojos.Usuarios;
import ve.org.seguros.funeasistencia.pojos.UsuariosRol;
import ve.org.seguros.funeasistencia.repositorios.PersonaRepo;
import ve.org.seguros.funeasistencia.repositorios.UsuariosRepo;
import ve.org.seguros.funeasistencia.repositorios.UsuariosRolRepo;
//import ve.org.seguros.funeasistencia.servicios.CoreXCobEmails;
import ve.org.seguros.funeasistencia.servicios.CoreXCobEmails;
import ve.org.seguros.funeasistencia.servicios.Utils;


@Controller
@RequestMapping("front.funeasistencia")
public class UsuariosController {
	
	/*
	 * 
	 */
     @Autowired
     private UsuariosFormulario usuariosFormulario;
     
     @Autowired
     private PersonaRepo personaRepo;

     @Autowired
     private UsuariosRepo usuariosRepo;
     
     @Autowired
     private Utils utils;
     
     @Autowired
     private CoreXCobEmails email;
     
     @Autowired
     private UsuariosRolRepo usuariosRolRepo;
	
	@GetMapping("/usuarios.indice")
    public String fnIndice(HttpServletRequest request) {
		request.setAttribute("idPojo", "cdusuario");
		request.setAttribute("metodoDt", "fnConfiguracionUsuarios");
		
		request.setAttribute("linkConsulta", "/front.funeasistencia/usuarios.consulta");
		request.setAttribute("funcionConsulta", "fnConsultar(1)");
		request.setAttribute("linkEliminar", "/front.funeasistencia/usuarios.eliminar");
		request.setAttribute("linkActualizar", "/front.funeasistencia/usuarios.indice.actualizar");
		request.setAttribute("codigoSubMenu","42");
		request.setAttribute("codigoMenu","41");
		request.setAttribute("linkAgregar", "/front.funeasistencia/usuarios.indice.agregar");
		request.setAttribute("tituloTarjeta", "Menú Principal de las Usuarios");
		request.setAttribute("tituloBadge1", "Procesos Principales");
		request.setAttribute("tituloBadge2", "Usuarios");
		request.setAttribute("nombreFormulario", "formPersonas");
		request.setAttribute("formularios", usuariosFormulario.fnConsulta() );
		request.setAttribute("scripts",usuariosFormulario.fnRetornarScripts());
        return "procesos/crud/index";
    }
	@GetMapping("/usuarios.indice.agregar")
    public String fnIndiceAgregar(HttpServletRequest request) {		
		request.setAttribute("codigoSubMenu","42");
		request.setAttribute("codigoMenu","41");
		request.setAttribute("linkVolver", "/front.funeasistencia/usuarios.indice");
		request.setAttribute("linkAgregar", "/front.funeasistencia/usuarios.insercion");
		
		request.setAttribute("tituloTarjeta", "Registro de Personas");
		request.setAttribute("tituloBadge1", "Procesos Principales");
		request.setAttribute("tituloBadge2", "Usuarios / Registro");
		request.setAttribute("nombreFormulario", "formPersonas");
		request.setAttribute("formularios",usuariosFormulario.fnRegistroUpdate("0","0"));
		request.setAttribute("scripts",usuariosFormulario.fnRetornarScripts());
        return "procesos/crud/agregar";
    }
	@GetMapping("/usuarios.indice.actualizar/{cdusuario}")
    public String fnIndiceAgregar(@PathVariable(name = "cdusuario") String cdusuario, HttpServletRequest request ) {		
		request.setAttribute("codigoSubMenu","42");
		request.setAttribute("codigoMenu","41");
		request.setAttribute("linkAgregar", "/front.funeasistencia/usuarios.insercion");
		request.setAttribute("linkVolver", "/front.funeasistencia/usuarios.indice");
		request.setAttribute("tituloTarjeta", "Actualización de Usuarios");
		request.setAttribute("tituloBadge1", "Procesos Principales");
		request.setAttribute("tituloBadge2", "Usuarios / Actualización");
		request.setAttribute("nombreFormulario", "formPersonas");
		request.setAttribute("formularios",usuariosFormulario.fnUpdate("1",cdusuario));
		request.setAttribute("scripts",usuariosFormulario.fnRetornarScripts());
        return "procesos/crud/update";
    }
	@PostMapping(value="usuarios.insercion")
	public ResponseEntity<Usuarios> fnInsercion(@RequestBody Usuarios pUsuarios,HttpServletRequest request ){
		Usuarios vListaRetorno=null;
		if(pUsuarios!=null) {
			pUsuarios.setStusuario(1L);
			pUsuarios.setStconexion(1L);
			pUsuarios.setStaccesoweb(1L);
			pUsuarios.setCdauthmaster("NA");
			String vGeneraClave="";
			pUsuarios.setCdusuario(pUsuarios.getCdusuario().toUpperCase());
			List<Usuarios> vBuscarUsuario=usuariosRepo.fnBusquedaCambioClave(pUsuarios.getCdusuario());
			if(vBuscarUsuario.size()>0) {
				pUsuarios.setTxclave(vBuscarUsuario.get(0).getTxclave());
				vListaRetorno=usuariosRepo.save(pUsuarios);
				UsuariosRol vRol=usuariosRolRepo.fnBusquedaDt(pUsuarios.getCdusuario());
				vRol.setCdrol(pUsuarios.getCdrol());
				usuariosRolRepo.save(vRol);
			}else {
				vGeneraClave=utils.fnGenerarClave();
				pUsuarios.setTxclave(utils.fnConvertirSha1(vGeneraClave));
				vListaRetorno=usuariosRepo.save(pUsuarios);
				UsuariosRol vObj=new  UsuariosRol();
				vObj.setCdrol(pUsuarios.getCdrol());
				vObj.setCdusuario(pUsuarios.getCdusuario().toUpperCase());
				usuariosRolRepo.save(vObj);
				String vCorreo=personaRepo.fnConsultaPorCodigo(vListaRetorno.getCdpersona()).getTxcorreo();
				String vMotivo="Creación de Contraseña FUNEASISTENCIA";

				String vMensaje="<img src='cid:image001' width=\"300\" height=\"45\"> <br>"
						+ "<p>Estimado Usuario de FUNEASISTENCIA, "+"<b>"+pUsuarios.getCdusuario()+"</b> </p>"
			    		+ "<p>Es un Placer Saludarle,  El presente correo es para notificar que se ha creado una contraseña. <br>"
			    		+ "<p>Siendo: </p> <b>"+vGeneraClave+"</b>"
			    		+ "<br><br> <p>Es un placer servirle de Apoyo.</p> <b><p> ATENCI&Oacute;N, ESTE CORREO ES PARA PROCESOS AUTOMATIZADOS, <b>(NO RESPONDER AL CORREO)</b></p></br> ";
				
				email.fnEnviarCorreo(vCorreo, vMensaje,vMotivo);
			}
		
			
			//enviar a correo
		}
		return ResponseEntity.ok(vListaRetorno);
	}
	
	@PostMapping(value="usuarios.eliminar")
	public ResponseEntity<Usuarios> fnEliminar(@RequestBody Usuarios pPersonas ){
		usuariosRepo.delete(pPersonas);
		return ResponseEntity.ok(pPersonas);
	}
	
	@PostMapping("/usuarios.consulta")
	public ResponseEntity<List<Usuarios>> fnBuscarGuardias(@RequestBody PeticionConsulta pPeticion,HttpServletRequest request ){
		List<Usuarios> vListaRetorno=null;
		if(request.getSession().getAttribute("cdoficina")!=null) {
			vListaRetorno=usuariosRepo.fnBusquedaPorCriterio(pPeticion.getParam1());
			for(int indice=0;indice<vListaRetorno.size();indice++) {
				vListaRetorno.get(indice).setTxnombre(
					personaRepo.fnConsultaPorCodigo(
							vListaRetorno.get(indice).getCdpersona()
							).getTxnombres()
				);
				vListaRetorno.get(indice).setTxdocumento(
						personaRepo.fnConsultaPorCodigo(
								vListaRetorno.get(indice).getCdpersona()
								).getTpdocumento()+"-"+
						personaRepo.fnConsultaPorCodigo(
								vListaRetorno.get(indice).getCdpersona()
								).getTxdocumento()
						
				);
				vListaRetorno.get(indice).setTxcorreo(
						personaRepo.fnConsultaPorCodigo(
								vListaRetorno.get(indice).getCdpersona()
								).getTxcorreo()
				);
				vListaRetorno.get(indice).setTxtelefono(
						personaRepo.fnConsultaPorCodigo(
								vListaRetorno.get(indice).getCdpersona()
								).getTxtelefono1()
				);
				
			}
			
		

		}
		return ResponseEntity.ok(vListaRetorno);
	}
}
