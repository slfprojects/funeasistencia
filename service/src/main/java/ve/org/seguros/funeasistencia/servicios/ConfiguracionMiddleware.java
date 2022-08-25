package ve.org.seguros.funeasistencia.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class ConfiguracionMiddleware  implements WebMvcConfigurer    {
	
	@Autowired
	private MiddlewareNegocio aMiddlewareNegocio;
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(aMiddlewareNegocio).addPathPatterns("/front.funeasistencia/**");
    }

}
