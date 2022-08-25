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
@Table(schema="SIRAPP",name="xsfmoneda")
@Setter
@Getter
@SequenceGenerator(schema = "SIRAPP",name = "XSFMONEDASEQ",sequenceName = "XSFMONEDA_SEQ",allocationSize = 1,initialValue = 1)
public class TipoMoneda {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "XSFMONEDASEQ")
	@Column(name="CD_MONEDA")
	private Long cdmoneda;
	@Column(name="TX_MONEDA")
	private String txmoneda;
}
