package ve.org.seguros.funeasistencia.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema="SIR",name="xsftipodocumento")
@Setter
@Getter
public class TipoDocumento {
	
	@Id
	@Column(name="cd_tipo_documento")
	private String cdtipodocumento;
	@Column(name="tx_tipo_documento")
	private String txtipodocumento;

}
