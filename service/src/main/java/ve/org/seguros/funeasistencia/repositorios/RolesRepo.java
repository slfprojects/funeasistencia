package ve.org.seguros.funeasistencia.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ve.org.seguros.funeasistencia.pojos.Roles;

public interface RolesRepo extends JpaRepository<Roles, Long> {
	@Query(value = "select  cd_rol, tx_rol,st_rol from xsfroles " + 
			"where " + 
			"tx_rol like case when ?1='todos' then tx_rol else '%'||?1||'%' end order by cd_rol ",nativeQuery = true)
	public List<Roles> fnBuscarPorCriterio(String pTxTipoAtaud);
	@Query(value = "select  cd_rol, tx_rol,st_rol from xsfroles " + 
			"where " + 
			"cd_rol=?1 ",nativeQuery = true)
	public Roles fnConsultaPorCodigo(Long pCdTipoAtaud);
	@Query(value = "select  cd_rol, tx_rol,st_rol from xsfroles " 
			,nativeQuery = true)
	public List<Roles> fnConsultaGlobal();
}
