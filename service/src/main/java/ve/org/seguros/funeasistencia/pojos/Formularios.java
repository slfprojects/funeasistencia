package ve.org.seguros.funeasistencia.pojos;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Formularios {

	private String name;
	private String cols;
	private String labelName;
	private String inputValue;
	private List<SelectValues> selectValues; 
	private String type;
	private String display;
	private String placeholder;
	private String disabled;
	private String focusout;
	private String onchange;
	
}
