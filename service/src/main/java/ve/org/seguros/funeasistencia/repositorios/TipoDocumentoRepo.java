package ve.org.seguros.funeasistencia.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ve.org.seguros.funeasistencia.pojos.TipoDocumento;

public interface TipoDocumentoRepo extends JpaRepository<TipoDocumento, Long> {
	
	@Query(value = "select cd_tipo_documento, tx_tipo_documento from xsftipodocumento " 
			 ,nativeQuery = true)
	public List<TipoDocumento> fnConsultaGlobal();
	
}
