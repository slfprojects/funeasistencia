package ve.org.seguros.funeasistencia.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ve.org.seguros.funeasistencia.pojos.TipoServicio;

public interface TipoServicioRepo extends JpaRepository<TipoServicio, Long> {
	
	@Query(value = "select  cd_tipo_servicio, tx_tipo_servicio,st_tipo_servicio, in_aplica_velacion from xsftiposervicio " + 
			"where " + 
			"tx_tipo_servicio like case when ?1='todos' then tx_tipo_servicio else '%'||?1||'%' end order by cd_tipo_servicio ",nativeQuery = true)
	public List<TipoServicio> fnBuscarPorCriterio(String pTxTipoAtaud);
	@Query(value = "select  cd_tipo_servicio, tx_tipo_servicio,st_tipo_servicio,in_aplica_velacion from xsftiposervicio " + 
			"where " + 
			"cd_tipo_servicio=?1 ",nativeQuery = true)
	public TipoServicio fnConsultaPorCodigo(Long pCdTipoAtaud);
	@Query(value = "select tiof.cd_tiposerviciooficina cd_tipo_servicio, tipo.tx_tipo_servicio, tipo.st_tipo_servicio,in_aplica_velacion from xsftiposervicio tipo, xsftiposerviciooficina tiof " + 
			"where " + 
			"tipo.cd_tipo_servicio=tiof.cd_tipo_servicio and  tiof.cd_oficina=?1 ",nativeQuery = true)
	public List<TipoServicio> fnConsultaTipoServicioOficina(Long pCdOficina);
	
	@Query(value = "select tiof.cd_tiposerviciooficina cd_tipo_servicio, tipo.tx_tipo_servicio, tiof.st_servicio_oficina st_tipo_servicio,in_aplica_velacion from xsftiposervicio tipo, xsftiposerviciooficina tiof " + 
			"where " + 
			"tipo.cd_tipo_servicio=tiof.cd_tipo_servicio and  tiof.cd_oficina=?1 ",nativeQuery = true)
	public List<TipoServicio> fFnConsultaTipoServicioOficina(Long pCdOficina);
	
	@Query(value = "select tiof.cd_tipo_servicio, tipo.tx_tipo_servicio, tipo.st_tipo_servicio,in_aplica_velacion from xsftiposervicio tipo, xsftiposerviciooficina tiof " + 
			"where " + 
			"tipo.cd_tipo_servicio=tiof.cd_tipo_servicio and  tiof.cd_oficina=?1 ",nativeQuery = true)
	public List<TipoServicio> fnConsultaServicioOficina(Long pCdOficina);
	
	@Query(value = "select tipo.cd_tipo_servicio, tipo.tx_tipo_servicio, tipo.st_tipo_servicio,in_aplica_velacion from xsftiposervicio tipo " 
			,nativeQuery = true)
	public List<TipoServicio> fnConsultaGlobal();
	
	@Query(value = "select tipo.cd_tipo_servicio, tipo.tx_tipo_servicio, tipo.st_tipo_servicio,in_aplica_velacion from xsftiposervicio tipo  where cd_tipo_servicio=?1 and in_aplica_velacion=1 " 
			,nativeQuery = true)
	public List<TipoServicio> fnConsultaUsaCapillaAtaud(String cdTipoServicio);
	
}
