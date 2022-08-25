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
@Table(name="xsfataudes", schema="SIRAPP")
@Getter
@Setter
@SequenceGenerator(schema = "SIRAPP",name = "XSFATAUDOFICINASEQ",sequenceName = "XSFATAUDOFICINA_SEQ",allocationSize = 1,initialValue = 1)
public class Ataudes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "XSFATAUDOFICINASEQ")
	@Column(name="cd_ataud")
	private Long cdataud;
	
	@Column(name="tx_ataud")
	private String txataud;
	@Column(name="tx_descripcion")
	private String txdescripcion;
	@Column(name="cd_oficina")
	private Long cdoficina;
	@Column(name="cd_proveedor")
	private Long cdproveedor;
	@Column(name="st_ataud")
	private Long stataud;
	@Transient
	private String txproveedor;
}
