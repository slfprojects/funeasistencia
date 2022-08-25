package ve.org.seguros.funeasistencia.pojos;

import java.util.Date;
import java.util.List;

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
@Table(name="xsfpresupuesto", schema="SIRAPP")
@Getter
@Setter
@SequenceGenerator(schema = "SIRAPP",name = "XSFPRESUPUESTOSEQ",sequenceName = "XSFPRESUPUESTO_SEQ",allocationSize = 1,initialValue = 1)
public class Presupuestos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "XSFPRESUPUESTOSEQ")
	@Column(name="cd_presupuesto")//
	private Long cdpresupuesto;
	@Column(name="cd_persona_titular")//
	private Long cdpersonatitular;
	@Column(name="cd_persona_elaborador")//
	private Long cdpersonaelaborador;
	@Column(name="cd_oficina")//
	private Long cdoficina;
	@Column(name="cd_capilla")//
	private Long cdcapilla;
	@Column(name="cd_ataud")//
	private Long cdataud;
	@Column(name="cd_usuario_elaborador")//
	private String cdusuarioelaborador;
	@Column(name="tx_documento_fallecido")//
	private String txdocumentofallecido;
	@Column(name="tx_nombre_fallecido")//
	private String txnombrefallecido;
	@Column(name="cd_contratacion_detalle")//
	private String cdcontrataciondetalle;
	@Column(name="cd_tipo_documento")//
	private String cdtipodocumento;
	@Column(name="fe_presupuesto")//
	private Date fepresupuesto;
	@Column(name="tx_lugar_traslado")//
	private String txlugartraslado;
	@Column(name="tx_lugar_retiro")//
	private String txlugarretiro;
	@Column(name="st_presupuesto")//
	private Long stpresupuesto;
	@Column(name="tx_causa_anulacion")//
	private String txcausaanulacion;
	@Column(name="cd_guardia")//
	private Long cdguardia;
	@Column(name="mt_tasa")//
	private Float mttasa;
	@Column(name="cd_moneda_presupuesto")//
	private Long cdmonedapresupuesto;
	@Transient
	private List<PresupuestoServicio> presupuestoservicio;
	@Transient
	private String txnombretitular;
	@Transient
	private String txdocumentotitular;
	@Transient
	private String txtelefonotitular;
	@Transient
	private String txpersonaencargada;
	@Transient
	private String txfepresupuesto;
	@Transient
	private String txcapilla;
	@Transient
	private String txoficina;
	@Transient
	private String txataud;
	@Transient
	private String txcontrataciondetalle;
	@Transient
	private String txestatus;
	
}
