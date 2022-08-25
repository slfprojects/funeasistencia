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
@Table(name="xsftipopersona", schema="SIRAPP")
@Getter
@Setter
@SequenceGenerator(schema = "SIRAPP",name = "XSFTIPOPERSONASEQ",sequenceName = "XSFTIPOPERSONA_SEQ",allocationSize = 1,initialValue = 1)
public class TipoPersona {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "XSFTIPOPERSONASEQ")
	@Column(name="cd_tipo_persona")
	private Long cdtipopersona;
	@Column(name="tx_tipo_persona")
	private String txtipopersona;
	@Column(name="st_tipo_persona")
	private Long sttipopersona;
}
