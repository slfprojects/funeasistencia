package ve.org.seguros.funeasistencia.repositorios;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ve.org.seguros.funeasistencia.pojos.PresupuestoServicio;

public interface PresupuestoServicioRepo extends JpaRepository<PresupuestoServicio, Long> {
	@Query(value = "select cd_servicio_presupuesto,cd_tipo_servicio,mt_presupuesto,cd_presupuesto,cd_tipo_pago,cd_moneda from xsfpresupuestoservicio  " + 
			"where " + 
			" CD_PRESUPUESTO=?1 ",nativeQuery = true)
	public List<PresupuestoServicio> fnConsultaPorCodigo(String pCdPresupuesto);
}
