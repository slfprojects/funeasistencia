package ve.org.seguros.funeasistencia.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ve.org.seguros.funeasistencia.pojos.Menus;

@Repository
public interface MenusRepo extends JpaRepository<Menus, Long> {
	@Query(value = "select cd_menu,tx_menu,tx_icono,tx_enlace,cd_menu_padre,st_menu,tp_menu,cd_orden from xsfmenu " + 
			"where " + 
			"tx_menu like case when ?1='todos' then tx_menu else '%'||?1||'%' end order by cd_menu ",nativeQuery = true)
	public List<Menus> fnBuscarPorCriterio(String pTxTipoAtaud);
	@Query(value = "select cd_menu,tx_menu,tx_icono,tx_enlace,cd_menu_padre,st_menu,tp_menu,cd_orden from xsfmenu  " + 
			"where " + 
			"cd_menu=?1 ",nativeQuery = true)
	public Menus fnConsultaPorCodigo(Long pCdTipoAtaud);
	@Query(value = "select cd_menu,tx_menu,tx_icono,tx_enlace,cd_menu_padre,st_menu,tp_menu,cd_orden from xsfmenu  " + 
			"where " + 
			"tp_menu!=3 ",nativeQuery = true)
	public List<Menus> fnMenusPadre();
	
	
	@Query(value = "select role.cd_menu_rol cd_menu,menu.tx_menu,menu.tx_icono,menu.tx_enlace,menu.cd_menu_padre,menu.st_menu,menu.tp_menu,menu.cd_orden "
			+ " from xsfmenu menu,xsfmenurol role  " + 
			"where "
			+ "role.cd_menu=menu.cd_menu and  " + 
			"role.cd_rol=?1 ",nativeQuery = true)
	public List<Menus> fnConsultaPorRoles(Long pCdTipoAtaud);
	
	@Query(value = "select cd_menu,tx_menu,tx_icono,tx_enlace,cd_menu_padre,st_menu,tp_menu,cd_orden from xsfmenu  " + 
			"where " + 
			"tp_menu=3 ",nativeQuery = true)
	public List<Menus> fnRetornaSubMenus();

	
}
