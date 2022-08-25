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
@Table(name="xsfmenurol", schema="SIRAPP")
@Getter
@Setter
@SequenceGenerator(schema = "SIRAPP",name = "XSFMENUROLSEQ",sequenceName = "XSFMENUROL_SEQ",allocationSize = 1,initialValue = 1)
public class MenuRol {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "XSFMENUROLSEQ")
	@Column(name="cd_menu_rol")
	private Long cdmenurol;
	@Column(name="cd_rol")
	private Long cdrol;
	@Column(name="cd_menu")
	private Long cdmenu;
	
	@Transient
	String txmenu;
	
	@Transient
	String txrol;
	
}
