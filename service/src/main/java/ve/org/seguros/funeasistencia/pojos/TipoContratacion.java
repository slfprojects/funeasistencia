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
@Table(schema="SIRAPP",name="xsftipocontratacion")
@Setter
@Getter
@SequenceGenerator(schema = "SIRAPP",name = "XSFTIPOCONTRATACIONSEQ",sequenceName = "XSFTIPOCONTRATACION_SEQ",allocationSize = 1,initialValue = 1)
public class TipoContratacion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "XSFTIPOCONTRATACIONSEQ")
	@Column(name="cd_tipo_contratacion")
	private Long cdtipocontratacion;
	@Column(name="tx_tipo_contratacion")
	private String txtipocontratacion;

}
