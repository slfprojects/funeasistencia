package ve.org.seguros.funeasistencia.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ve.org.seguros.funeasistencia.pojos.UsuariosRol;

public interface UsuariosRolRepo extends JpaRepository<UsuariosRol, Long> {
	@Query(value="SELECT CD_USUARIO,CD_ROL,CD_ROL_USUARIO FROM XSFUSUARIOSROL where cd_usuario=?1",nativeQuery=true)
	public UsuariosRol fnBusquedaDt(String pCodigo);
}
