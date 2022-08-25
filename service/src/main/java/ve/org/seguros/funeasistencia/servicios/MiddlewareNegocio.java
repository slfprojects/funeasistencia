package ve.org.seguros.funeasistencia.servicios;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class MiddlewareNegocio implements HandlerInterceptor {

	 private String URL_INICIO_SESION="/front.funeasistencia/inicio";
	 private String URL_AUTENTICAR="/front.funeasistencia/autenticar";
	 private String URL_AUTENTICAR2="/front.funeasistencia/autenticar2";
	 private String URL_CIERRE_SESION="/front.funeasistencia/cerrarSesion";
	 private String URL_VERFICAR_SESION="/front.funeasistencia/verificarSesion";
	 private String URL_RECUPERAR_CLAVE="/front.funeasistencia/recuperarClave.indice";
	 private String URL_GESTION_CLAVE="/front.funeasistencia/recuperarclave.gestion";
	 private String URL_APK="/front.funeasistencia/descargarapp";
	 
	 
	 
	 @Override
	 public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object o) {
		try {
			if(this.URL_INICIO_SESION.equalsIgnoreCase(req.getRequestURI()) ||
					this.URL_AUTENTICAR.equalsIgnoreCase(req.getRequestURI()) ||
					this.URL_CIERRE_SESION.equalsIgnoreCase(req.getRequestURI())||
					this.URL_VERFICAR_SESION.equalsIgnoreCase(req.getRequestURI())||
					this.URL_AUTENTICAR2.equalsIgnoreCase(req.getRequestURI())||
					this.URL_RECUPERAR_CLAVE.equalsIgnoreCase(req.getRequestURI())||
					this.URL_GESTION_CLAVE.equalsIgnoreCase(req.getRequestURI())||
					this.URL_APK.equalsIgnoreCase(req.getRequestURI())
					
					
					) {
				 
			 }else {
				 if(req.getSession().getAttribute("cdusuario")==null) {
					 res.sendRedirect(URL_INICIO_SESION);
				 }else {
				 }
			 }
		     
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
		 
	 }

	 @Override
	 public void postHandle(HttpServletRequest req, HttpServletResponse res, Object o, ModelAndView mav) {
	 }
	
	 @Override
	 public void afterCompletion(HttpServletRequest req, HttpServletResponse rep, Object o, Exception e) {
	 }
}
