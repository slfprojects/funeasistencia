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
@Table(name="xsftipoataud", schema="SIRAPP")
@Getter
@Setter
@SequenceGenerator(schema = "SIRAPP",name = "xsftipoataudseq",sequenceName = "xsftipoataud_seq",allocationSize = 1,initialValue = 1)
public class TipoAtaud {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "xsftipoataudseq")
	@Column(name="cd_ataud")
	private Long cdataud;
	@Column(name="tx_tipo_ataud")
	private String txtipoataud;
	@Column(name="tx_descripcion")
	private String txdescripcion;
	@Column(name="st_tipo_ataud")
	private Long sttipoataud;
}
