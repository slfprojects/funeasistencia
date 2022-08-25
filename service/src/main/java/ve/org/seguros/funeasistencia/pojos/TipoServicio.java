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
@Table(name="xsftiposervicio", schema="SIRAPP")
@Getter
@Setter
@SequenceGenerator(schema = "SIRAPP",name = "XSFTIPOSERVICIOSEQ",sequenceName = "XSFTIPOSERVICIO_SEQ",allocationSize = 1,initialValue = 1)
public class TipoServicio {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "XSFTIPOSERVICIOSEQ")
	@Column(name="cd_tipo_servicio")
	private Long cdtiposervicio;
	@Column(name="tx_tipo_servicio")
	private String txtiposervicio;
	@Column(name="st_tipo_servicio")
	private Long sttiposervicio;
	@Column(name="in_aplica_velacion")
	private Long inaplicavelacion;
	
}
