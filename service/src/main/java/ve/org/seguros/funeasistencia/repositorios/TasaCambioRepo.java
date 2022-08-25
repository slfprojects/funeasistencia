package ve.org.seguros.funeasistencia.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ve.org.seguros.funeasistencia.pojos.TasaCambio;


public interface TasaCambioRepo extends JpaRepository<TasaCambio, Long> {
	
	@Query(value = "select  version, cd_moneda, tp_tasa,fe_efectiva_tasa,mt_tasa from tasacambio "
			+ "			where fe_efectiva_tasa=trunc(sysdate) and cd_moneda=?1 and tp_tasa='C'"
			,nativeQuery = true)
	public List<TasaCambio> fnBuscarPorCriterio(Long pCdMoneda);
	@Query(value = "select  version, cd_moneda, tp_tasa,fe_efectiva_tasa,mt_tasa from tasacambio "
			+ "			where cd_moneda=?1 and tp_tasa='C'"
			+ " and fe_efectiva_tasa in (select max(fe_efectiva_tasa) from tasacambio where cd_moneda=?1 and tp_tasa='C' )"
			,nativeQuery = true)
	public List<TasaCambio> fnBuscarUltimaFecha(Long pCdMoneda);
	@Query(value = "select  version, cd_moneda, tp_tasa,fe_efectiva_tasa,mt_tasa from tasacambio "
			+ "			where cd_moneda=?1 and tp_tasa='C'"
			,nativeQuery = true)
	public List<TasaCambio> fnBuscarTasaCambio(String cdMoneda);

}
