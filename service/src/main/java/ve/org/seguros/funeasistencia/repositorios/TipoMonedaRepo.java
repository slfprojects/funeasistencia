package ve.org.seguros.funeasistencia.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ve.org.seguros.funeasistencia.pojos.TipoMoneda;

public interface TipoMonedaRepo extends JpaRepository<TipoMoneda, Long> {
	
	@Query(value = "select cd_moneda, tx_moneda from xsfmoneda " 
			 ,nativeQuery = true)
	public List<TipoMoneda> fnConsultaGlobal();
	@Query(value = "select cd_moneda, tx_moneda from xsfmoneda where cd_moneda!=1 " 
			 ,nativeQuery = true)
	public List<TipoMoneda> fnConsultaGlobalPresupuesto();
	@Query(value = "select cd_moneda, tx_moneda from xsfmoneda where cd_moneda=?1" 
			 ,nativeQuery = true)
	public TipoMoneda fnConsultaPorCodigo(Long pCdMoneda);
	@Query(value = "select cd_moneda, tx_moneda from xsfmoneda where "
			+ " tx_moneda like case when ?1='todos' then tx_moneda else '%'||?1||'%' end order by cd_moneda" 
			 ,nativeQuery = true)
	public List<TipoMoneda> fnBuscarPorCriterio(String pCdMoneda);
}
