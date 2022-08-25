package ve.org.seguros.funeasistencia.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="XSFGUARDIAINTEGRANTES", schema="SIRAPP")
@Getter
@Setter
@SequenceGenerator(schema = "SIRAPP",name = "XSFGUARDIAINTEGRANTESSEQ",sequenceName = "XSFGUARDIAINTEGRANTES_SEQ",allocationSize = 1,initialValue = 1)
public class GuardiaIntegrantes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "XSFGUARDIAINTEGRANTESSEQ")
	@Column(name="cd_guardia_integrante")
	private Long cd_guardia_integrante;
	@Column(name="cd_guardia")
	private Long cdguardia;
	@Column(name="cd_persona")
	private Long cdpersona;
}
