package ve.org.seguros.funeasistencia.pojos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(schema = "SIR" ,name = "TASACAMBIO")
public class TasaCambio {
	
	@Column(name="version")
	private Long version;
	
	@Column(name="cd_moneda")
	private Long cdmoneda;
	@Column(name="tp_tasa")
	private String tptasa;
	@Id
	@Column(name="fe_efectiva_tasa")
	private Date feefectivatasa;
	@Column(name="mt_tasa")
	private Float mttasa;
}
