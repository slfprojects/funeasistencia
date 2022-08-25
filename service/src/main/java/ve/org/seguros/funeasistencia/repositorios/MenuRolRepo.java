package ve.org.seguros.funeasistencia.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ve.org.seguros.funeasistencia.pojos.MenuRol;

public interface MenuRolRepo extends JpaRepository<MenuRol, Long> {
	@Query(value="SELECT CD_MENU_ROL,CD_MENU,CD_ROL FROM XSFMENUROL where cd_menu_rol=?1",nativeQuery=true)
	public MenuRol fnBusquedaDt(Long pCodigo);
	
	@Query(value="SELECT CD_MENU_ROL,CD_MENU,CD_ROL FROM XSFMENUROL where CD_ROL=?1",nativeQuery=true)
	public List<MenuRol> fnBusquedaPorCriterio(String pCodigo);
}
