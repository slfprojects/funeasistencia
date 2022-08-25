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
@Table(schema="SIRAPP",name="xsftipocontrataciondetalle")
@Setter
@Getter
@SequenceGenerator(schema = "SIRAPP",name = "XSFTIPOCONTRATACIONDETALLSEQ",sequenceName = "XSFTIPOCONTRATACIONDETALL_SEQ",allocationSize = 1,initialValue = 1)

public class TipoContratacionDetalle {

		@Id
		@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "XSFTIPOCONTRATACIONDETALLSEQ")
		@Column(name="cd_contratacion_detalle")
		private Long cdcontrataciondetalle;
		@Column(name="CD_TIPO_CONTRATACION")
		private Long cdtipocontratacion;
		@Column(name="TX_CONTRATACION_DETALLE")
		private String txcontrataciondetalle;
		
		@Transient
		private String txtipocontratacion;
	
}
