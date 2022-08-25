package ve.org.seguros.funeasistencia.repositorios;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import ve.org.seguros.funeasistencia.pojos.Ataudes;

public interface AtaudesRepo extends JpaRepository<Ataudes, Long> {
	@Query(value = "select  cd_ataud, tx_ataud,cd_oficina,st_ataud,tx_descripcion,cd_proveedor from xsfataudes " + 
			"where " + 
			"cd_oficina=?1  order by cd_ataud ",nativeQuery = true)
	public List<Ataudes> fnBuscarPorCriterio(Long pTxTipoAtaud);
	@Query(value = "select  cd_ataud, tx_ataud,cd_oficina,st_ataud,tx_descripcion,cd_proveedor from xsfataudes " + 
			"where " + 
			"cd_ataud=?1 ",nativeQuery = true)
	public Ataudes fnConsultaPorCodigo(Long pCdTipoAtaud);
	@Query(value = "select  cd_ataud, tx_ataud,cd_oficina,st_ataud,tx_descripcion,cd_proveedor from xsfataudes " + 
			"where " + 
			"cd_oficina=?1 ",nativeQuery = true)
	public List<Ataudes> fnPorOficina(String pCdOficina);
	
	@Transactional
	@Modifying
	@Query(value = "update sirapp.xsfataudes "
			+ " set st_ataud=?1 "
			+ " where cd_ataud=?2 " 
			,nativeQuery = true)
	public void fnActualizar(String pStProveedor ,String pCdProveedor);
}
