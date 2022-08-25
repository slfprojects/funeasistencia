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
@Table(schema="SIRAPP",name="xsftipopago")
@Setter
@Getter
@SequenceGenerator(schema = "SIRAPP",name = "XSFTIPOPAGOSEQ",sequenceName = "XSFTIPOPAGO_SEQ",allocationSize = 1,initialValue = 1)

public class TipoPago {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "XSFTIPOPAGOSEQ")
	@Column(name="cd_tipo_pago")
	private Long cdtipopago;
	@Column(name="tx_tipo_pago")
	private String txtipopago;
	@Column(name="cd_moneda")
	private Long cdmoneda;
	
	@Transient
	private String txmoneda;
}