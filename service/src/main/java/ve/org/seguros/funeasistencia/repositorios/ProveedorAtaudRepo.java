package ve.org.seguros.funeasistencia.repositorios;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import ve.org.seguros.funeasistencia.pojos.ProveedorAtaud;


public interface ProveedorAtaudRepo extends JpaRepository<ProveedorAtaud, Long> {
	
	@Query(value = "select  cd_proveedor, tx_proveedor,cd_oficina,st_proveedor from XSFATAUDPROVEEDOR " 
			,nativeQuery = true)
	public List<ProveedorAtaud> fnConsultaGlobal();
	@Query(value = "select  cd_proveedor, tx_proveedor,cd_oficina,st_proveedor from XSFATAUDPROVEEDOR "
			+ " where cd_proveedor=?1" 
			,nativeQuery = true)
	public ProveedorAtaud fnConsultaCodigo(Long pCdProveedor);
	
	@Query(value = "select  cd_proveedor, tx_proveedor,cd_oficina,st_proveedor from XSFATAUDPROVEEDOR "
			+ " where cd_oficina=?1" 
			,nativeQuery = true)
	public List<ProveedorAtaud> fnPorOficina(String pCdProveedor);
	
	@Transactional
	@Modifying
	@Query(value = "update sirapp.XSFATAUDPROVEEDOR "
			+ " set st_proveedor=?1 "
			+ " where cd_proveedor=?2 " 
			,nativeQuery = true)
	public void fnActualizar(String pStProveedor ,String pCdProveedor);
}
