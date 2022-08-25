package ve.org.seguros.funeasistencia.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import ve.org.seguros.funeasistencia.formularios.EstadisticasFormulario;
import ve.org.seguros.funeasistencia.pojos.PeticionConsulta;
import ve.org.seguros.funeasistencia.servicios.CustomSQL;


@Controller
@RequestMapping("front.funeasistencia")
public class EstadisticasController {
	
	@Autowired
	private CustomSQL customSQL;
	
	@Autowired
	private EstadisticasFormulario estadisticasFormulario;
	
	@GetMapping("estadisticas.inicio")
    public String fnInicio(Model model, HttpServletRequest request) {
		request.setAttribute("codigoSubMenu","52");
		request.setAttribute("codigoMenu","51");
		request.setAttribute("tituloTarjeta", "Módulo Estadistico");
		request.setAttribute("tituloBadge1", "Procesos Principales");
		request.setAttribute("tituloBadge2", "Estadisticas");
		request.setAttribute("formularios", estadisticasFormulario.fnConsulta() );
		request.setAttribute("scripts",estadisticasFormulario.fnRetornarScripts());
        return "estadisticas/index";
    }
	@PostMapping("estadisticas.consulta")
	public ResponseEntity<List<Object>> fnRetornarValoresParaEstadisticas(@RequestBody PeticionConsulta pPeticion){
		List <Object> vLista=customSQL.fnREtornarEstadisticas(pPeticion.getParam1(),pPeticion.getParam2());
		return ResponseEntity.ok(vLista);
	}
	@PostMapping("estadisticas.consulta.poroficina")
	public ResponseEntity<List<Object>> fnRetornarValoresParaEstadisticasPorOficina(@RequestBody PeticionConsulta pPeticion){
		List <Object> vLista=customSQL.fnREtornarEstadisticasPorOficina(pPeticion.getParam1(),pPeticion.getParam2(),pPeticion.getParam3());
		return ResponseEntity.ok(vLista);
	}
	
	
}
