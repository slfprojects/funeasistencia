package ve.org.seguros.funeasistencia.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ve.org.seguros.funeasistencia.pojos.Oficina;


public interface OficinaRepo extends JpaRepository<Oficina, Long> {
	
	@Query(value = "select  cd_oficina, tx_compania,tx_telefono1,tx_telefono2,tx_direccion,st_compania from xsfoficina " + 
			"where " + 
			"tx_compania like case when ?1='todos' then tx_compania else '%'||?1||'%' end order by cd_oficina ",nativeQuery = true)
	public List<Oficina> fnBuscarPorCriterio(String pTxTipoAtaud);
	@Query(value = "select  cd_oficina, tx_compania,tx_telefono1,tx_telefono2,tx_direccion,st_compania from xsfoficina  " + 
			"where " + 
			"cd_oficina=?1 ",nativeQuery = true)
	public Oficina fnConsultaPorCodigo(Long pCdTipoAtaud);
	@Query(value = "select  cd_oficina, tx_compania,tx_telefono1,tx_telefono2,tx_direccion,st_compania from xsfoficina  " 
			,nativeQuery = true)
	public  List<Oficina> fnConsultaGlobal();
}
