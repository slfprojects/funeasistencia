package ve.org.seguros.funeasistencia.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="xsfusuarios", schema="SIRAPP")
@Getter
@Setter
public class Usuarios {
	
	@Id
	@Column(name="cd_usuario")
	private String cdusuario;
	@Column(name="tx_clave")
	private String txclave;
	@Column(name="cd_compania")
	private Long cdcompania;
	@Column(name="cd_persona")
	private Long cdpersona;
	@Column(name="cd_auth_master")
	private String cdauthmaster;
	@Column(name="st_conexion")
	private Long stconexion;
	@Column(name="st_usuario")
	private Long stusuario;
	@Column(name="st_acceso_web")
	private Long staccesoweb;
	@Column(name="st_acceso_appmovil")
	private Long staccesoappmovil;
	@Transient
	private Long cdrol;
	@Transient
	private String txnombre;
	@Transient
	private String txdocumento;
	@Transient
	private String txcorreo;
	@Transient
	private String txtelefono;
	@Transient
	private String txrol;
	
}
