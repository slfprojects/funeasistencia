package ve.org.seguros.funeasistencia.controllers;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import ve.org.seguros.funeasistencia.pojos.PeticionConsulta;
import ve.org.seguros.funeasistencia.servicios.CustomSQL;


@Controller
@RequestMapping("front.funeasistencia")
public class ReportesController {
	@Autowired
    protected DataSource dataSource;
	
	@Autowired
	private CustomSQL customSQL;
	
	@GetMapping("/reportes.indice.guardias")
    public String fnIndice1(HttpServletRequest request) {
		List<String> vScripts=new ArrayList<>();
		vScripts.add("objetosDt.js");
		vScripts.add("core.js");
		vScripts.add("/reportes/index.js");
		request.setAttribute("codigoSubMenu","71");
		request.setAttribute("codigoMenu","70");
		request.setAttribute("tituloTarjeta", "Reportes de Guardias");
		request.setAttribute("tituloBadge1", "Procesos Principales");
		request.setAttribute("funcionGenerar", "fnGenerarReportes1()");
		request.setAttribute("funcionMostrar", "fnGuardias()");
		request.setAttribute("tituloBadge2", "Guardias");
		request.setAttribute("scripts",vScripts);
        return "procesos/reportes/index";
    }
	@GetMapping("/reportes.indice.presupuestos1")
    public String fnIndice2(HttpServletRequest request) {
		List<String> vScripts=new ArrayList<>();
		vScripts.add("objetosDt.js");
		vScripts.add("core.js");
		vScripts.add("/reportes/index.js");
		request.setAttribute("codigoSubMenu","72");
		request.setAttribute("codigoMenu","70");
		request.setAttribute("tituloTarjeta", "Reportes de Presupuestos");
		request.setAttribute("tituloBadge1", "Procesos Principales");
		request.setAttribute("funcionGenerar", "fnGenerarReportes2()");
		request.setAttribute("funcionMostrar", "fnPresupuestos1()");
		request.setAttribute("tituloBadge2", "Presupuestos");
		request.setAttribute("scripts",vScripts);
        return "procesos/reportes/index";
    }
	@GetMapping("/reportes.indice.presupuestos2")
    public String fnIndice3(HttpServletRequest request) {
		List<String> vScripts=new ArrayList<>();
		vScripts.add("objetosDt.js");
		vScripts.add("core.js");
		vScripts.add("/reportes/index.js");
		request.setAttribute("codigoSubMenu","73");
		request.setAttribute("codigoMenu","70");
		request.setAttribute("tituloTarjeta", "Reportes de Presupuestos ");
		request.setAttribute("tituloBadge1", "Procesos Principales");
		request.setAttribute("funcionGenerar", "fnGenerarReportes3()");
		request.setAttribute("funcionMostrar", "fnPresupuestos2()");
		request.setAttribute("tituloBadge2", "Presupuestos");
		request.setAttribute("scripts",vScripts);
        return "procesos/reportes/index";
    }
	@GetMapping("reportes.presupuestos1L/{feDesde}/{feHasta}")
	public ResponseEntity<Resource> fnReporteGuardias(@PathVariable(value = "feDesde")String feDesde,
			@PathVariable(value = "feHasta")String feHasta){
			Path vRuta = null;
			String vNombreDelReporte="";
		    ByteArrayResource vArchivoRetorno = null;
		    File archivoExportado=null;
		    try {
		    	SimpleDateFormat vFormatoFecha=new SimpleDateFormat("dd-MM-yy");
		    	String vFechaExtraccion=vFormatoFecha.format(new Date());
		    	HashMap<String, Object> parametros= new HashMap<String, Object>();
		    	parametros.put("FE_A", feDesde);
		    	parametros.put("FE_B", feHasta);
		    	Connection connection = dataSource.getConnection();
		    	JasperPrint vImpresionReporte = JasperFillManager.fillReport(
		    			"Presupuestos1L.jasper", 
		    			parametros,
		    			connection);
		    	vNombreDelReporte="Presupuestos1L"+vFechaExtraccion;
	
		    	vNombreDelReporte+=".xlsx";
				SimpleXlsxReportConfiguration vConfiguracionDelReporte= new SimpleXlsxReportConfiguration();
				vConfiguracionDelReporte.setDetectCellType(true);
				vConfiguracionDelReporte.setOnePagePerSheet(true);
				vConfiguracionDelReporte.setIgnoreCellBackground(false);
				vConfiguracionDelReporte.setWhitePageBackground(true);
				File vArchivoSalida= new File(vNombreDelReporte);
				JRXlsxExporter vExportadorDeArchivo= new JRXlsxExporter();
				vExportadorDeArchivo.setExporterInput(new SimpleExporterInput(vImpresionReporte));
				vExportadorDeArchivo.setExporterOutput(new SimpleOutputStreamExporterOutput(vArchivoSalida));
				vExportadorDeArchivo.setConfiguration(vConfiguracionDelReporte);
				vExportadorDeArchivo.exportReport();
		    	archivoExportado=new File(vNombreDelReporte);
		    	connection.close();
		    	
		    	vRuta = Paths.get(archivoExportado.getAbsolutePath());
		    	vArchivoRetorno = new ByteArrayResource(Files.readAllBytes(vRuta));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		    return ResponseEntity.ok()
		    		.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename="+vNombreDelReporte )
		            .contentLength(archivoExportado.length())
		            .contentType(MediaType.APPLICATION_OCTET_STREAM)
		            .body(vArchivoRetorno);
	}
	@GetMapping("reportes.presupuestos2L/{feDesde}/{feHasta}")
	public ResponseEntity<Resource> fnReportePresupuestos2(@PathVariable(value = "feDesde")String feDesde,
			@PathVariable(value = "feHasta")String feHasta){
		Path vRuta = null;
		String vNombreDelReporte="";
	    ByteArrayResource vArchivoRetorno = null;
	    File archivoExportado=null;
	    try {
	    	SimpleDateFormat vFormatoFecha=new SimpleDateFormat("dd-MM-yy");
	    	String vFechaExtraccion=vFormatoFecha.format(new Date());
	    	HashMap<String, Object> parametros= new HashMap<String, Object>();
	    	parametros.put("FE_A", feDesde);
	    	parametros.put("FE_B", feHasta);

	    	Connection connection = dataSource.getConnection();
	    	JasperPrint vImpresionReporte = JasperFillManager.fillReport(
	    			"Presupuestos2L.jasper", 
	    			parametros,
	    			connection);
	    	vNombreDelReporte="Presupuestos2L"+vFechaExtraccion;

	    	vNombreDelReporte+=".xlsx";
			SimpleXlsxReportConfiguration vConfiguracionDelReporte= new SimpleXlsxReportConfiguration();
			vConfiguracionDelReporte.setDetectCellType(true);
			vConfiguracionDelReporte.setOnePagePerSheet(true);
			vConfiguracionDelReporte.setIgnoreCellBackground(false);
			vConfiguracionDelReporte.setWhitePageBackground(true);
			File vArchivoSalida= new File(vNombreDelReporte);
			JRXlsxExporter vExportadorDeArchivo= new JRXlsxExporter();
			vExportadorDeArchivo.setExporterInput(new SimpleExporterInput(vImpresionReporte));
			vExportadorDeArchivo.setExporterOutput(new SimpleOutputStreamExporterOutput(vArchivoSalida));
			vExportadorDeArchivo.setConfiguration(vConfiguracionDelReporte);
			vExportadorDeArchivo.exportReport();
	    	archivoExportado=new File(vNombreDelReporte);
	    	connection.close();
	    	vRuta = Paths.get(archivoExportado.getAbsolutePath());
	    	vArchivoRetorno = new ByteArrayResource(Files.readAllBytes(vRuta));
	    	
	    	//archivoExportado.deleteOnExit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	    return ResponseEntity.ok()
	    		.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename="+vNombreDelReporte )
	            .contentLength(archivoExportado.length())
	            .contentType(MediaType.APPLICATION_OCTET_STREAM)
	            .body(vArchivoRetorno);
	}
	
	@GetMapping("reportes.guardias1L/{feDesde}/{feHasta}")
	public ResponseEntity<Resource> fnReportePresupuestos1(@PathVariable(value = "feDesde")String feDesde,
			@PathVariable(value = "feHasta")String feHasta){
		Path vRuta = null;
		String vNombreDelReporte="";
	    ByteArrayResource vArchivoRetorno = null;
	    File archivoExportado=null;
	    try {
	    	SimpleDateFormat vFormatoFecha=new SimpleDateFormat("dd-MM-yy");
	    	String vFechaExtraccion=vFormatoFecha.format(new Date());
	    	HashMap<String, Object> parametros= new HashMap<String, Object>();
	    	parametros.put("FE_A", feDesde);
	    	parametros.put("FE_B", feHasta);

	    	java.sql.Connection connection = dataSource.getConnection();
	    	JasperPrint vImpresionReporte = JasperFillManager.fillReport(
	    			"Guardias1L.jasper", 
	    			parametros,
	    			connection);
	    	vNombreDelReporte="Guardias1L"+vFechaExtraccion;

	    	vNombreDelReporte+=".xlsx";
			SimpleXlsxReportConfiguration vConfiguracionDelReporte= new SimpleXlsxReportConfiguration();
			vConfiguracionDelReporte.setDetectCellType(true);
			vConfiguracionDelReporte.setOnePagePerSheet(true);
			vConfiguracionDelReporte.setIgnoreCellBackground(false);
			vConfiguracionDelReporte.setWhitePageBackground(true);
			File vArchivoSalida= new File(vNombreDelReporte);
			JRXlsxExporter vExportadorDeArchivo= new JRXlsxExporter();
			vExportadorDeArchivo.setExporterInput(new SimpleExporterInput(vImpresionReporte));
			vExportadorDeArchivo.setExporterOutput(new SimpleOutputStreamExporterOutput(vArchivoSalida));
			vExportadorDeArchivo.setConfiguration(vConfiguracionDelReporte);
			vExportadorDeArchivo.exportReport();
	    	archivoExportado=new File(vNombreDelReporte);
	    	connection.close();
	    	vRuta = Paths.get(archivoExportado.getAbsolutePath());
	    	vArchivoRetorno = new ByteArrayResource(Files.readAllBytes(vRuta));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	    return ResponseEntity.ok()
	    		.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename="+vNombreDelReporte )
	            .contentLength(archivoExportado.length())
	            .contentType(MediaType.APPLICATION_OCTET_STREAM)
	            .body(vArchivoRetorno);
		}
	
	@PostMapping("reportes.consulta.guardias")
	public ResponseEntity<List<Object>> fnRetornarConsultaGuardias(@RequestBody PeticionConsulta pPeticion){
		List <Object> vLista=null;
		try {
			vLista=customSQL.fnRetornarGuardiasOficina(pPeticion.getParam1(),pPeticion.getParam2());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.ok(vLista);
	}
	@PostMapping("reportes.consulta.presupuestos1")
	public ResponseEntity<List<Object>> fnRetornarConsultaPresupuestos1(@RequestBody PeticionConsulta pPeticion){
		List <Object> vLista=customSQL.fnRetornarServicios(pPeticion.getParam1(),pPeticion.getParam2());
		return ResponseEntity.ok(vLista);
	}
	@PostMapping("reportes.consulta.presupuestos2")
	public ResponseEntity<List<Object>> fnRetornarConsultaPresupuestos2(@RequestBody PeticionConsulta pPeticion){
		List <Object> vLista=customSQL.fnRetornarServiciosPresupuestos(pPeticion.getParam1(),pPeticion.getParam2());
		return ResponseEntity.ok(vLista);
	}
		
	
}