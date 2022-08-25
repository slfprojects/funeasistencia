package ve.org.seguros.funeasistencia.repositorios;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import ve.org.seguros.funeasistencia.pojos.Usuarios;

public interface UsuariosRepo extends JpaRepository<Usuarios, Long> {
	@Query(
			value="select CD_USUARIO,TX_CLAVE,CD_COMPANIA,CD_PERSONA,CD_AUTH_MASTER,ST_CONEXION,ST_USUARIO,ST_ACCESO_WEB,ST_ACCESO_APPMOVIL from SIRAPP.XSFUSUARIOS " + 
					"where cd_auth_master!='NA' ",
			nativeQuery = true
				)
	public List<Usuarios> fnUsuariosParaEnviarNotificacionPush();
	@Transactional
	@Modifying
	@Query(
			value=" update SIRAPP.XSFUSUARIOS "
					+ " set CD_AUTH_MASTER=?1 " + 
					" where CD_USUARIO=?2 ",
			nativeQuery = true
				)
	public void fnModificarToken(String cdToken, String cdUsuario);
	
	@Query(
			value="select CD_USUARIO,"
					+ "(select usro.cd_rol from xsfusuariosrol usro where usro.cd_usuario = usua.cd_usuario) TX_CLAVE,"
					+ "CD_COMPANIA,CD_PERSONA,CD_AUTH_MASTER,ST_CONEXION,ST_USUARIO,ST_ACCESO_WEB,ST_ACCESO_APPMOVIL from SIRAPP.XSFUSUARIOS usua " + 
					"where cd_usuario=?1 ",
			nativeQuery = true
				)
	public Usuarios fnPorCodigo(String pCdUsuario);
	
	@Transactional
	@Modifying
	@Query(
			value=" update SIRAPP.XSFUSUARIOS "
					+ " set tx_clave=?1 " + 
					" where CD_USUARIO=?2 ",
			nativeQuery = true
				)
	public void fnModificarClave(String txClave, String cdUsuario);
	
	@Query(
			value="select CD_USUARIO, "
					+ "	 (select tx_correo from xsfpersona pers where pers.cd_persona=usua.cd_persona) TX_CLAVE, "
					+ "	 CD_COMPANIA,CD_PERSONA,CD_AUTH_MASTER,ST_CONEXION,ST_USUARIO,ST_ACCESO_WEB,ST_ACCESO_APPMOVIL from SIRAPP.XSFUSUARIOS usua    "
					+ "	 where cd_usuario=?1 "
					+ " and trim((select tx_correo from xsfpersona pers where pers.cd_persona=usua.cd_persona))=trim(?2)",
			nativeQuery = true
				)
	public List<Usuarios> fnPorBuscarCorreoUsuario(String pCdUsuario,String pTxCorreo);
	
	@Query(
			value="select CD_USUARIO,"
					+ "(select usro.cd_rol from xsfusuariosrol usro where usro.cd_usuario = usua.cd_usuario) TX_CLAVE,"
					+ "CD_COMPANIA,CD_PERSONA,CD_AUTH_MASTER,ST_CONEXION,ST_USUARIO,ST_ACCESO_WEB,ST_ACCESO_APPMOVIL from SIRAPP.XSFUSUARIOS usua " + 
					"where usua.CD_USUARIO like case when nvl(?1,'todos')='todos' then CD_USUARIO else '%'||?1||'%' end  ",
			nativeQuery = true
				)
	public List<Usuarios> fnBusquedaPorCriterio(String pCdUsuario);
	
	@Query(
			value="select CD_USUARIO,"
					+ "TX_CLAVE,"
					+ "CD_COMPANIA,CD_PERSONA,CD_AUTH_MASTER,ST_CONEXION,ST_USUARIO,ST_ACCESO_WEB,ST_ACCESO_APPMOVIL from SIRAPP.XSFUSUARIOS usua " + 
					"where usua.CD_USUARIO like case when nvl(?1,'todos')='todos' then CD_USUARIO else '%'||?1||'%' end  ",
			nativeQuery = true
				)
	public List<Usuarios> fnBusquedaCambioClave(String pCdUsuario);
	
	
}
