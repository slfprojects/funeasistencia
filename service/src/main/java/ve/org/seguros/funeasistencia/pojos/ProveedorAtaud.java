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
@Table(name="XSFATAUDPROVEEDOR", schema="SIRAPP")
@Getter
@Setter
@SequenceGenerator(schema = "SIRAPP",name = "XSFATAUDPROVEEDOSEQ",sequenceName = "XSFATAUDPROVEEDOR_SEQ",allocationSize = 1,initialValue = 1)
public class ProveedorAtaud {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "XSFATAUDPROVEEDOSEQ")
	@Column(name="cd_proveedor")
	private Long cdproveedor;
	@Column(name="tx_proveedor")
	private String txproveedor;
	@Column(name="cd_oficina")
	private Long cdoficina;
	@Column(name="st_proveedor")
	private Long stproveedor;
	

}
