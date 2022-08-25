package ve.org.seguros.funeasistencia.repositorios;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ve.org.seguros.funeasistencia.pojos.Persona;

public interface PersonaRepo extends JpaRepository<Persona, Long> {
	@Query(value = "select  cd_persona,tp_documento,tx_documento, tx_nombres ,tp_persona, tx_telefono1,tx_telefono2,tx_correo,tx_direccion,st_persona,cd_oficina from xsfpersona " + 
			"where " + 
			"tx_documento like case when ?1='todos' then tx_documento else '%'||?1||'%' end order by cd_persona ",nativeQuery = true)
	public List<Persona> fnBuscarPorCriterio(String pTxDocumento);
	
	@Query(value = "select  cd_persona,tp_documento,tx_documento, tx_nombres ,tp_persona, tx_telefono1,tx_telefono2,tx_correo,tx_direccion,st_persona,cd_oficina from xsfpersona  " + 
			"where " + 
			"cd_persona=?1 ",nativeQuery = true)
	public Persona fnConsultaPorCodigo(Long pCdPersona);
	
	@Query(value = "select  pers.cd_persona,pers.tp_documento,pers.tx_documento, pers.tx_nombres ,pers.tp_persona, pers.tx_telefono1,pers.tx_telefono2,pers.tx_correo,pers.tx_direccion,pers.st_persona,pers.cd_oficina from xsfpersona pers,xsfguardiaintegrantes guar  " + 
			"where  pers.cd_persona=guar.cd_persona " + 
			"and guar.cd_guardia=?1 ",nativeQuery = true)
	public List<Persona> fnConsultaPorGuardia(String pCdPersona);
	
	@Query(value = "select  cd_persona,tp_documento,tx_documento, tx_nombres ,tp_persona, tx_telefono1,tx_telefono2,tx_correo,tx_direccion,st_persona,cd_oficina from xsfpersona " + 
			"where " + 
			"tx_documento=?1 ",nativeQuery = true)
	public List<Persona> fnBuscarPorCedula(String pTxDocumento);
	
	@Query(value = "select  cd_persona,tp_documento,tx_documento, tx_nombres ,tp_persona, tx_telefono1,tx_telefono2,tx_correo,tx_direccion,st_persona,cd_oficina from xsfpersona " + 
			"where " + 
			"tp_persona in (4,5) and cd_oficina=?1 ",nativeQuery = true)
	public List<Persona> fnPersonaPorCargo(String pCdOficina);
	
	@Query(value = "select  cd_persona,tp_documento,tp_documento||'-'||tx_documento tx_documento, tx_nombres ,tp_persona, tx_telefono1,tx_telefono2,tx_correo,tx_direccion,st_persona,cd_oficina from xsfpersona    "
			+ "			where    "
			+ "			tx_documento like case when nvl(?1,'todos')='todos' then tx_documento else '%'||?1||'%' end  "
			+ "			 and tx_nombres like case when nvl(?2,'todos')='todos' then tx_nombres else '%'||?2||'%' end"
			+ "    and tp_persona in (1,2)"
			+ "			 order by cd_persona ",nativeQuery = true)
	public List <Persona> fnBusquedaConsultaAdmin(String pNuDocumento,String pNombre);
	@Query(value = "select  cd_persona,tp_documento,tp_documento||'-'||tx_documento tx_documento, tx_nombres ,tp_persona, tx_telefono1,tx_telefono2,tx_correo,tx_direccion,st_persona,cd_oficina from xsfpersona    "
			+ "			where    "
			+ "			tx_documento like case when nvl(?1,'todos')='todos' then tx_documento else '%'||?1||'%' end  "
			+ "			 and tx_nombres like case when nvl(?2,'todos')='todos' then tx_nombres else '%'||?2||'%' end and cd_oficina=?3 and tp_persona not in (1,2)"
			+ "			 order by cd_persona ",nativeQuery = true)	
	public List <Persona> fnBusquedaConsultaVendedor(String pNuDocumento,String pNombre,String pCdOficina);
	
	
	@Query(value = "select  cd_persona,tp_documento,tx_documento, tx_nombres ,tp_persona, tx_telefono1,tx_telefono2,tx_correo,tx_direccion,st_persona,cd_oficina from xsfpersona per " + 
			"where " + 
			"tp_persona in (1,2) and cd_persona not in (select cd_persona from xsfusuarios) ",nativeQuery = true)
	public List<Persona> fnPorTipoPersona();
	
	
	
	
	
}
