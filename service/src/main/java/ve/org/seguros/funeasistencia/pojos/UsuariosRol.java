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
@Table(name="xsfusuariosrol", schema="SIRAPP")
@Getter
@Setter
@SequenceGenerator(schema = "SIRAPP",name = "XSFSUARIOROLSEQ",sequenceName = "XSFSUARIOROL_SEQ",allocationSize = 1,initialValue =1)
public class UsuariosRol {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "XSFSUARIOROLSEQ")
	@Column(name="cd_rol_usuario")
	private Long cdrolusuario;
	@Column(name="cd_rol")
	private Long cdrol;
	@Column(name="cd_usuario")
	private String cdusuario;
}
