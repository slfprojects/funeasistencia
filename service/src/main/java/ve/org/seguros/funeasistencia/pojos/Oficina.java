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
@Table(name="xsfoficina", schema="SIRAPP")
@Getter
@Setter
@SequenceGenerator(schema = "SIRAPP",name = "XSFCOMPANIASEQ",sequenceName = "XSFCOMPANIA_SEQ",allocationSize = 1,initialValue = 1)
public class Oficina {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "XSFCOMPANIASEQ")
	@Column(name="cd_oficina")
	private Long cdoficina;
	@Column(name="tx_compania")
	private String txcompania;
	@Column(name="tx_telefono1")
	private String txtelefono1;
	@Column(name="tx_telefono2")
	private String txtelefono2;
	@Column(name="tx_direccion")
	private String txdireccion;
	@Column(name="st_compania")
	private Long stcompania;
	
}
