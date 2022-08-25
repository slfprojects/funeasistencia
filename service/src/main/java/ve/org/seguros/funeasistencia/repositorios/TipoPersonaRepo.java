package ve.org.seguros.funeasistencia.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ve.org.seguros.funeasistencia.pojos.TipoPersona;

public interface TipoPersonaRepo extends JpaRepository<TipoPersona, Long> {
	
	@Query(value = "select  cd_tipo_persona, tx_tipo_persona,st_tipo_persona from xsftipopersona " + 
			"where " + 
			"tx_tipo_persona like case when ?1='todos' then tx_tipo_persona else '%'||?1||'%' end order by cd_tipo_persona ",nativeQuery = true)
	public List<TipoPersona> fnBuscarPorCriterio(String pTxTipoAtaud);
	@Query(value = "select  cd_tipo_persona, tx_tipo_persona,st_tipo_persona from xsftipopersona " + 
			"where " + 
			"cd_tipo_persona=?1 ",nativeQuery = true)
	public TipoPersona fnConsultaPorCodigo(Long pCdTipoAtaud);
	
	@Query(value = "select  cd_tipo_persona, tx_tipo_persona,st_tipo_persona from xsftipopersona where cd_tipo_persona in (1,2)" 
			 ,nativeQuery = true)
	public List<TipoPersona> fnConsultaGlobalAdmin();
	@Query(value = "select  cd_tipo_persona, tx_tipo_persona,st_tipo_persona from xsftipopersona where cd_tipo_persona not  in (1,2)" 
			 ,nativeQuery = true)
	public List<TipoPersona> fnConsultaGlobalVendedor();
}
