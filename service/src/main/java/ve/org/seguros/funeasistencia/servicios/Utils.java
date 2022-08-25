package ve.org.seguros.funeasistencia.servicios;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ve.org.seguros.funeasistencia.pojos.Usuarios;
import ve.org.seguros.funeasistencia.repositorios.UsuariosRepo;

@Service
public class Utils {
	@Autowired
	private CustomSQL customSQL;
	
	@Autowired
	private UsuariosRepo usuariosRepo;
	
	@Autowired
	private CoreXCobEmails email ;
	
	public String fnConvertirSha1(String pValor) {
		String vRetorno="";
		try {
			MessageDigest vCifrado=MessageDigest.getInstance("sha1");
			byte[] vCifradoBytes=vCifrado.digest(pValor.getBytes());
			BigInteger vConversionByes=new BigInteger(1,vCifradoBytes);
			vRetorno=vConversionByes.toString(16);
			while(vRetorno.length()<32) {
				vRetorno="0"+vRetorno;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vRetorno;
	}
	public String fnArmarMenus(String pCdUsuario) {
		
		//# BParametrizar HTML

        String vAperturaMenu="<li class=\"menu-item\" id=\"&id&\">";
        String vCierreMenu="</li>";

        String vAperturaSubMenu="<ul class=\"menu-sub\">";
        String vCierreSubMenu="</ul>";

        String vPagina="<li class=\"menu-header small text-uppercase\">"+
            "<span class=\"menu-header-text\">&pagina&</span>"+
        "</li>";
        String vMenu=
        "<a href=\"javascript:void(0);\" class=\"menu-link menu-toggle\">"+
            "<i class=\"menu-icon tf-icons bx bx-dock-top\"></i>"+
           "<div data-i18n=\"&menu&\">&menu&</div>"+
        "</a>";
        String vSubMenu=
        "<li class=\"menu-item\" id=\"&id&\">"+
            "<a href=\"/&enlace&\" class=\"menu-link\">"+
                "<div data-i18n=\"&submenu&\">&submenu&</div>"+
            "</a>"+
        "</li>";
        String vMenuFinalizado="";
		try {
			
			List<Object> oMenuPadre=customSQL.fnRetornaMenuPadre(pCdUsuario);
			for(int indice=0;indice<oMenuPadre.size();indice++) {
				
				Object [] objMenuPadre=(Object[]) oMenuPadre.get(indice);
				vMenuFinalizado+=vPagina.replace("&pagina&",objMenuPadre[1].toString());
				List<Object> oMenu=customSQL.fnRetornarMenu(objMenuPadre[0].toString(),pCdUsuario);
				for(int indice2=0;indice2<oMenu.size();indice2++) {
					Object [] objMenu=(Object[]) oMenu.get(indice2);
					vMenuFinalizado+=vAperturaMenu.replace("&id&", objMenu[0].toString());
					vMenuFinalizado+=vMenu.replace("&menu&", objMenu[1].toString());
					List<Object> sSubMenu=customSQL.fnRetornarSubMenu(objMenu[0].toString(),pCdUsuario);
					vMenuFinalizado+=vAperturaSubMenu;
					for(int indice3=0;indice3<sSubMenu.size();indice3++) {
						Object [] objSubmenu=(Object[]) sSubMenu.get(indice3);
						vMenuFinalizado+=vSubMenu
								.replace("&id&", objSubmenu[0].toString())
								.replace("&enlace&", objSubmenu[2].toString())
								.replace("&submenu&", objSubmenu[1].toString());
					}
					
					vMenuFinalizado+=vCierreSubMenu;
					
				}
				
				vMenuFinalizado+=vCierreMenu;
				
				
			}
			/*if(vMenusPadre.size()>0) {
				for(int indice=0;indice<vMenusPadre.size();indice++) {
					Menus iMenuPadre= new Menus();
					iMenuPadre=vMenusPadre.get(indice);
					vMenuFinalizado+=vPagina.replace("&pagina&",iMenuPadre.getTxmenu());
					List <Menus> vMenus=new ArrayList<>();
					vMenus=menusRepo.fnRetornarMenu(iMenuPadre.getCdmenupadre());
					System.out.println(iMenuPadre.getCdmenupadre());
					for(int indice1=0;indice1<vMenus.size();indice1++) {
						
						Menus iMenu= new Menus();
						iMenu=vMenus.get(indice);
						System.out.println(iMenu.getTxmenu());
						List <Menus> vSubMenus=new ArrayList<>();
						vMenuFinalizado+=vAperturaMenu.replace("&id&", iMenu.getCdmenu().toString());
						vMenuFinalizado+=vMenu.replace("&menu&", iMenu.getTxmenu());
						vSubMenus=menusRepo.fnRetornarSubMenu(iMenu.getCdmenu(),pCdUsuario);
						vMenuFinalizado+=vAperturaSubMenu;
						for(int indice2=0;indice2<vSubMenus.size();indice2++) {
							Menus iSubMenu= new Menus();
							iSubMenu=vSubMenus.get(indice);
							System.out.println(iSubMenu.getTxmenu());
							vMenuFinalizado+=vSubMenu
									.replace("&id&", iSubMenu.getCdmenu().toString())
									.replace("&enlace&", iSubMenu.getTxenlace())
									.replace("&submenu&", iSubMenu.getTxmenu());
						}
						vMenuFinalizado+=vCierreMenu;
					}
					vMenuFinalizado+=vCierreSubMenu;
				}
			}*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vMenuFinalizado;
	}
	
	public void fnCambiarClave(String pCdUsaurio, String pTxCorreo) {
		List<Usuarios> vRetorno=usuariosRepo.fnPorBuscarCorreoUsuario(pCdUsaurio, pTxCorreo);
		if(vRetorno.size()>0) {
			String vClave="";
			String[] vArrayConjunto=new String[8];
			vArrayConjunto[0]="fun$20#";
			vArrayConjunto[1]="$fun#1";
			vArrayConjunto[2]="asist#$";
			vArrayConjunto[3]="#$fune";
			vArrayConjunto[4]="conc$#1";
			vArrayConjunto[5]="val#$a";
			vArrayConjunto[6]="a$ist#";
			vArrayConjunto[7]="funeasistencia$";
			int min=0;
			int max=7;
			int minClave=1000;
			int maxClave=9999;
			Random vRand=new Random();
			int vRandNumero= vRand.nextInt((max-min)+1)+min;
			int vRandNumeroClave= vRand.nextInt((maxClave-minClave)+1)+minClave;
			vClave=vArrayConjunto[vRandNumero]+"."+vRandNumeroClave;
			usuariosRepo.fnModificarClave(this.fnConvertirSha1(vClave),pCdUsaurio);
			String vMotivo="Cambio de Contraseña FUNEASISTENCIA.";
			String vMensaje="<img src='cid:image001' width=\"300\" height=\"45\"> <br>"
					+ "<p>Estimado Usuario de FUNEASISTENCIAS,"+"<b>"+pCdUsaurio+"</b> </p>"
		    		+ "<p>Es un Placer Saludarle,  El presente correo es para notificar que se ha cambiado su contrase&ntilde;a. <br>"
		    		+ "<p>Siendo su contrase&ntilde;a: </p> <b>"+vClave+"</b>"
		    		+ "<br><br> <p>Es un placer servirle de Apoyo.</p> <b><p> ATENCI&Oacute;N, ESTE CORREO ES PARA PROCESOS AUTOMATIZADOS, <b>(NO RESPONDER AL CORREO)</b></p></br> ";
			email.fnEnviarCorreo(pTxCorreo, vMensaje,vMotivo);
		}
	}
	public String fnGenerarClave() {
			String vClave="";
			String[] vArrayConjunto=new String[8];
			vArrayConjunto[0]="fun$20#";
			vArrayConjunto[1]="$fun#1";
			vArrayConjunto[2]="asist#$";
			vArrayConjunto[3]="#$fune";
			vArrayConjunto[4]="conc$#1";
			vArrayConjunto[5]="val#$a";
			vArrayConjunto[6]="a$ist#";
			vArrayConjunto[7]="funeasistencia$";
			int min=0;
			int max=7;
			int minClave=10000;
			int maxClave=99999;
			Random vRand=new Random();
			int vRandNumero= vRand.nextInt((max-min)+1)+min;
			int vRandNumeroClave= vRand.nextInt((maxClave-minClave)+1)+minClave;
			vClave=vArrayConjunto[vRandNumero]+"."+vRandNumeroClave;
			return vClave;
	}
		
	
}
