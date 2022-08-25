package ve.org.seguros.funeasistencia.servicios;


import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
@Service
public class CoreXCobEmails {
	


	
    public void fnEnviarCorreo(String pCorreo,String pMensaje,String vMotivo){
    	
    	JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    	mailSender.setPort(25);
    	mailSender.setHost("10.10.0.31");
		MimeMessage message = mailSender.createMimeMessage();
	     
	    MimeMessageHelper helper;
	
		try {
			String[] vCorreos=new String[1];
			vCorreos[0]=pCorreo;
			helper = new MimeMessageHelper(message, true);
			helper.setFrom("core.funeasistencia@seguroslafe.com");
		    helper.setCc(vCorreos);
		    helper.setSubject(vMotivo);
		    helper.setText("",pMensaje);
		    FileSystemResource resource = new FileSystemResource(new File("tituloemail.png"));
		    System.out.println(new File("tituloemail.png").exists());
    		helper.addInline("image001", resource);
		    mailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	    
	    
    }
    
    
	
}

