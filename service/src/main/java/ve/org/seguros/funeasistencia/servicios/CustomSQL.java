package ve.org.seguros.funeasistencia.servicios;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ve.org.seguros.funeasistencia.repositorios.UsuariosRepo;

@Service
public class CustomSQL {
	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private UsuariosRepo usuariosRepo;
	
	public List<Object> fnRetornarAutenticacion(String pCdUsuario,String pClave,int pTokenValida,String pToken) {
		List <Object> vRetorno=null;
		String vBusqueda="select " +
				"(select tx_nombres from xsfpersona pers where pers.cd_persona=usua.cd_persona), "
				+ "cd_usuario,"
				+ "cd_compania, "
				+ "(select tx_compania from xsfoficina ofic where ofic.cd_oficina=usua.cd_compania ) oficina,"
				+ " (select tx_rol from xsfroles where cd_rol=(select cd_rol from xsfusuariosrol where cd_usuario=usua.cd_usuario )) rol,"
				+ " (select st_cierre_guardia from xsfroles where cd_rol=(select cd_rol from xsfusuariosrol where cd_usuario=usua.cd_usuario )) abre_guardia,"
				+ " (select " + 
				"                 case when x2.cd_usuario!=usua.cd_usuario then 0 else 1 end " + 
				"                 from xsfguardia x2 " + 
				"                 where x2.cd_guardia in (" + 
				"                    select max(cd_guardia) from xsfguardia x1 where st_guardia=1 and x1.cd_usuario=usua.cd_usuario)" + 
				"                ) posee_guardia, "
				+ "usua.cd_persona, "
				+ " (select cd_rol from xsfroles where cd_rol=(select cd_rol from xsfusuariosrol where cd_usuario=usua.cd_usuario )) cd_rol,"
				+ "(select st_cierre_guardia from xsfroles where cd_rol=(select cd_rol from xsfusuariosrol where cd_usuario=usua.cd_usuario )) cierre "+
				"from xsfusuarios usua " + 
				"where cd_usuario=? " + 
				"and tx_clave=? ";
		try {
				
				Query vQuery=entityManager.createNativeQuery(vBusqueda)
				.setParameter(1, pCdUsuario)
				.setParameter(2, pClave);
				vRetorno=vQuery.getResultList();
				if(vRetorno.size()>0) {
					if(pTokenValida==1) {
						usuariosRepo.fnModificarToken(pToken, pCdUsuario);
					}
					
				}
			
		} catch (Exception e) {
			
		}
		return vRetorno;
	}
	public List<Object> fnValidarGuardia(String pCdOficina) {
		List <Object> vRetorno=null;
		String vBusqueda=
				 "select   "
				 + "case when extract(day from sysdate-fe_inicio)>0 then 1 else case when EXTRACT(hour from sysdate-fe_inicio)<6 then 0  else 1 end end validacion,    "
				 + "(select tx_nombres from xsfpersona where cd_persona=cd_persona_encargada) tx_nombre   "
				 + "from xsfguardia   "
				 + "where cd_guardia in (   "
				 + "				        select    "
				 + "				        max(cd_guardia)   "
				 + "				        from xsfguardia   "
				 + "				        where cd_oficina=?  "
				 + "				        and st_guardia=1   "
				 + "				    ) ";
		try {
				
				Query vQuery=entityManager.createNativeQuery(vBusqueda)
				.setParameter(1, pCdOficina);
				vRetorno=vQuery.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vRetorno;
	}
	public List<Object> fnValidarUsuarioDistintoGuardia(String pCdOficina) {
		List <Object> vRetorno=null;
		String vBusqueda=
				 "select "
				+ "case when EXTRACT(hour from sysdate-fe_inicio)<6 then 0 else 1 end validacion_hora, "
				+ "cd_usuario "
				+ "from xsfguardia "
				+ "where cd_guardia in ( "
				+ "    select  "
				+ "    max(cd_guardia) "
				+ "    from xsfguardia "
				+ "    where cd_oficina=? "
				+ "    and st_guardia=1 "
				+ ")";
		try {
				
				Query vQuery=entityManager.createNativeQuery(vBusqueda)
				.setParameter(1, pCdOficina);
				vRetorno=vQuery.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vRetorno;
	}
	public List<Object> fnValidarQueExistaGuardiaPorOficina(String pCdOficina,String pCdUsuario) {
		List <Object> vRetorno=null;
		String vBusqueda=
				"select   "
						+ "count(cd_guardia) conteo,1 valor,2 valor2 "
				+ "from xsfguardia  "
				+ "where cd_oficina=? "
				+ "and cd_persona_encargada=?"
				+ "and st_guardia=1 "
				+ "and trunc(fe_inicio)=trunc(sysdate) " ;
		try {
				
				Query vQuery=entityManager.createNativeQuery(vBusqueda)
				.setParameter(1, pCdOficina)
				.setParameter(2, pCdUsuario);
				vRetorno=vQuery.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vRetorno;
	}
	public List<Object> fnRetornaMenuPadre(String pUsuario) {
		List <Object> vRetorno=null;
		String vBusqueda=
				 "select cd_menu_padre,tx_menu from ( "
				 + "select   "
				 + "cd_menu,(select tx_menu from xsfmenu xsf1 where xsf1.cd_menu=xsf2.cd_menu_padre) tx_menu ,'' tx_enlace,'' tx_icono, cd_menu_padre, 1 st_menu, 0 tp_menu, 0 cd_orden  "
				 + "			 from xsfmenu xsf2   "
				 + "			 where cd_menu in (   "
				 + "				 select   "
				 + "				    menu.cd_menu_padre     "
				 + "				 from SIRAPP.xsfusuariosrol usro,    "
				 + "				 SIRAPP.xsfmenurol mero,      "
				 + "				 SIRAPP.xsfmenu menu      "
				 + "				 where usro.CD_ROL = mero.CD_ROL      "
				 + "				 and mero.CD_ROL = usro.CD_ROL      "
				 + "				 and menu.CD_MENU = mero.CD_MENU      "
				 + "				 and usro.CD_USUARIO=? "
				 + "				 and tp_menu=3   "
				 + "                 group by cd_menu_padre "
				 + "			 )   "
				 + "			 group by cd_menu,cd_menu_padre,tx_menu,tx_enlace,tx_icono,st_menu,tp_menu,cd_orden "
				 + ") group by cd_menu_padre,tx_menu,tx_enlace,tx_icono,st_menu,tp_menu,cd_orden";
		try {
				
				Query vQuery=entityManager.createNativeQuery(vBusqueda)
				.setParameter(1, pUsuario);
				vRetorno=vQuery.getResultList();
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vRetorno;
	}
	public List<Object> fnRetornarMenu(String pCdMenuPadre,String pCdUsuario) {
		List <Object> vRetorno=null;
		String vBusqueda=
				 "select cd_menu, tx_menu , tx_enlace, tx_icono, cd_menu_padre, st_menu, tp_menu, cd_orden  "
				 + "			 from xsfmenu  "
				 + "			 where cd_menu_padre in (?) "
				 + "			 and cd_menu in ("
				 + "					select cd_menu_padre from xsfmenu menu,xsfmenurol mero, xsfusuariosrol usro where usro.cd_usuario=? "
				 + "                    and usro.cd_rol=mero.cd_rol "
				 + "                    and mero.cd_menu=menu.cd_menu "
				 					+ ") "
				 + "             and tp_menu=2 order by cd_orden";
		try {
				
				Query vQuery=entityManager.createNativeQuery(vBusqueda)
				.setParameter(1, pCdMenuPadre)
				.setParameter(2, pCdUsuario);
				
				vRetorno=vQuery.getResultList();
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vRetorno;
	}
	public List<Object> fnRetornarSubMenu(String pCdMenuPadre,String pCdUsuario) {
		List <Object> vRetorno=null;
		String vBusqueda=
				 "select menu.cd_menu, menu.tx_menu , menu.tx_enlace, menu.tx_icono, menu.cd_menu_padre, menu.st_menu, menu.tp_menu, menu.cd_orden  "
				 + "			from xsfmenu menu, xsfmenurol mero,xsfusuariosrol usro  "
				 + "			where menu.cd_menu_padre=?  "
				 + "			and usro.cd_usuario=?  "
				 + "		    and mero.cd_menu=menu.cd_menu  "
				 + "		    and usro.cd_rol=mero.cd_rol  "
				 + "		    order by cd_orden";
		try {
				
				Query vQuery=entityManager.createNativeQuery(vBusqueda)
				.setParameter(1, pCdMenuPadre)
				.setParameter(2, pCdUsuario);
				vRetorno=vQuery.getResultList();
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vRetorno;
	}
	
	public List<Object> fnREtornarEstadisticas(String pFeDesde, String pFeHasta) {
		//--(select mt_tasa from tasacambio where cd_moneda=2 and tp_tasa='C' and fe_efectiva_tasa=trunc(sysdate))
		List <Object> vRetorno=null;
		String vBusqueda=
				"select     "
				+ "nvl(cd_oficina,999)cd_oficina,   "
				+ "nvl(oficina,'<b>Total General</b>'),   "
				+ "cantidad_presupuestos,   "
				+ "cantidad_servicios,   "
				+ "nvl(mt_generado_en_ds,0),  "
				+ "nvl(mt_generado_en_bs,0)   "
				+ "from (   "
				+ "select    "
				+ "cd_oficina,   "
				+ "cd_pais,   "
				+ "sum(mt_generado_en_ds) mt_generado_en_ds, "
				+ "sum(mt_generado_en_bs) mt_generado_en_bs,  "
				+ "(select tx_compania from xsfoficina where cd_oficina=a1.cd_oficina) oficina,   "
				+ "sum(cantidad_servicios) cantidad_servicios,   "
				+ "    sum(cantidad_presupuestos)cantidad_presupuestos   "
				+ "from (         "
				+ "    select  "
				+ "    sum(mt_presupuesto_ds)mt_generado_en_ds, "
				+ "    sum(mt_presupuesto_bs)mt_generado_en_bs,     "
				+ "    sum(cantidad_servicios) cantidad_servicios,     "
				+ "    sum(cantidad_presupuestos)cantidad_presupuestos,     "
				+ "    cd_oficina ,   "
				+ "    1 cd_pais   "
				+ "    from (     "
				+ "    select     "
				+ "        cd_oficina,     "
				+ "    case when st_presupuesto in (2,4) then  "
				+ "        round(sum(prse.MT_PRESUPUESTO*pres.mt_tasa),2) "
				+ "    end MT_PRESUPUESTO_BS,  "
				+ "    case when st_presupuesto in (2,4) then  "
				+ "        round(sum(prse.MT_PRESUPUESTO),2) "
				+ "    end MT_PRESUPUESTO_DS,    "
				+ "    case when st_presupuesto in (1,2,3,4) then count(1) else 0 end cantidad_presupuestos,     "
				+ "    case when st_presupuesto in (2,4) then count(1) else 0 end cantidad_servicios,     "
				+ "    cd_moneda     "
				+ "    from xsfpresupuesto pres, xsfpresupuestoservicio prse     "
				+ "    where pres.CD_PRESUPUESTO = prse.CD_PRESUPUESTO    "
				+ "    and trunc(fe_presupuesto) between to_date(?,'yyyy-mm-dd') and to_date(?,'yyyy-mm-dd')   "
				+ "    group by pres.cd_oficina,prse.CD_MONEDA,st_presupuesto ,pres.mt_tasa   "
				+ "    )group by cd_oficina   "
				+ ")a1 group by grouping sets(cd_oficina, cd_pais)"
				+ ")a2"
				 ;
		try {
				
				Query vQuery=entityManager.createNativeQuery(vBusqueda)
						.setParameter(1, pFeDesde)
						.setParameter(2, pFeHasta);
				vRetorno=vQuery.getResultList();
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vRetorno;
	}
	public List<Object> fnREtornarEstadisticasPorOficina(String pFeDesde, String pFeHasta,String pCdOficina) {
		//--(select mt_tasa from tasacambio where cd_moneda=2 and tp_tasa='C' and fe_efectiva_tasa=trunc(sysdate))
		List <Object> vRetorno=null;
		String vBusqueda="select * from (  "
				+ "select nvl(tx_tipo_pago,'<b>Total General</b>-11') tipo_pago,  "
				+ "nvl(mt_bs,0),  "
				+ "nvl(mt_ds,0),  "
				+ "nvl(mt_presupuesto,0),  "
				+ "cantidad_presupuestos,  "
				+ "cantidad_servicios  "
				+ "from (  "
				+ "select   "
				+ "    (select tx_compania from xsfoficina where cd_oficina=a1.cd_oficina) oficina,  "
				+ "    sum(mt_bs)mt_bs,  "
				+ "    sum(mt_ds)mt_ds,  "
				+ "    sum(mt_presupuesto)mt_presupuesto,  "
				+ "    sum(cantidad_presupuestos) cantidad_presupuestos,  "
				+ "    sum(cantidad_servicios)cantidad_servicios,  "
				+ "    cd_pais,  "
				+ "    (select tx_tipo_pago from xsftipopago tipa where tipa.cd_tipo_pago=a1.cd_tipo_pago)tx_tipo_pago  "
				+ "    from(  "
				+ "    select  "
				+ "        1 cd_pais,  "
				+ "        cd_oficina,        "
				+ "    sum( prse.MT_PRESUPUESTO) MT_PRESUPUESTO,  "
				+ "     sum( prse.MT_PRESUPUESTO*mt_tasa) mt_bs,  "
				+ "    sum( prse.MT_PRESUPUESTO) mt_ds,  "
				+ "    case when st_presupuesto in (1,2,3,4) then count(1) else 0 end cantidad_presupuestos,    "
				+ "    case when st_presupuesto in (2,4) then count(1) else 0 end cantidad_servicios,    "
				+ "    cd_moneda ,  "
				+ "    cd_tipo_pago  "
				+ "    from xsfpresupuesto pres, xsfpresupuestoservicio prse    "
				+ "    where pres.CD_PRESUPUESTO = prse.CD_PRESUPUESTO    "
				+ "    and st_presupuesto in (2,4)  "
				+ "    and cd_oficina=?  "
				+ "    and trunc(fe_presupuesto) between to_date(?,'yyyy-mm-dd') and to_date(?,'yyyy-mm-dd')  "
				+ "    group by pres.cd_oficina,prse.CD_MONEDA,st_presupuesto,cd_tipo_pago ,pres.mt_tasa   "
				+ ")a1  "
				+ "group by   "
				+ "grouping sets(cd_pais,cd_oficina,cd_tipo_pago)  "
				+ ") where cd_pais is null  "
				+ "order by cantidad_servicios asc  "
				+ ")  "
				+ "union all  "
				+ "select  "
				+ "tipo_pago,  "
				+ "nvl(sum(mt_bs),0) mt_bs,  "
				+ "nvl(sum(mt_ds),0) mt_ds,  "
				+ "nvl(sum(MT_PRESUPUESTO),0)MT_PRESUPUESTO,  "
				+ "sum(cantidad_presupuestos)cantidad_presupuestos,  "
				+ "sum(cantidad_servicios)cantidad_servicios  "
				+ "from(  "
				+ "select  "
				+ "'<b>Presupuesto y Servicios</b>-11' tipo_pago,  "
				+ "sum( prse.MT_PRESUPUESTO*pres.mt_tasa)  mt_bs,  "
				+ "sum( prse.MT_PRESUPUESTO) mt_ds,  "
				+ "sum( prse.MT_PRESUPUESTO)   MT_PRESUPUESTO,  "
				+ "case when st_presupuesto in (1,2,3,4) then sum(1) else 0 end cantidad_presupuestos,  "
				+ "case when st_presupuesto in (2,4) then sum(1) else 0 end cantidad_servicios  "
				+ "from xsfpresupuesto pres, xsfpresupuestoservicio prse    "
				+ "    where pres.CD_PRESUPUESTO = prse.CD_PRESUPUESTO    "
				+ "    and st_presupuesto in (1,2,3,4)  "
				+ "    and cd_oficina=? "
				+ "    and trunc(fe_presupuesto) between to_date(?,'yyyy-mm-dd') and to_date(?,'yyyy-mm-dd')  "
				+ "    group by pres.cd_oficina,prse.CD_MONEDA,st_presupuesto,cd_tipo_pago  ,pres.mt_tasa "
				+ ")  "
				+ "group by tipo_pago";
				/*"select * from (  "
				+ "			select nvl(tx_tipo_pago,'<b>Total General</b>-11') tipo_pago,  "
				+ "			nvl(mt_bs,0),  "
				+ "			nvl(mt_ds,0),  "
				+ "			nvl(mt_presupuesto,0),  "
				+ "			cantidad_presupuestos,  "
				+ "			cantidad_servicios  "
				+ "			from (  "
				+ "			select   "
				+ "			    (select tx_compania from xsfoficina where cd_oficina=a1.cd_oficina) oficina,  "
				+ "			    sum(mt_bs)mt_bs,  "
				+ "			    sum(mt_ds)mt_ds,  "
				+ "			    sum(mt_presupuesto)mt_presupuesto,  "
				+ "			    sum(cantidad_presupuestos) cantidad_presupuestos,  "
				+ "			    sum(cantidad_servicios)cantidad_servicios,  "
				+ "			    cd_pais,  "
				+ "			    (select tx_tipo_pago from xsftipopago tipa where tipa.cd_tipo_pago=a1.cd_tipo_pago)tx_tipo_pago  "
				+ "			    from(  "
				+ "			    select  "
				+ "			     1 cd_pais,  "
				+ "			     cd_oficina,    "
				+ "			    case when prse.cd_moneda=1     "
				+ "			    then     "
				+ "					 round(sum( prse.MT_PRESUPUESTO)/(pres.mt_tasa),2)  "
				+ "			    else    "
				+ "			    sum( prse.MT_PRESUPUESTO)    "
				+ "			    end MT_PRESUPUESTO,  "
				+ "			     case when cd_moneda in (1) then sum( prse.MT_PRESUPUESTO) else 0 end mt_bs,  "
				+ "			     case when cd_moneda in (2) then sum( prse.MT_PRESUPUESTO) else 0 end mt_ds,  "
				+ "			    case when st_presupuesto in (1,2,3) then count(1) else 0 end cantidad_presupuestos,    "
				+ "			    case when st_presupuesto in (2) then count(1) else 0 end cantidad_servicios,    "
				+ "			    cd_moneda ,  "
				+ "			    cd_tipo_pago  "
				+ "			    from xsfpresupuesto pres, xsfpresupuestoservicio prse    "
				+ "			    where pres.CD_PRESUPUESTO = prse.CD_PRESUPUESTO    "
				+ "			    and st_presupuesto=2  "
				+ "			    and cd_oficina=?  "
				+ "             and trunc(fe_presupuesto) between to_date(?,'yyyy-mm-dd') and to_date(?,'yyyy-mm-dd')  "
				+ "			    group by pres.cd_oficina,prse.CD_MONEDA,st_presupuesto,cd_tipo_pago ,pres.mt_tasa   "
				+ "			)a1  "
				+ "			group by   "
				+ "			grouping sets(cd_pais,cd_oficina,cd_tipo_pago)  "
				+ "			) where cd_pais is null  "
				+ "			order by cantidad_servicios asc  "
				+ "			)  "
				+ "			union all  "
				+ "			select  "
				+ "			tipo_pago,  "
				+ "			nvl(sum(mt_bs),0) mt_bs,  "
				+ "			nvl(sum(mt_ds),0) mt_ds,  "
				+ "			nvl(sum(MT_PRESUPUESTO),0)MT_PRESUPUESTO,  "
				+ "			sum(cantidad_presupuestos)cantidad_presupuestos,  "
				+ "			sum(cantidad_servicios)cantidad_servicios  "
				+ "			from(  "
				+ "			select  "
				+ "			'<b>Presupuesto y Servicios</b>-11' tipo_pago,  "
				+ "			case when cd_moneda in (1) then sum( prse.MT_PRESUPUESTO) else 0 end mt_bs,  "
				+ "			case when cd_moneda in (2) then sum( prse.MT_PRESUPUESTO) else 0 end mt_ds,  "
				+ "			case when prse.cd_moneda=1     "
				+ "			    then     "
				+ "					 round(sum( prse.MT_PRESUPUESTO)/(pres.mt_tasa),2)  "
				+ "			    else    "
				+ "			    sum( prse.MT_PRESUPUESTO)    "
				+ "			    end MT_PRESUPUESTO,  "
				+ "			case when st_presupuesto in (1,2,3) then sum(1) else 0 end cantidad_presupuestos,  "
				+ "         case when st_presupuesto in (2) then sum(1) else 0 end cantidad_servicios  "
				+ "			from xsfpresupuesto pres, xsfpresupuestoservicio prse    "
				+ "			    where pres.CD_PRESUPUESTO = prse.CD_PRESUPUESTO    "
				+ "			    and st_presupuesto in (1,2,3)  "
				+ "			    and cd_oficina=? "
				+ "             and trunc(fe_presupuesto) between to_date(?,'yyyy-mm-dd') and to_date(?,'yyyy-mm-dd')  "
				+ "			    group by pres.cd_oficina,prse.CD_MONEDA,st_presupuesto,cd_tipo_pago  ,pres.mt_tasa "
				+ "			)  "
				+ "			group by tipo_pago"
				 ;*/
		try {
				
				Query vQuery=entityManager.createNativeQuery(vBusqueda)
						.setParameter(1, pCdOficina)
						.setParameter(2, pFeDesde)
						.setParameter(3, pFeHasta)
						.setParameter(4, pCdOficina)
						.setParameter(5, pFeDesde)
						.setParameter(6, pFeHasta);
				vRetorno=vQuery.getResultList();
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vRetorno;
	}
	public List<Object> fnRetornarServiciosPresupuestos(String pFeDesde, String pFeHasta) {
		//--(select mt_tasa from tasacambio where cd_moneda=2 and tp_tasa='C' and fe_efectiva_tasa=trunc(sysdate))
		List <Object> vRetorno=null;
		String vBusqueda=
				"select   "
				+ "case when tipo_servicio is null then 'Sub Total por '||oficina else tipo_servicio end tipo_servicio,  "
				+ "sum(presupuestos)presupuestos,  "
				+ "sum(servicios)servicios,  "
				+ "sum(mt_servicio_ds)mt_servicio_ds,  "
				+ "sum(mt_servicio_bs)mt_servicio_bs,  "
				+ "case when tipo_servicio is null then sum(presupuestos)  else 0 end presupuestos_alt,  "
				+ "case when tipo_servicio is null then sum(servicios)  else 0 end servicios_alt,  "
				+ "case when tipo_servicio is null then sum(mt_servicio_ds)  else 0 end mt_servicio_alt_ds , "
				+ "case when tipo_servicio is null then sum(mt_servicio_bs)  else 0 end mt_servicio_alt_bs  "
				+ "from (  "
				+ "select   "
				+ "sum(total_presupuestos)presupuestos,  "
				+ "sum(servicios)servicios, "
				+ "sum(mt_servicio_ds)mt_servicio_ds,  "
				+ "sum(mt_servicio_bs)mt_servicio_bs,  "
				+ "tipo_servicio,  "
				+ "oficina  "
				+ "    from(  "
				+ "        select   "
				+ "        sum(CANTIDAD_SERVICIO) total_presupuestos,  "
				+ "        case when st_presupuesto in (2,4) then sum(CANTIDAD_SERVICIO) else 0 end servicios,  "
				+ "        case when st_presupuesto in (2,4) then sum(mt_presupuesto_ds) else 0 end mt_servicio_ds,  "
				+ "        case when st_presupuesto in (2,4) then sum(mt_presupuesto_bs) else 0 end mt_servicio_bs, "
				+ "        cd_oficina,  "
				+ "        cd_tipo_servicio,  "
				+ "        tipo_servicio,  "
				+ "        oficina  "
				+ "        from (  "
				+ "            SELECT   "
				+ "            st_presupuesto,  "
				+ "            cd_oficina,  "
				+ "            PRSE.CD_TIPO_SERVICIO,  "
				+ "            COUNT(1) CANTIDAD_SERVICIO,  "
				+ "            (SELECT TX_TIPO_SERVICIO FROM XSFTIPOSERVICIO WHERE CD_TIPO_SERVICIO=PRSE.CD_TIPO_SERVICIO)tipo_servicio,  "
				+ "            round( (SUM(MT_PRESUPUESTO)*pres.mt_tasa),2)MT_PRESUPUESTO_BS,       "
				+ "            round( (SUM(MT_PRESUPUESTO)),2)MT_PRESUPUESTO_DS,      "
				+ "            (SELECT TX_COMPANIA FROM XSFOFICINA WHERE CD_OFICINA=PRES.CD_OFICINA) oficina  "
				+ "            FROM XSFPRESUPUESTO PRES, XSFPRESUPUESTOSERVICIO PRSE  "
				+ "            WHERE PRES.CD_PRESUPUESTO=PRSE.CD_PRESUPUESTO  "
				+ "            AND  trunc(pres.fe_presupuesto) BETWEEN TO_DATE( ?,'yyyy-mm-dd')  AND TO_DATE( ?,'yyyy-mm-dd')  "
				+ "            and st_presupuesto in (1,2,3,4)  "
				+ "            GROUP BY PRSE.CD_TIPO_SERVICIO,CD_OFICINA,st_presupuesto ,mt_tasa,cd_moneda "
				+ "        )  "
				+ "        group by cd_oficina,  "
				+ "        cd_tipo_servicio,  "
				+ "        tipo_servicio,  "
				+ "        oficina,st_presupuesto  "
				+ "    )group by cd_oficina,  "
				+ "    cd_tipo_servicio,  "
				+ "    tipo_servicio,  "
				+ "    oficina  "
				+ ")  "
				+ "group by grouping sets (tipo_servicio,oficina),(oficina) "
				 ;
		try {
				
				Query vQuery=entityManager.createNativeQuery(vBusqueda)
						.setParameter(1, pFeDesde)
						.setParameter(2, pFeHasta);
				vRetorno=vQuery.getResultList();
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vRetorno;
	}
	
	public List<Object> fnRetornarServicios(String pFeDesde, String pFeHasta) {
		//--(select mt_tasa from tasacambio where cd_moneda=2 and tp_tasa='C' and fe_efectiva_tasa=trunc(sysdate))
		List <Object> vRetorno=null;
		String vBusqueda="select  "
				+ "tipo_servicio,"
				+ "cantidad_servicio, "
				+ "mt_presupuesto_ds, "
				+ "mt_presupuesto_bs, "
				+ "presupuestos_alt, "
				+ "mt_presupuesto_ds_alt,"
				+ "mt_presupuesto_bs_alt  "
				+ "from (  "
				+ "    select   "
				+ "    sum(cantidad_servicio) cantidad_servicio,  "
				+ "    case when tipo_servicio is null then '<b>Sub Total por '||oficina else tipo_servicio end tipo_servicio,  "
				+ "    sum(mt_presupuesto_bs) mt_presupuesto_bs, "
				+ "    sum(mt_presupuesto_ds) mt_presupuesto_ds, "
				+ "    oficina,  "
				+ "    case when tipo_servicio is null then sum(cantidad_servicio)  else 0 end presupuestos_alt,  "
				+ "    case when tipo_servicio is null then sum(mt_presupuesto_bs)  else 0 end mt_presupuesto_bs_alt,"
				+ "    case when tipo_servicio is null then sum(mt_presupuesto_ds)  else 0 end mt_presupuesto_ds_alt  "
				+ "    from (  "
				+ "        SELECT   "
				+ "        PRSE.CD_TIPO_SERVICIO,  "
				+ "        1 cd_pais,  "
				+ "        COUNT(1) CANTIDAD_SERVICIO,  "
				+ "        (SELECT nvl(TX_TIPO_SERVICIO,1) FROM XSFTIPOSERVICIO WHERE CD_TIPO_SERVICIO=PRSE.CD_TIPO_SERVICIO) tipo_servicio,  "
				+ "        SUM(MT_PRESUPUESTO) MT_PRESUPUESTO, "
				+ "        round( (SUM(MT_PRESUPUESTO)*pres.mt_tasa),2)MT_PRESUPUESTO_BS,       "
				+ "        round( (SUM(MT_PRESUPUESTO) ),2)MT_PRESUPUESTO_DS,      "
				+ "        (SELECT TX_COMPANIA FROM XSFOFICINA WHERE CD_OFICINA=PRES.CD_OFICINA) oficina,  "
				+ "        cd_oficina  "
				+ "        FROM sirapp.XSFPRESUPUESTO PRES, sirapp.XSFPRESUPUESTOSERVICIO PRSE  "
				+ "        WHERE PRES.CD_PRESUPUESTO=PRSE.CD_PRESUPUESTO  "
				+ "        AND ST_PRESUPUESTO in (2,4)  "
				+ "        AND  trunc(pres.fe_presupuesto) BETWEEN TO_DATE( ?,'yyyy-mm-dd')  AND TO_DATE( ?,'yyyy-mm-dd')  "
				+ "        GROUP BY PRSE.CD_TIPO_SERVICIO,CD_OFICINA ,cd_moneda,mt_tasa "
				+ "    )  "
				+ "    group by grouping sets(oficina,tipo_servicio) ,(oficina)  "
				+ ")";
				/*
				"select  "
				+ "tipo_servicio,"
				+ "cantidad_servicio, "
				+ "mt_presupuesto_ds, "
				+ "mt_presupuesto_bs, "
				+ "presupuestos_alt, "
				+ "mt_presupuesto_ds_alt,"
				+ "mt_presupuesto_bs_alt  "
				+ "from (  "
				+ "	select   "
				+ "	sum(cantidad_servicio) cantidad_servicio,  "
				+ "	case when tipo_servicio is null then '<b>Sub Total por '||oficina else tipo_servicio end tipo_servicio,  "
				+ "	sum(mt_presupuesto_bs) mt_presupuesto_bs, "
				+ "	sum(mt_presupuesto_ds) mt_presupuesto_ds, "
				+ "	oficina,  "
				+ "	case when tipo_servicio is null then sum(cantidad_servicio)  else 0 end presupuestos_alt,  "
				+ "	case when tipo_servicio is null then sum(mt_presupuesto_bs)  else 0 end mt_presupuesto_bs_alt,"
				+ " case when tipo_servicio is null then sum(mt_presupuesto_ds)  else 0 end mt_presupuesto_ds_alt  "
				+ "	from (  "
				+ "		SELECT   "
				+ "		PRSE.CD_TIPO_SERVICIO,  "
				+ "		1 cd_pais,  "
				+ "		COUNT(1) CANTIDAD_SERVICIO,  "
				+ "		(SELECT nvl(TX_TIPO_SERVICIO,1) FROM XSFTIPOSERVICIO WHERE CD_TIPO_SERVICIO=PRSE.CD_TIPO_SERVICIO) tipo_servicio,  "
				+ "		SUM(MT_PRESUPUESTO) MT_PRESUPUESTO, "
				+ "                        round( (case when cd_moneda=1 then SUM(MT_PRESUPUESTO) else SUM(MT_PRESUPUESTO)*pres.mt_tasa end),2)MT_PRESUPUESTO_BS,       "
				+ "                        round( (case when cd_moneda=2 then SUM(MT_PRESUPUESTO) else SUM(MT_PRESUPUESTO)/pres.mt_tasa end),2)MT_PRESUPUESTO_DS,      "
				+ "		(SELECT TX_COMPANIA FROM XSFOFICINA WHERE CD_OFICINA=PRES.CD_OFICINA) oficina,  "
				+ "		cd_oficina  "
				+ "		FROM sirapp.XSFPRESUPUESTO PRES, sirapp.XSFPRESUPUESTOSERVICIO PRSE  "
				+ "		WHERE PRES.CD_PRESUPUESTO=PRSE.CD_PRESUPUESTO  "
				+ "		AND ST_PRESUPUESTO=2  "
				+ "		AND  trunc(pres.fe_presupuesto) BETWEEN TO_DATE( ?,'yyyy-mm-dd')  AND TO_DATE( ?,'yyyy-mm-dd')  "
				+ "		GROUP BY PRSE.CD_TIPO_SERVICIO,CD_OFICINA ,cd_moneda,mt_tasa "
				+ "	)  "
				+ "	group by grouping sets(oficina,tipo_servicio) ,(oficina)  "
				+ ")"
				 ;*/
		try {
				
				Query vQuery=entityManager.createNativeQuery(vBusqueda)
						.setParameter(1, pFeDesde)
						.setParameter(2, pFeHasta);
				vRetorno=vQuery.getResultList();
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vRetorno;
	}
	public List<Object> fnRetornarGuardiasOficina(String pFeDesde, String pFeHasta) {
		List <Object> vRetorno=null;
		String vBusqueda="select      "
				+ "    persona,       "
				+ "    guardias,       "
				+ "    cantidad_servicios,     "
				+ "    mt_servicio_ds,   "
				+ "    mt_servicio_bs,  "
				+ "    sub_total_mt_servicio_ds,   "
				+ "    sub_total_mt_servicio_bs,  "
				+ "    sub_total_guardias,       "
				+ "    sub_total_cantidad_servicios   "
				+ "        "
				+ "    from (     "
				+ "        select       "
				+ "            case when persona is null then  '<b>Sub Total por '||oficina else persona end persona,       "
				+ "            sum(guardias) guardias,       "
				+ "            sum(cantidad_servicios)cantidad_servicios,       "
				+ "            sum(mt_servicio_bs)mt_servicio_bs,      "
				+ "            sum(mt_servicio_ds)mt_servicio_ds,     "
				+ "            oficina,       "
				+ "            case when persona is null then sum(mt_servicio_bs)  else 0 end sub_total_mt_servicio_bs,      "
				+ "            case when persona is null then sum(mt_servicio_ds)  else 0 end sub_total_mt_servicio_ds,     "
				+ "            case when persona is null then sum(guardias)  else 0 end sub_total_guardias,       "
				+ "            case when persona is null then sum(cantidad_servicios)  else 0 end sub_total_cantidad_servicios       "
				+ "            "
				+ "            from (       "
				+ "                select        "
				+ "                    sum(guardias) guardias,       "
				+ "                    sum(cantidad_servicios)cantidad_servicios,       "
				+ "                    sum(mt_servicio_bs)mt_servicio_bs,      "
				+ "                    sum(mt_servicio_ds)mt_servicio_ds,     "
				+ "                    (select tx_compania from xsfoficina where cd_oficina=a1.cd_oficina)oficina,       "
				+ "                    persona       "
				+ "                from(       "
				+ "                    select        "
				+ "                        (select tx_nombres from xsfpersona where cd_persona=cd_persona_encargada) persona,       "
				+ "                        0 guardias,       "
				+ "                        sum(CANTIDAD_SERVICIO) cantidad_servicios,       "
				+ "                        sum(MT_PRESUPUESTO_BS)mt_servicio_bs,      "
				+ "                        sum(MT_PRESUPUESTO_DS)mt_servicio_ds,       "
				+ "                        cd_persona_encargada,       "
				+ "                        CD_TIPO_SERVICIO,       "
				+ "                        tipo_servicio,       "
				+ "                        cd_oficina       "
				+ "                    from(        "
				+ "                        select      "
				+ "                            (select cd_persona_encargada from xsfguardia where cd_guardia=pres.cd_guardia) cd_persona_encargada,     "
				+ "                            st_presupuesto,      "
				+ "                            cd_oficina,      "
				+ "                            PRSE.CD_TIPO_SERVICIO,      "
				+ "                            COUNT(1) CANTIDAD_SERVICIO,      "
				+ "                            (SELECT TX_TIPO_SERVICIO FROM XSFTIPOSERVICIO WHERE CD_TIPO_SERVICIO=PRSE.CD_TIPO_SERVICIO)tipo_servicio,"
				+ "                            round(SUM(MT_PRESUPUESTO)*pres.mt_tasa,2) MT_PRESUPUESTO_BS,      "
				+ "                            round( (SUM(MT_PRESUPUESTO)),2)MT_PRESUPUESTO_DS,     "
				+ "                            (SELECT TX_COMPANIA FROM XSFOFICINA WHERE CD_OFICINA=PRES.CD_OFICINA) oficina      "
				+ "                        FROM XSFPRESUPUESTO PRES, XSFPRESUPUESTOSERVICIO PRSE      "
				+ "                        WHERE PRES.CD_PRESUPUESTO=PRSE.CD_PRESUPUESTO      "
				+ "                        and st_presupuesto in (2,4)      "
				+ "                        AND  trunc(pres.fe_presupuesto) BETWEEN TO_DATE( ?,'yyyy-mm-dd')  AND TO_DATE( ?,'yyyy-mm-dd')      "
				+ "                        GROUP BY PRSE.CD_TIPO_SERVICIO,CD_OFICINA,st_presupuesto,cd_guardia,cd_moneda,pres.mt_tasa     "
				+ "                    )       "
				+ "                    group by cd_persona_encargada,cd_oficina, CD_TIPO_SERVICIO,      "
				+ "                        tipo_servicio       "
				+ "                    union all       "
				+ "                    select         "
				+ "                        (select tx_nombres from xsfpersona where cd_persona=xsfguardia.cd_persona_encargada)persona,       "
				+ "                        count(1)guardias,       "
				+ "                        0 cantidad_servicios,       "
				+ "                        0 mt_servicio_bs,       "
				+ "                        0 mt_servicio_ds,      "
				+ "                        cd_persona_encargada,       "
				+ "                        0 CD_TIPO_SERVICIO,       "
				+ "                        '' tipo_servicio,       "
				+ "                        cd_oficina       "
				+ "                    from xsfguardia       "
				+ "                        where  trunc(fe_inicio) BETWEEN TO_DATE( ?,'yyyy-mm-dd')  AND TO_DATE( ?,'yyyy-mm-dd')    "
				+ "                    group by cd_persona_encargada,cd_oficina       "
				+ "                    )a1       "
				+ "                group by cd_oficina,       "
				+ "            cd_persona_encargada,persona       "
				+ "        )       "
				+ "        group by grouping sets (oficina,persona) ,(oficina)"
				+ "    )";
				/*"select       "
				+ "case when persona like '%Sub Total%' then rownum else 0 end  cod_empresa,      "
				+ "persona,        "
				+ "guardias,        "
				+ "cantidad_servicios,      "
				+ "mt_servicio_ds,    "
				+ "mt_servicio_bs,   "
				+ "sub_total_mt_servicio_ds,    "
				+ "sub_total_mt_servicio_bs,   "
				+ "sub_total_guardias,        "
				+ "sub_total_cantidad_servicios    "
				+ "       "
				+ "from (      "
				+ "    select        "
				+ "        case when persona is null then  '<b>Sub Total por '||oficina else persona end persona,        "
				+ "        sum(guardias) guardias,        "
				+ "        sum(cantidad_servicios)cantidad_servicios,        "
				+ "        sum(mt_servicio_bs)mt_servicio_bs,       "
				+ "        sum(mt_servicio_ds)mt_servicio_ds,      "
				+ "        oficina,        "
				+ "        case when persona is null then sum(mt_servicio_bs)  else 0 end sub_total_mt_servicio_bs,       "
				+ "        case when persona is null then sum(mt_servicio_ds)  else 0 end sub_total_mt_servicio_ds,      "
				+ "        case when persona is null then sum(guardias)  else 0 end sub_total_guardias,        "
				+ "        case when persona is null then sum(cantidad_servicios)  else 0 end sub_total_cantidad_servicios        "
				+ "           "
				+ "        from (        "
				+ "            select         "
				+ "                sum(guardias) guardias,        "
				+ "                sum(cantidad_servicios)cantidad_servicios,        "
				+ "                sum(mt_servicio_bs)mt_servicio_bs,       "
				+ "                sum(mt_servicio_ds)mt_servicio_ds,      "
				+ "                (select tx_compania from xsfoficina where cd_oficina=a1.cd_oficina)oficina,        "
				+ "                persona        "
				+ "            from(        "
				+ "                select         "
				+ "                    (select tx_nombres from xsfpersona where cd_persona=cd_persona_encargada) persona,        "
				+ "                    0 guardias,        "
				+ "                    sum(CANTIDAD_SERVICIO) cantidad_servicios,        "
				+ "                    sum(MT_PRESUPUESTO_BS)mt_servicio_bs,       "
				+ "                    sum(MT_PRESUPUESTO_DS)mt_servicio_ds,        "
				+ "                    cd_persona_encargada,        "
				+ "                    CD_TIPO_SERVICIO,        "
				+ "                    tipo_servicio,        "
				+ "                    cd_oficina        "
				+ "                from(         "
				+ "                    select       "
				+ "                        (select cd_persona_encargada from xsfguardia where cd_guardia=pres.cd_guardia) cd_persona_encargada,      "
				+ "                        st_presupuesto,       "
				+ "                        cd_oficina,       "
				+ "                        PRSE.CD_TIPO_SERVICIO,       "
				+ "                        COUNT(1) CANTIDAD_SERVICIO,       "
				+ "                        (SELECT TX_TIPO_SERVICIO FROM XSFTIPOSERVICIO WHERE CD_TIPO_SERVICIO=PRSE.CD_TIPO_SERVICIO)tipo_servicio, "
				+ "                        round( (case when cd_moneda=1 then SUM(MT_PRESUPUESTO) else SUM(MT_PRESUPUESTO)*pres.mt_tasa end),2)MT_PRESUPUESTO_BS,       "
				+ "                        round( (case when cd_moneda=2 then SUM(MT_PRESUPUESTO) else SUM(MT_PRESUPUESTO)/pres.mt_tasa end),2)MT_PRESUPUESTO_DS,      "
				+ "                        (SELECT TX_COMPANIA FROM XSFOFICINA WHERE CD_OFICINA=PRES.CD_OFICINA) oficina       "
				+ "                    FROM XSFPRESUPUESTO PRES, XSFPRESUPUESTOSERVICIO PRSE       "
				+ "                    WHERE PRES.CD_PRESUPUESTO=PRSE.CD_PRESUPUESTO       "
				+ "                    and st_presupuesto in (2)       "
				+ "                    AND  trunc(pres.fe_presupuesto) BETWEEN TO_DATE( ?,'yyyy-mm-dd')  AND TO_DATE( ?,'yyyy-mm-dd')       "
				+ "                    GROUP BY PRSE.CD_TIPO_SERVICIO,CD_OFICINA,st_presupuesto,cd_guardia,cd_moneda,pres.mt_tasa      "
				+ "                )        "
				+ "                group by cd_persona_encargada,cd_oficina, CD_TIPO_SERVICIO,       "
				+ "                    tipo_servicio        "
				+ "                union all        "
				+ "                select          "
				+ "                    (select tx_nombres from xsfpersona where cd_persona=xsfguardia.cd_persona_encargada)persona,        "
				+ "                    count(1)guardias,        "
				+ "                    0 cantidad_servicios,        "
				+ "                    0 mt_servicio_bs,        "
				+ "                    0 mt_servicio_ds,       "
				+ "                    cd_persona_encargada,        "
				+ "                    0 CD_TIPO_SERVICIO,        "
				+ "                    '' tipo_servicio,        "
				+ "                    cd_oficina        "
				+ "                from xsfguardia        "
				+ "                    where  trunc(fe_inicio) BETWEEN TO_DATE( ?,'yyyy-mm-dd')  AND TO_DATE( ?,'yyyy-mm-dd')     "
				+ "                group by cd_persona_encargada,cd_oficina        "
				+ "                )a1        "
				+ "            group by cd_oficina,        "
				+ "        cd_persona_encargada,persona        "
				+ "    )        "
				+ "    group by grouping sets (oficina,persona) ,(oficina)       "
				+ ")";*/
				 
		try {
				
				Query vQuery=entityManager.createNativeQuery(vBusqueda)
					.setParameter(1, pFeDesde)
						.setParameter(2, pFeHasta)
					 .setParameter(3, pFeDesde)
						.setParameter(4, pFeHasta);
				vRetorno=vQuery.getResultList();

				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vRetorno;
	}
	
	 
}
