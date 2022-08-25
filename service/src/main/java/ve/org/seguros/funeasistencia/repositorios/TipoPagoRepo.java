package ve.org.seguros.funeasistencia.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ve.org.seguros.funeasistencia.pojos.TipoPago;

public interface TipoPagoRepo extends JpaRepository<TipoPago, Long> {
	
	@Query(value = "select cd_tipo_pago, tx_tipo_pago,cd_moneda from xsftipopago " 
			 ,nativeQuery = true)
	public List<TipoPago> fnConsultaGlobal();
	
	@Query(value = "select cd_tipo_pago, tx_tipo_pago,cd_moneda from xsftipopago where cd_tipo_pago=?1 " 
			 ,nativeQuery = true)
	public TipoPago fnConsultaConsulta(Long pCdTipoPago);
	
	@Query(value = "select cd_tipo_pago, tx_tipo_pago,cd_moneda from xsftipopago where "
			+ "tx_tipo_pago like case when ?1='todos' then tx_tipo_pago else '%'||?1||'%' end order by cd_tipo_pago " 
			 ,nativeQuery = true)
	public List<TipoPago> fnConsultaCriterio(String pCdTipoPago);
	
	@Query(value = "select cd_tipo_pago, tx_tipo_pago,cd_moneda from xsftipopago where cd_moneda=?1 " 
			 ,nativeQuery = true)
	public List<TipoPago> fnConsultaPorMoneda(String pCdTipoPago);;
}
