package ve.org.seguros.funeasistencia.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="xsfpersona", schema="SIRAPP")
@Getter
@Setter
@SequenceGenerator(schema = "SIRAPP",name = "XSFPERSONASEQ",sequenceName = "XSFPERSONA_SEQ",allocationSize = 1,initialValue = 1)
public class Persona {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "XSFPERSONASEQ")
	@Column(name="cd_persona")
	private Long cdpersona;
	@Column(name="tp_documento")
	private String tpdocumento;
	@Column(name="tx_documento")
	private String txdocumento;
	@Column(name="tx_nombres")
	private String txnombres;
	@Column(name="tp_persona")
	private Long tppersona;
	@Column(name="tx_telefono1")
	private String txtelefono1;
	@Column(name="tx_telefono2")
	private String txtelefono2;
	@Column(name="tx_correo")
	private String txcorreo;
	@Column(name="tx_direccion")
	private String txdireccion;
	@Column(name="cd_oficina")
	private Long cdoficina;
	@Column(name="st_persona")
	private Long stpersona;
	@Transient
	private String txtipopersona;
}
