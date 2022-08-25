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
@Table(name="xsfmenu", schema="SIRAPP")
@Getter
@Setter
@SequenceGenerator(schema = "SIRAPP",name = "XSFMENUSSEQ",sequenceName = "XSFMENUS_SEQ",allocationSize = 1,initialValue = 1)
public class Menus {
	
	@Id
	@Column(name="cd_menu")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "XSFMENUSSEQ")
	private Long cdmenu;
	@Column(name="tx_menu")
	private String txmenu;
	@Column(name="tx_enlace")
	private String txenlace;
	@Column(name="tx_icono")
	private String txicono;
	@Column(name="cd_menu_padre")
	private Long cdmenupadre;
	@Column(name="st_menu")
	private Long stmenu;
	@Column(name="tp_menu")
	private Long tpmenu;
	@Column(name="cd_orden")
	private Long cdorden;
}
