package ve.org.seguros.funeasistencia.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ve.org.seguros.funeasistencia.pojos.TipoContratacion;

public interface TipoContratacionRepo extends JpaRepository<TipoContratacion, Long> {
	
	@Query(value = "select cd_tipo_contratacion, tx_tipo_contratacion from xsftipocontratacion " 
			 ,nativeQuery = true)
	public List<TipoContratacion> fnConsultaGlobal();
	
	@Query(value = "select cd_tipo_contratacion, tx_tipo_contratacion from xsftipocontratacion  " +
			"where tx_tipo_contratacion like case when ?1='todos' then tx_tipo_contratacion else '%'||?1||'%' end order by cd_tipo_contratacion" ,nativeQuery = true)
	public List<TipoContratacion> fnConsultaCriterio(String pTxTipoContrato);
	
	@Query(value = "select cd_tipo_contratacion, tx_tipo_contratacion from xsftipocontratacion "
			+ "where cd_tipo_contratacion=?1" 
			 ,nativeQuery = true)
	public TipoContratacion fnConsultaPorCodigo(Long pCdCodigo);
}
