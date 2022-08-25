package ve.org.seguros.funeasistencia.repositorios;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import ve.org.seguros.funeasistencia.pojos.Capilla;

public interface CapillaRepo extends JpaRepository<Capilla, Long> {
	@Query(value = "select  cd_capilla, tx_capilla,cd_oficina,st_capilla from xsfcapilla " + 
			"where " + 
			"cd_oficina=?1  order by tx_capilla ",nativeQuery = true)
	public List<Capilla> fnBuscarPorCriterio(Long pTxTipoAtaud);
	@Query(value = "select  cd_capilla, tx_capilla,cd_oficina,st_capilla from xsfcapilla " + 
			"where " + 
			"cd_capilla=?1 ",nativeQuery = true)
	public Capilla fnConsultaPorCodigo(Long pCdTipoAtaud);
	@Query(value = "select  cd_capilla, tx_capilla,cd_oficina,st_capilla from xsfcapilla " + 
			"where " + 
			"cd_oficina=?1 ",nativeQuery = true)
	public List<Capilla> fnPorOficina(String pCdOficina);
	@Transactional
	@Modifying
	@Query(value = "update sirapp.xsfcapilla "
			+ " set st_capilla=?1 "
			+ " where cd_capilla=?2 " 
			,nativeQuery = true)
	public void fnActualizar(String pStProveedor ,String pCdProveedor);
}
