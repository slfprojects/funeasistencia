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
@Table(name="xsftiposerviciooficina", schema="SIRAPP")
@Getter
@Setter
@SequenceGenerator(schema = "SIRAPP",name = "XSFTIPOSERVICIOOFICINASEQ",sequenceName = "XSFTIPOSERVICIOOFICINA_SEQ",allocationSize = 1,initialValue = 1)

public class TipoServicioPorOficina {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "XSFTIPOSERVICIOOFICINASEQ")
	@Column(name="cd_tiposerviciooficina")
	private Long cdtiposerviciooficina;
	@Column(name="cd_oficina")
	private Long cdoficina;
	@Column(name="cd_tipo_servicio")
	private Long cdtiposervicio;
	@Column(name="mt_divisa_servicio")
	private Long mtdivisaservicio;
	@Column(name="st_servicio_oficina")
	private Long stserviciooficina;

}
