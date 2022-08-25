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
@Table(name="xsfpresupuestoservicio", schema="SIRAPP")
@Getter
@Setter
@SequenceGenerator(schema = "SIRAPP",name = "XSFPRESUPUESTOSERVICIOSEQ",sequenceName = "XSFPRESUPUESTOSERVICIO_SEQ",allocationSize = 1,initialValue = 1)
public class PresupuestoServicio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "XSFPRESUPUESTOSERVICIOSEQ")
	@Column(name="CD_SERVICIO_PRESUPUESTO")//
	private Long cdpresupuestoservicio;
	@Column(name="CD_TIPO_SERVICIO")//
	private Long cdtiposervicio;
	@Column(name="MT_PRESUPUESTO")//
	private Float mtpresupuesto;
	@Column(name="CD_PRESUPUESTO")//
	private Long cdpresupuesto;
	@Column(name="cd_tipo_pago")//
	private Long cdtipopago;
	@Column(name="CD_MONEDA")//
	private Long cdmoneda;
	@Transient
	private String txservicio;
	@Transient
	private Long cdmonedapresupuesto;
	@Transient
	private String txtipopago;
	@Transient
	private String txmoneda;
	
}
