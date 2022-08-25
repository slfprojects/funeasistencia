package ve.org.seguros.funeasistencia.servicios;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PayloadHttp {
	private List<Object> contenidoMensaje;
	private Long httpEstatus;
	private String httpMensajeEstatus;
	private int httpConfirmacion;

}