package ve.org.seguros.funeasistencia.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import ve.org.seguros.funeasistencia.pojos.GuardiaIntegrantes;

public interface GuardiasIntegrantesRepo extends JpaRepository<GuardiaIntegrantes, Long> {
	
}
