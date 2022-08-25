package ve.org.seguros.funeasistencia.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ve.org.seguros.funeasistencia.pojos.TipoAtaud;


public interface TipoAtaudRepo extends JpaRepository<TipoAtaud, Long> {
	
	@Query(value = "select  cd_ataud, tx_tipo_ataud, tx_descripcion,st_tipo_ataud from xsftipoataud " + 
			"where " + 
			"tx_tipo_ataud like case when ?1='todos' then tx_tipo_ataud else '%'||?1||'%' end order by cd_ataud ",nativeQuery = true)
	public List<TipoAtaud> fnBuscarPorCriterio(String pTxTipoAtaud);
	@Query(value = "select  cd_ataud, tx_tipo_ataud, tx_descripcion,st_tipo_ataud from xsftipoataud " + 
			"where " + 
			"cd_ataud=?1 ",nativeQuery = true)
	public TipoAtaud fnConsultaPorCodigo(Long pCdTipoAtaud);
}
