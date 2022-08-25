package ve.org.seguros.funeasistencia.repositorios;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import ve.org.seguros.funeasistencia.pojos.TipoServicioPorOficina;

public interface TipoServicioPorOficinaRepo extends JpaRepository<TipoServicioPorOficina, Long> {
	
	
	@Query(value = "select  cd_tiposerviciooficina,cd_oficina, cd_tipo_servicio,mt_divisa_servicio,st_servicio_oficina  from xsftiposerviciooficina " + 
			"where " + 
			"cd_tiposerviciooficina=?1 ",nativeQuery = true)
	public TipoServicioPorOficina fnConsultaPorCodigo(String pCdOficina);
	@Query(value = "select  cd_tiposerviciooficina,cd_oficina, cd_tipo_servicio,mt_divisa_servicio,st_servicio_oficina  from xsftiposerviciooficina " + 
			"where " + 
			"cd_oficina=?1 and st_servicio_oficina=1",nativeQuery = true)
	public TipoServicioPorOficina fnConsultaGlobal(String pCdOficina);
	
	@Transactional
	@Modifying
	@Query(value = "update sirapp.xsftiposerviciooficina "
			+ " set st_servicio_oficina=?1 "
			+ " where cd_tiposerviciooficina=?2 " 
			,nativeQuery = true)
	public void fnActualizar(String pStProveedor ,String pCdProveedor);
}
