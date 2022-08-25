package ve.org.seguros.funeasistencia.repositorios;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import ve.org.seguros.funeasistencia.pojos.Presupuestos;

public interface PresupuestosRepo extends JpaRepository<Presupuestos, Long> {
	@Query(value = " select cd_moneda_presupuesto,mt_tasa,cd_guardia,tx_documento_fallecido,tx_nombre_fallecido,cd_tipo_documento,cd_contratacion_detalle,cd_ataud,cd_capilla,st_presupuesto,cd_persona_elaborador,tx_causa_anulacion,cd_presupuesto,cd_persona_titular,fe_presupuesto,cd_usuario_elaborador,cd_oficina,tx_lugar_retiro,tx_lugar_traslado "
			+ " from xsfpresupuesto  " + 
			"where " + 
			" trunc(fe_presupuesto) between to_date(?1,'yyyy-mm-dd') and to_date(?2,'yyyy-mm-dd') and cd_oficina=?3 and st_presupuesto in (1,3) ",nativeQuery = true)
	public List<Presupuestos> fnBuscarPorCriterio(String pFechaInicio,String pFechaFin,String pCdOficina);
	
	@Query(value = " select cd_moneda_presupuesto,mt_tasa,cd_guardia,tx_documento_fallecido,tx_nombre_fallecido,cd_tipo_documento,cd_contratacion_detalle,cd_ataud,cd_capilla,st_presupuesto,cd_persona_elaborador,tx_causa_anulacion,cd_presupuesto,cd_persona_titular,fe_presupuesto,cd_usuario_elaborador,cd_oficina,tx_lugar_retiro,tx_lugar_traslado "
			+ " from xsfpresupuesto  " + 
			"where " + 
			" trunc(fe_presupuesto) between to_date(?1,'yyyy-mm-dd') and to_date(?2,'yyyy-mm-dd') and cd_oficina=?3 and st_presupuesto in (2,4) ",nativeQuery = true)
	public List<Presupuestos> fnBuscarServiciosPorCriterio(String pFechaInicio,String pFechaFin,String pCdOficina);
	
	@Query(value = " select cd_moneda_presupuesto,mt_tasa,cd_guardia,tx_documento_fallecido,tx_nombre_fallecido,cd_tipo_documento,cd_contratacion_detalle,cd_ataud,cd_capilla,st_presupuesto,cd_persona_elaborador,tx_causa_anulacion,cd_presupuesto,cd_persona_titular,fe_presupuesto,cd_usuario_elaborador,cd_oficina,tx_lugar_retiro,tx_lugar_traslado "
			+ " from xsfpresupuesto  " + 
			"where " + 
			" cd_presupuesto=?1 ",nativeQuery = true)
	public Presupuestos fnBuscarPorCodigo(Long pCdPresupuesto);
	
	@Query(value = " select cd_moneda_presupuesto,mt_tasa,cd_guardia,tx_documento_fallecido,tx_nombre_fallecido,cd_tipo_documento,cd_contratacion_detalle,cd_ataud,cd_capilla,st_presupuesto,cd_persona_elaborador,tx_causa_anulacion,cd_presupuesto,cd_persona_titular,fe_presupuesto,cd_usuario_elaborador,cd_oficina,tx_lugar_retiro,tx_lugar_traslado "
			+ " from xsfpresupuesto  " + 
			"where " + 
			" tx_documento_fallecido=?1 ",nativeQuery = true)
	public List<Presupuestos> fnBuscarPorClienteFallecido(String pCdPresupuesto);
	
	@Transactional
	@Modifying
	@Query(value = "update xsfpresupuesto "
			+ "set st_presupuesto=?1, tx_causa_anulacion=?2 "
			+ "where cd_presupuesto=?3  ",nativeQuery = true)
	public void fnModificarPresupuesto(Long pStPresupuesto,String pCdCausaAnulacion,Long pCdPresupuesto);

}
