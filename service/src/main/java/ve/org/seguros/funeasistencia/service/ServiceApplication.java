package ve.org.seguros.funeasistencia.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "ve.org.seguros.funeasistencia.controllers")
@ComponentScan(basePackages = "ve.org.seguros.funeasistencia.servicios")
@ComponentScan(basePackages = "ve.org.seguros.funeasistencia.formularios")
@EntityScan(basePackages = "ve.org.seguros.funeasistencia.pojos")
@EnableJpaRepositories("ve.org.seguros.funeasistencia.repositorios")
public class ServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceApplication.class, args);
	}

}
