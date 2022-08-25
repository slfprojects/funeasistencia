package ve.org.seguros.funeasistencia.repositorios;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ve.org.seguros.funeasistencia.pojos.Guardia;

public interface GuardiasRepo extends JpaRepository<Guardia, Long> {
	@Query(value = "select cd_guardia,cd_persona_encargada,cd_oficina,fe_inicio,fe_final,cd_usuario,st_guardia from xsfguardia " + 
			"where " + 
			" trunc(fe_inicio) between to_date(?1,'yyyy-mm-dd') and to_date(?2,'yyyy-mm-dd') and cd_oficina=?3 order by cd_guardia desc ",nativeQuery = true)
	public List<Guardia> fnBuscarPorCriterio(String pFechaInicio,String pFechaFin,String pCdOficina);
	
	@Query(value = "select max(cd_guardia) cd_guardia,0 cd_persona_encargada,0 cd_oficina,TRUNC(SYSDATE) fe_inicio, TRUNC(SYSDATE) fe_final,0 cd_usuario, 0 st_guardia from ( "
			+ "select cd_guardia from xsfguardia    "
			+ "			where    "
			+ "			 cd_oficina=?1"
			+ "             and st_guardia=1 "
			+ ") ",nativeQuery = true)
	public Guardia fnBuscarUltimaGuardiaOficina(String pCdOficina);
	
	@Query(value = "select cd_guardia,cd_persona_encargada,cd_oficina, to_char(fe_inicio,'yyyy-mm-dd hh:mm:ss') fe_inicio, to_char(fe_final,'yyyy-mm-dd hh:mm:ss')fe_final,cd_usuario,st_guardia from xsfguardia " + 
			"where " + 
			" cd_guardia=?1 ",nativeQuery = true)
	public Guardia fnBuscarPorCodigo(Long pCdOficina);
	@Query(value = "select cd_guardia,cd_persona_encargada,cd_oficina, to_char(fe_inicio,'yyyy-mm-dd hh:mm:ss') fe_inicio, to_char(fe_final,'yyyy-mm-dd hh:mm:ss')fe_final,cd_usuario,st_guardia from xsfguardia " + 
			"where " + 
			" cd_oficina=?1 "
			+ "and cd_guardia in (select max(cd_guardia) from xsfguardia where cd_oficina=?1) ",nativeQuery = true)
	public Guardia fnMinimaGuardiaOficina(Long pCdOficina);
}
