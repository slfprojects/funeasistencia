package ve.org.seguros.funeasistencia.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ve.org.seguros.funeasistencia.pojos.TipoContratacionDetalle;

public interface TipoContratacionDetalleRepo extends JpaRepository<TipoContratacionDetalle, Long> {
	
	@Query(value = "select CD_CONTRATACION_DETALLE, CD_TIPO_CONTRATACION,TX_CONTRATACION_DETALLE from XSFTIPOCONTRATACIONDETALLE "
			+ "where CD_TIPO_CONTRATACION=?1" 
			 ,nativeQuery = true)
	public List<TipoContratacionDetalle> fnConsultaGlobal(String pCdTipoContratacion);
	@Query(value = "select CD_CONTRATACION_DETALLE, CD_TIPO_CONTRATACION,TX_CONTRATACION_DETALLE from XSFTIPOCONTRATACIONDETALLE "
			+ "where CD_CONTRATACION_DETALLE=?1" 
			 ,nativeQuery = true)
	public TipoContratacionDetalle fnConsultaPorCodigo(String pCdTipoContratacionDetalle);
}
