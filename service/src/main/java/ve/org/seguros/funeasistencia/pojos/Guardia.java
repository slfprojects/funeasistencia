package ve.org.seguros.funeasistencia.pojos;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="xsfguardia", schema="SIRAPP")
@Getter
@Setter
@SequenceGenerator(schema = "SIRAPP",name = "XSFGUARDIASEQ",sequenceName = "XSFGUARDIA_SEQ",allocationSize = 1,initialValue = 1)
public class Guardia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "XSFGUARDIASEQ")
	@Column(name="cd_guardia")
	private Long cdguardia;
	@Column(name="cd_persona_encargada")
	private Long cdpersonaencargada;
	@Column(name="cd_oficina")
	private Long cdoficina;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fe_inicio")
	private Date feinicio;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fe_final")
	private Date fefinal;
	@Column(name="cd_usuario")
	private String cdusuario;
	@Column(name="st_guardia")
	private String stguardia;
	@Transient
	private List<GuardiaIntegrantes> guardiaIntegrantes;
	@Transient
	private String txoficina;
	@Transient
	private String txencargado;
	@Transient
	private String feinicioaux;
	@Transient
	private String fefinalaux;
	
	

}
