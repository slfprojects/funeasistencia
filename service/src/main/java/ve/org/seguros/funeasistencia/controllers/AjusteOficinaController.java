package ve.org.seguros.funeasistencia.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ve.org.seguros.funeasistencia.formularios.AjusteOficinaFormulario;
import ve.org.seguros.funeasistencia.pojos.Ataudes;
import ve.org.seguros.funeasistencia.pojos.Capilla;
import ve.org.seguros.funeasistencia.pojos.Oficina;
import ve.org.seguros.funeasistencia.pojos.ProveedorAtaud;
import ve.org.seguros.funeasistencia.pojos.TipoServicio;
import ve.org.seguros.funeasistencia.pojos.TipoServicioPorOficina;
import ve.org.seguros.funeasistencia.repositorios.AtaudesRepo;
import ve.org.seguros.funeasistencia.repositorios.CapillaRepo;
import ve.org.seguros.funeasistencia.repositorios.OficinaRepo;
import ve.org.seguros.funeasistencia.repositorios.ProveedorAtaudRepo;
import ve.org.seguros.funeasistencia.repositorios.TipoServicioPorOficinaRepo;
import ve.org.seguros.funeasistencia.repositorios.TipoServicioRepo;


@Controller
@RequestMapping("front.funeasistencia")
public class AjusteOficinaController {
	@Autowired
	private OficinaRepo oficinaRepo;
	
	@Autowired
	private TipoServicioRepo tipoServicioRepo;
	
	@Autowired
	private CapillaRepo capillaRepo;
	
	@Autowired
	private AtaudesRepo ataudesRepo;
	
	@Autowired
	private AjusteOficinaFormulario ajusteOficinaFormulario;
	
	@Autowired
	private TipoServicioPorOficinaRepo tipoServicioPorOficinaRepo;
	
	@Autowired
	private ProveedorAtaudRepo proveedorAtaudRepo;
	
	@GetMapping("/ajusteOficina.indice")
    public String fnIndice(HttpServletRequest request) {
		request.setAttribute("codigoSubMenu","24");
		request.setAttribute("codigoMenu","21");
		String vCdOficina="0";
		Oficina vOficina=new Oficina();
		if(request.getSession().getAttribute("cdoficina")!=null) {
			vCdOficina=request.getSession().getAttribute("cdoficina").toString();
			vOficina=oficinaRepo.fnConsultaPorCodigo(Long.valueOf(
					vCdOficina
					));
			List <Ataudes> vAtaudes=new ArrayList<Ataudes>();
			List <Capilla> vCapillas=new ArrayList<Capilla>();
			List <TipoServicio> vServicios=new ArrayList<TipoServicio>();
			List <ProveedorAtaud> vProveedorAtaud=new ArrayList<ProveedorAtaud>();
			vAtaudes=ataudesRepo.fnPorOficina(vCdOficina);
			for(int indice=0;indice<vAtaudes.size();indice++) {
				vAtaudes.get(indice).setTxproveedor(
						proveedorAtaudRepo.
						fnConsultaCodigo(vAtaudes.get(indice).getCdproveedor()).getTxproveedor()
						);
			}
			vCapillas=capillaRepo.fnPorOficina(vCdOficina);
			vServicios=tipoServicioRepo.fFnConsultaTipoServicioOficina(
					Long.valueOf(
							vCdOficina
							)
					);
			vProveedorAtaud=proveedorAtaudRepo.fnPorOficina(vCdOficina);
			
			
			request.setAttribute("linkAgregarServicios", "/front.funeasistencia/servicioOficina.indice.agregar");
			request.setAttribute("linkAgregarAtaudes", "/front.funeasistencia/ataudesOficina.indice.agregar");
			request.setAttribute("linkAgregarCapillas", "/front.funeasistencia/capillasOficina.indice.agregar");
			request.setAttribute("linkAgregarProveedores", "/front.funeasistencia/proveedorOficina.indice.agregar");
			
			request.setAttribute("linkEliminarServicios", "/front.funeasistencia/servicioOficina.eliminar");
			request.setAttribute("linkEliminarAtaudes", "/front.funeasistencia/ataudesOficina.eliminar");
			request.setAttribute("linkEliminarCapillas", "/front.funeasistencia/capillasOficina.eliminar");
			request.setAttribute("linkEliminarProveedor", "/front.funeasistencia/proveedorOficina.eliminar");
			
			request.setAttribute("valoresOficina", vOficina);
			request.setAttribute("valoresCapillas", vCapillas);
			request.setAttribute("valoresServicios", vServicios);
			request.setAttribute("valoresAtaudes", vAtaudes);
			request.setAttribute("valoresProveedores", vProveedorAtaud);
			
			
			request.setAttribute("tituloTarjeta", "Oficina: "+vOficina.getTxcompania());
			request.setAttribute("tituloBadge1", "Procesos Principales");
			request.setAttribute("tituloBadge2", "Ajuste de Oficina");
			request.setAttribute("scripts", ajusteOficinaFormulario.fnRetornarScripts());
			
		}
		return "procesos/oficina/index";
		
    }

	@GetMapping("/ataudesOficina.indice.agregar")
    public String fnataudesOficina(HttpServletRequest request) {		
		request.setAttribute("codigoSubMenu","24");
		request.setAttribute("codigoMenu","21");
		request.setAttribute("linkVolver", "/front.funeasistencia/ajusteOficina.indice");
		request.setAttribute("linkAgregar", "/front.funeasistencia/ataudesOficina.insercion");
		request.setAttribute("tituloTarjeta", "Registro de Ataudes por Oficina");
		request.setAttribute("tituloBadge1", "Procesos Principales");
		request.setAttribute("tituloBadge2", "Ajuste Oficina / Registro");
		request.setAttribute("nombreFormulario", "formPersonas");
		if(request.getSession().getAttribute("cdoficina")!=null) {
			request.setAttribute("formularios",ajusteOficinaFormulario.fnRegistroAtaud(
					request.getSession().getAttribute("cdoficina").toString()
					));
		}
		
		request.setAttribute("scripts",ajusteOficinaFormulario.fnRetornarScripts());
        return "procesos/crud/agregar";
    }
	@PostMapping(value="ataudesOficina.insercion")
	public ResponseEntity<Ataudes> fnInsercionAtaudes(@RequestBody Ataudes pAtaudes,HttpServletRequest request ){
		Ataudes vListaRetorno=null;
		if(pAtaudes!=null) {
			pAtaudes.setStataud(1L);
			if(request.getSession().getAttribute("cdpersona")!=null) {
				pAtaudes.setCdoficina(Long.valueOf(request.getSession().getAttribute("cdoficina").toString()));
				pAtaudes.setTxdescripcion("N/A");
				pAtaudes.setStataud(1L);
			}
			
			vListaRetorno=ataudesRepo.save(pAtaudes);
		}
		return ResponseEntity.ok(vListaRetorno);
	}
	@GetMapping("/servicioOficina.indice.agregar")
    public String fnservicioOficina(HttpServletRequest request) {		
		request.setAttribute("codigoSubMenu","24");
		request.setAttribute("codigoMenu","21");
		request.setAttribute("linkVolver", "/front.funeasistencia/ajusteOficina.indice");
		request.setAttribute("linkAgregar", "/front.funeasistencia/servicioOficina.insercion");
		request.setAttribute("tituloTarjeta", "Registro de Servicios Por Oficina");
		request.setAttribute("tituloBadge1", "Procesos Principales");
		request.setAttribute("tituloBadge2", "Servicios por Oficina / Registro");
		request.setAttribute("nombreFormulario", "formPersonas");
		if(request.getSession().getAttribute("cdoficina")!=null) {
			request.setAttribute("formularios",ajusteOficinaFormulario.fnRegistroServicio(
					request.getSession().getAttribute("cdoficina").toString()
					));
		}
		
		request.setAttribute("scripts",ajusteOficinaFormulario.fnRetornarScripts());
        return "procesos/crud/agregar";
    }
	@PostMapping(value="servicioOficina.insercion")
	public ResponseEntity<TipoServicioPorOficina> fnInsercionServicio(@RequestBody TipoServicioPorOficina pServicio,HttpServletRequest request ){
		TipoServicioPorOficina vListaRetorno=null;
		if(pServicio!=null) {
			if(request.getSession().getAttribute("cdpersona")!=null) {
				pServicio.setCdoficina(Long.valueOf(request.getSession().getAttribute("cdoficina").toString()));
			}
			pServicio.setStserviciooficina(1L);
			vListaRetorno=tipoServicioPorOficinaRepo.save(pServicio);
		}
		return ResponseEntity.ok(vListaRetorno);
	}
	@GetMapping("/capillasOficina.indice.agregar")
    public String fncapillasOficina(HttpServletRequest request) {		
		request.setAttribute("codigoSubMenu","24");
		request.setAttribute("codigoMenu","21");
		request.setAttribute("linkVolver", "/front.funeasistencia/ajusteOficina.indice");
		request.setAttribute("linkAgregar", "/front.funeasistencia/capillasOficina.insercion");
		request.setAttribute("tituloTarjeta", "Registro de Capillas Por Oficina");
		request.setAttribute("tituloBadge1", "Procesos Principales");
		request.setAttribute("tituloBadge2", "Capillas por Oficina / Registro");
		request.setAttribute("nombreFormulario", "formPersonas");
		request.setAttribute("scripts", ajusteOficinaFormulario.fnRetornarScripts());
		if(request.getSession().getAttribute("cdoficina")!=null) {
			request.setAttribute("formularios",ajusteOficinaFormulario.fnRegistroCapilla(
					request.getSession().getAttribute("cdoficina").toString()
					));
		}
        return "procesos/crud/agregar";
    }
	@PostMapping(value="capillasOficina.insercion")
	public ResponseEntity<Capilla> fnInsertarCapillas(@RequestBody Capilla pPersonas, HttpServletRequest request ){
		if(request.getSession().getAttribute("cdpersona")!=null) {
			pPersonas.setCdoficina(Long.valueOf(request.getSession().getAttribute("cdoficina").toString()));
		}
		pPersonas.setStcapilla(1L);
		capillaRepo.save(pPersonas);
		return ResponseEntity.ok(pPersonas);
	}
	
	@GetMapping("/proveedorOficina.indice.agregar")
    public String fnProveedorOficina(HttpServletRequest request) {		
		request.setAttribute("codigoSubMenu","24");
		request.setAttribute("codigoMenu","21");
		request.setAttribute("linkVolver", "/front.funeasistencia/ajusteOficina.indice");
		request.setAttribute("linkAgregar", "/front.funeasistencia/proveedorOficina.insercion");
		request.setAttribute("tituloTarjeta", "Registro de Proveedores de Ataudes Por Oficina");
		request.setAttribute("tituloBadge1", "Procesos Principales");
		request.setAttribute("tituloBadge2", "Proveedores de Ataudes por Oficina / Registro");
		request.setAttribute("nombreFormulario", "formPersonas");
		if(request.getSession().getAttribute("cdoficina")!=null) {
			request.setAttribute("formularios",ajusteOficinaFormulario.fnRegistroProveedor(
					request.getSession().getAttribute("cdoficina").toString()
					));
		}
		request.setAttribute("scripts",ajusteOficinaFormulario.fnRetornarScripts());
        return "procesos/crud/agregar";
    }
	@PostMapping(value="proveedorOficina.insercion")
	public ResponseEntity<ProveedorAtaud> fnInsercionProveedor(@RequestBody ProveedorAtaud pServicio,HttpServletRequest request ){
		ProveedorAtaud vListaRetorno=null;
		if(pServicio!=null) {
			if(request.getSession().getAttribute("cdpersona")!=null) {
				pServicio.setCdoficina(Long.valueOf(request.getSession().getAttribute("cdoficina").toString()));
			}
			pServicio.setStproveedor(1L); 
			vListaRetorno=proveedorAtaudRepo.save(pServicio);
		}
		return ResponseEntity.ok(vListaRetorno);
	}
	

	@PostMapping(value="capillasOficina.eliminar")
	public ResponseEntity<Capilla> fnEliminarCapilla(@RequestBody Capilla pPersonas ){
		Capilla vPojo=capillaRepo.fnConsultaPorCodigo(pPersonas.getCdcapilla());
		if(vPojo.getStcapilla()==1L) {
			capillaRepo.fnActualizar("0",pPersonas.getCdcapilla().toString());
		}else {
			capillaRepo.fnActualizar("1",pPersonas.getCdcapilla().toString());
		}
		return ResponseEntity.ok(vPojo);
	}
	@PostMapping(value="servicioOficina.eliminar")
	public ResponseEntity<TipoServicioPorOficina> fnEliminar(@RequestBody TipoServicioPorOficina pPersonas ){
		TipoServicioPorOficina vPojo=tipoServicioPorOficinaRepo.fnConsultaPorCodigo(pPersonas.getCdtiposerviciooficina().toString());
		if(vPojo.getStserviciooficina()==1L) {
			tipoServicioPorOficinaRepo.fnActualizar("0",pPersonas.getCdtiposerviciooficina().toString());
		}else {
			tipoServicioPorOficinaRepo.fnActualizar("1",pPersonas.getCdtiposerviciooficina().toString());
		}
		return ResponseEntity.ok(vPojo);
	}

	@PostMapping(value="ataudesOficina.eliminar")
	public ResponseEntity<Ataudes> fnEliminar(@RequestBody Ataudes pPersonas ){
		Ataudes vPojo=ataudesRepo.fnConsultaPorCodigo(pPersonas.getCdataud());
		
		if(vPojo.getStataud()==1L) {
			ataudesRepo.fnActualizar("0",pPersonas.getCdataud().toString());
		}else {
			ataudesRepo.fnActualizar("1",pPersonas.getCdataud().toString());
		}
		return ResponseEntity.ok(pPersonas);
	}
	
	
	@PostMapping(value="proveedorOficina.eliminar")
	public ResponseEntity<ProveedorAtaud> fnEliminarProveedor(@RequestBody ProveedorAtaud pPersonas ){
		ProveedorAtaud vPojo=proveedorAtaudRepo.fnConsultaCodigo(pPersonas.getCdproveedor());
		
		if(vPojo.getStproveedor()==1L) {
			proveedorAtaudRepo.fnActualizar("0",pPersonas.getCdproveedor().toString());
		}else {
			proveedorAtaudRepo.fnActualizar("1",pPersonas.getCdproveedor().toString());
		}
		return ResponseEntity.ok(vPojo);
	}
	
	
}
