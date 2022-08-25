package ve.org.seguros.funeasistencia.servicios;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ve.org.seguros.funeasistencia.pojos.Guardia;
import ve.org.seguros.funeasistencia.pojos.Persona;
import ve.org.seguros.funeasistencia.pojos.PresupuestoServicio;
import ve.org.seguros.funeasistencia.pojos.Presupuestos;
import ve.org.seguros.funeasistencia.pojos.Usuarios;
import ve.org.seguros.funeasistencia.repositorios.AtaudesRepo;
import ve.org.seguros.funeasistencia.repositorios.CapillaRepo;
import ve.org.seguros.funeasistencia.repositorios.GuardiasRepo;
import ve.org.seguros.funeasistencia.repositorios.OficinaRepo;
import ve.org.seguros.funeasistencia.repositorios.PersonaRepo;
import ve.org.seguros.funeasistencia.repositorios.PresupuestoServicioRepo;
import ve.org.seguros.funeasistencia.repositorios.PresupuestosRepo;
import ve.org.seguros.funeasistencia.repositorios.TipoMonedaRepo;
import ve.org.seguros.funeasistencia.repositorios.TipoPagoRepo;
import ve.org.seguros.funeasistencia.repositorios.TipoPersonaRepo;
import ve.org.seguros.funeasistencia.repositorios.TipoServicioRepo;
import ve.org.seguros.funeasistencia.repositorios.UsuariosRepo;

@Service
public class EnviarNotificacionesPush {

	@Autowired
	private PersonaRepo personaRepo;
	
	@Autowired
	private PresupuestosRepo presupuestosRepo;
	
	@Autowired
	private PresupuestoServicioRepo presupuestoServicioRepo;
	
	@Autowired
	private AtaudesRepo ataudesRepo;
	
	@Autowired
	private CapillaRepo capillaRepo;
	
	@Autowired
	private TipoServicioRepo tipoServicioRepo;
	
	@Autowired
	private UsuariosRepo usuariosRepo;
	
	@Autowired
	private  TipoMonedaRepo tipoMonedaRepo;
	
	@Autowired
	private TipoPagoRepo tipoPagoRepo;
	
	@Autowired
	private GuardiasRepo  guardiasRepo;
	
	@Autowired
	private TipoPersonaRepo tipoPersonaRepo;
	
	@Autowired
	private OficinaRepo oficinaRepo;
	
	public void fnNotificacionPushPresupuesto(Presupuestos pPresupuesto) {
		
		
		String url = "https://fcm.googleapis.com/fcm/send";
		// create an instance of RestTemplate
		List<PresupuestoServicio> vPersonas=null;
		Presupuestos vGuardia=new  Presupuestos();
		
		
		vGuardia=presupuestosRepo.fnBuscarPorCodigo(pPresupuesto.getCdpresupuesto());
		vPersonas=presupuestoServicioRepo.fnConsultaPorCodigo(pPresupuesto.getCdpresupuesto().toString());
		if(vPersonas.size()>0) {
			for(int a=0;a<vPersonas.size();a++) {
				vPersonas.get(a).setTxmoneda(
						tipoMonedaRepo.fnConsultaPorCodigo(vPersonas.get(a).getCdmoneda()).getTxmoneda()
						
				);
				vPersonas.get(a).setTxtipopago(
					tipoPagoRepo.fnConsultaConsulta(
						vPersonas.get(a).getCdtipopago()
					).getTxtipopago()
						
				);
						
			}
		}
		String vIntegrantes="";
		for(PresupuestoServicio c:vPersonas) {
			c.setTxservicio(tipoServicioRepo.fnConsultaPorCodigo(c.getCdtiposervicio()).getTxtiposervicio());
			vIntegrantes+=c.getTxmoneda()+"#"+c.getTxservicio()+"#"+c.getMtpresupuesto()+"#";
		}
		/*
		Cédula de Identidad del Solicitante--
		5.2-Nombre y Apellido del Solicitante--
		5.3-Número de Teléfono del Solicitante:--
		5.4- Cédula de Identidad del Difunto--
		5.5-Nombre del Difunto:--
		5.6-Lugar del Retiro:--
		5.7-Tipo de Ataúd: --
		5.9-Nombre de la Capilla de Velación:--
		5.11-Lugar de Traslado: --
		 5.12-Estatus del Servicio: “En Curso”--
		5.13-La fecha:--
		 5.15-Vendedor Asociado: --
		 5.16-Oficina:--
		 */
		SimpleDateFormat vFormatoFecha=new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		String Final=vIntegrantes.substring(0, vIntegrantes.length()-1);
		
		vGuardia.setTxdocumentotitular(personaRepo.fnConsultaPorCodigo(vGuardia.getCdpersonatitular()).getTpdocumento()+"-"+personaRepo.fnConsultaPorCodigo(vGuardia.getCdpersonatitular()).getTxdocumento());
		vGuardia.setTxnombretitular(personaRepo.fnConsultaPorCodigo(vGuardia.getCdpersonatitular()).getTxnombres());
		vGuardia.setTxtelefonotitular(personaRepo.fnConsultaPorCodigo(vGuardia.getCdpersonatitular()).getTxtelefono1()+"/"+personaRepo.fnConsultaPorCodigo(vGuardia.getCdpersonatitular()).getTxtelefono2());
		vGuardia.setTxataud(ataudesRepo.fnConsultaPorCodigo(vGuardia.getCdataud()).getTxataud());
		vGuardia.setTxcapilla(capillaRepo.fnConsultaPorCodigo(vGuardia.getCdcapilla()).getTxcapilla());
		vGuardia.setTxestatus("En Curso");
		vGuardia.setTxfepresupuesto(
				vFormatoFecha.format(vGuardia.getFepresupuesto())
				);
		vGuardia.setTxpersonaencargada(personaRepo.fnConsultaPorCodigo(vGuardia.getCdpersonatitular()).getTxnombres());
		vGuardia.setTxoficina(oficinaRepo.fnConsultaPorCodigo(vGuardia.getCdoficina()).getTxcompania());
		String vGuardiaString=
				vGuardia.getTxdocumentotitular()+
				"|"+vGuardia.getTxnombretitular()+
				"|"+vGuardia.getTxtelefonotitular()+
				"|"+vGuardia.getCdtipodocumento()+"-"+vGuardia.getTxdocumentofallecido()+
				"|"+vGuardia.getTxnombrefallecido()+
				"|"+vGuardia.getTxlugarretiro()+
				"|"+vGuardia.getTxataud()+
				"|"+vGuardia.getTxcapilla()+
				"|"+vGuardia.getTxlugartraslado()+
				"|"+vGuardia.getTxestatus()+
				"|"+vGuardia.getTxfepresupuesto()+
				"|"+vGuardia.getTxpersonaencargada()+
				"|"+vGuardia.getTxoficina()+
				"|"+Final;
		
		List <Usuarios> vUsuarios=usuariosRepo.fnUsuariosParaEnviarNotificacionPush();
		if(vUsuarios.size()>0) {
			RestTemplate restTemplate = new RestTemplate();
			String [] valor= new String[vUsuarios.size()];
			for(int indice=0;indice<vUsuarios.size();indice++) {
				valor[indice]=vUsuarios.get(indice).getCdauthmaster();
			}
			for(String v:valor) {
				HttpHeaders headers = new HttpHeaders();
				// set `content-type` header
				headers.setContentType(MediaType.APPLICATION_JSON);
				// set `accept` header
				headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
				headers.set("Authorization","key=AAAA1koaQ88:APA91bFZWFm4RX5kV1QwV6OqG1lvfGkU3qbD6GYti44Tye-00kR6EVQ_VNExzkSxWU4izgNMbkeUUr1qGFf2YUjDYBTQFWpcpjUeYspjKoBQCkksgIPU7v5PUbV4PuTCENe6wdT0sspT");
				// request body parameters
				HashMap<String, Object> map = new HashMap<>();
				HashMap<String, Object> valores=new HashMap<String, Object>();
				valores.put("title", "Notificación");
				valores.put("validacion", "PRESUPUESTOS");
				valores.put("message", "Se ha registrado un presupuesto en la WEBAPP de Funeasistencia.");
				valores.put("guardia",vGuardiaString );
				valores.put("type", "ACTIONS");
				map.put("to", v);
				map.put("data",valores);

				// build the request
				HttpEntity<HashMap<String, Object>> entity = new HttpEntity<>(map, headers);

				// send POST request
				ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

			}
		}
		
	}
	public void fnNotificacionPushGuardia(Guardia pGuardia) {

		
		String url = "https://fcm.googleapis.com/fcm/send";
		// create an instance of RestTemplate
		List<Persona> vPersonas=null;
		Guardia vGuardia=new  Guardia();
		vGuardia=guardiasRepo.fnBuscarPorCodigo(pGuardia.getCdguardia());
		SimpleDateFormat vFormatoFecha=new SimpleDateFormat("dd/MM/yyyy hh:mm");
		vGuardia.setFeinicioaux(
				vFormatoFecha.format(vGuardia.getFeinicio())
				);
		vGuardia.setFefinalaux(
				vFormatoFecha.format(vGuardia.getFefinal())
				);
		vGuardia.setTxencargado(personaRepo.fnConsultaPorCodigo(vGuardia.getCdpersonaencargada()).getTxnombres());
		vPersonas=personaRepo.fnConsultaPorGuardia(pGuardia.getCdguardia().toString());
		if(vPersonas.size()>0) {
			for(int a=0;a<vPersonas.size();a++) {
				vPersonas.get(a).setTxtipopersona(
						tipoPersonaRepo.fnConsultaPorCodigo(vPersonas.get(a).getTppersona()).getTxtipopersona()
					);
			}
		}
		String vIntegrantes="";
		for(Persona c:vPersonas) {
			vIntegrantes+=c.getTxtipopersona()+"#"+c.getTxnombres()+"#"+c.getTxdocumento()+"#"+c.getTxtelefono1()+"#";
		}
		vGuardia.setTxoficina(oficinaRepo.fnConsultaPorCodigo(vGuardia.getCdoficina()).getTxcompania());
		String Final=vIntegrantes.substring(0, vIntegrantes.length()-1);
		String vGuardiaString=vGuardia.getTxencargado()+"|"+vGuardia.getFeinicioaux()+"|"+vGuardia.getFefinalaux()+"|"+vGuardia.getTxoficina()+"|"+Final;
		
		
		List <Usuarios> vUsuarios=usuariosRepo.fnUsuariosParaEnviarNotificacionPush();
		if(vUsuarios.size()>0) {
			RestTemplate restTemplate = new RestTemplate();
			String [] valor= new String[vUsuarios.size()];
			for(int indice=0;indice<vUsuarios.size();indice++) {
				valor[indice]=vUsuarios.get(indice).getCdauthmaster();
			}
			for(String v:valor) {
				HttpHeaders headers = new HttpHeaders();
				// set `content-type` header
				headers.setContentType(MediaType.APPLICATION_JSON);
				// set `accept` header
				headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
				headers.set("Authorization","key=AAAA1koaQ88:APA91bFZWFm4RX5kV1QwV6OqG1lvfGkU3qbD6GYti44Tye-00kR6EVQ_VNExzkSxWU4izgNMbkeUUr1qGFf2YUjDYBTQFWpcpjUeYspjKoBQCkksgIPU7v5PUbV4PuTCENe6wdT0sspT");
				// request body parameters
				HashMap<String, Object> map = new HashMap<>();
				HashMap<String, Object> valores=new HashMap<String, Object>();
				valores.put("title", "Notificación");
				valores.put("validacion", "GUARDIA");
				valores.put("message", "Se ha registrado una Guardia en la WEBAPP de Funeasistencia.");
				valores.put("guardia",vGuardiaString );
				valores.put("type", "ACTIONS");
				map.put("to", v);
				map.put("data",valores);

				// build the request
				HttpEntity<HashMap<String, Object>> entity = new HttpEntity<>(map, headers);

				// send POST request
				ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
				System.out.println(response);
			}
		}
		
		
	}
}
