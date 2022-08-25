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
@Table(name="xsfcapilla", schema="SIRAPP")
@Getter
@Setter
@SequenceGenerator(schema = "SIRAPP",name = "XSFCAPILLASEQ",sequenceName = "XSFCAPILLA_SEQ",allocationSize = 1,initialValue = 1)
public class Capilla {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "XSFCAPILLASEQ")
	@Column(name="cd_capilla")
	private Long cdcapilla;
	@Column(name="tx_capilla")
	private String txcapilla;
	@Column(name="cd_oficina")
	private Long cdoficina;
	@Column(name="st_capilla")
	private Long stcapilla;
}
