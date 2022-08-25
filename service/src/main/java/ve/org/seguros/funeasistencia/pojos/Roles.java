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
@Table(name="xsfroles", schema="SIRAPP")
@Getter
@Setter
@SequenceGenerator(schema = "SIRAPP",name = "XSFROLESSEQ",sequenceName = "XSFROLES_SEQ",allocationSize = 1,initialValue = 1)
public class Roles {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "XSFROLESSEQ")
	@Column(name="cd_rol")
	private Long cdrol;
	@Column(name="tx_rol")
	private String txrol;
	@Column(name="st_rol")
	private Long strol;
}
