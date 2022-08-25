package ve.org.seguros.funeasistencia.controllers;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ve.org.seguros.funeasistencia.pojos.Formularios;
import ve.org.seguros.funeasistencia.pojos.Usuarios;
import ve.org.seguros.funeasistencia.servicios.CustomSQL;
import ve.org.seguros.funeasistencia.servicios.PayloadHttp;
import ve.org.seguros.funeasistencia.servicios.Utils;

@Controller
@RequestMapping("front.funeasistencia")
public class Autenticacion {	
	
	@Autowired
	private CustomSQL customSQL;
	
	@Autowired
	private Utils utils;
	
	@GetMapping("inicio")
    public String fnInicio(Model model) {
        return "Autenticacion/index";
    }
	
	@GetMapping("recuperarclave.indice")
    public String fnRecuperarClave(Model model) {
        return "Autenticacion/recuperarClave";
    }
	
	@PostMapping("recuperarclave.gestion")
	public String fnCambiarClave(@RequestParam(value = "txcorreo") String txcorreo,
			@RequestParam(value = "txusuario") String txusuario) {
		utils.fnCambiarClave(txusuario.toUpperCase(), txcorreo);

		return "Autenticacion/mensaje";
	}
	@GetMapping("descargarapp")
	public ResponseEntity<Resource> fnReporteGuardias(){
			Path vRuta = null;
		    ByteArrayResource vArchivoRetorno = null;
		    File archivoExportado=null;
		    try {
		    	archivoExportado=new File("app-debug.apk");
		    	
		    	vRuta = Paths.get(archivoExportado.getAbsolutePath());
		    	vArchivoRetorno = new ByteArrayResource(Files.readAllBytes(vRuta));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		    return ResponseEntity.ok()
		    		.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=app-debug.apk" )
		            .contentLength(archivoExportado.length())
		            .contentType(MediaType.APPLICATION_OCTET_STREAM)
		            .body(vArchivoRetorno);
	}
	
	@PostMapping("autenticar")
	public String fnAutenticar(@RequestParam(value = "cdusuario") String cdUsaurio,
			@RequestParam(value = "txclave") String txClave,Model model,HttpServletRequest pRequest) {
		String vUsuario=cdUsaurio.toUpperCase();
		List <Object> vRespuesta=customSQL.fnRetornarAutenticacion(vUsuario, utils.fnConvertirSha1(txClave),0,"N/A");
		String vLinkRetorno="";
		if(vRespuesta.size()>0) {
			Object [] vObjRespuesta=(Object[]) vRespuesta.get(0);
			pRequest.getSession().setAttribute("cdoficina", vObjRespuesta[2]);
			pRequest.getSession().setAttribute("txoficina",vObjRespuesta[3]);
			pRequest.getSession().setAttribute("nmusuario", vObjRespuesta[0]);
			pRequest.getSession().setAttribute("cdusuario", vObjRespuesta[1]);
			pRequest.getSession().setAttribute("rol", vObjRespuesta[4]);
			pRequest.getSession().setAttribute("cdpersona", vObjRespuesta[7]);
			pRequest.getSession().setAttribute("cdrol", vObjRespuesta[8]);
			pRequest.getSession().setAttribute("MenusPorUsuario", utils.fnArmarMenus(vUsuario));
			List<Object> objGuardia=customSQL.fnValidarUsuarioDistintoGuardia(vObjRespuesta[2].toString());
			
			if(vObjRespuesta[9].toString().equalsIgnoreCase("1")) {
				if(objGuardia.size()>0) {
					Object [] vObjectoRespuesta=(Object[]) objGuardia.get(0);
					
					if(!vObjectoRespuesta[1].toString().trim().equalsIgnoreCase(cdUsaurio) && !vObjRespuesta[8].toString().equalsIgnoreCase("1") 
							 ) {
						vLinkRetorno="redirect:/front.funeasistencia/guardias.indice.agregar";
					}else {
						vLinkRetorno= "redirect:/front.funeasistencia/contenido";
					}
				}else {
					vLinkRetorno= "redirect:/front.funeasistencia/guardias.indice.agregar";
				}
			}else {
				vLinkRetorno= "redirect:/front.funeasistencia/contenido";
			}
			
			return vLinkRetorno;
		}else {
			return "redirect:/front.funeasistencia/inicio";
		}
		
	}


	@GetMapping("/contenido")
    public String fnIndiceContenido(HttpServletRequest request) {
		List<Formularios> vListFormularios=new ArrayList<Formularios>();
		request.setAttribute("nombreFormulario", "formPrueba");
		request.setAttribute("formularios", vListFormularios);
        return "Autenticacion/contenido";
    }
	
	@GetMapping("/cerrarSesion")
    public String fnCerrarSesion(HttpServletRequest pRequest) {
		pRequest.getSession().invalidate();
        return "redirect:/front.funeasistencia/inicio";
    }
	
	@GetMapping("/verificarSesion")
    public ResponseEntity<List<String>> fnVerificarSesion(HttpServletRequest pRequest) {
		List<String> vListaRetorno=new ArrayList<>();
		Object vSesion=pRequest.getSession().getAttribute("cdusuario");
		if(vSesion==null) {
			
		}else {
			vListaRetorno.add("SACFA");
		}
        return ResponseEntity.ok(vListaRetorno);
    }
	
	@PostMapping(value="autenticar2")
	public ResponseEntity<PayloadHttp> fnAutenticarUsuario2(@RequestBody Usuarios pUsuario){
		PayloadHttp vPayload=new PayloadHttp();
		try {
			String vUsuario=pUsuario.getCdusuario().toUpperCase();
			List <Object> vRespuesta=customSQL.fnRetornarAutenticacion(vUsuario, utils.fnConvertirSha1(pUsuario.getTxclave()),1,pUsuario.getCdauthmaster());
			
			vPayload.setHttpConfirmacion(0);
			vPayload.setHttpEstatus(200L);
			
			if(vRespuesta.size()>0) {
				vPayload.setHttpConfirmacion(1);
				vPayload.setContenidoMensaje(vRespuesta);
			}
		} catch (Exception e) {
			vPayload.setHttpConfirmacion(0);
			vPayload.setHttpMensajeEstatus(e.getLocalizedMessage());
		}
		
		return ResponseEntity.ok(vPayload);
	}
	
	

}
