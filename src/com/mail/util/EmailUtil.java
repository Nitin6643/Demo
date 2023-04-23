package com.mail.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtil {
	
	public static Properties initializeProps() {
		 Properties props = new Properties();  
		    props.setProperty("mail.transport.protocol", "smtp");     
		    props.setProperty("mail.host", "smtp.gmail.com");  
		    props.put("mail.smtp.auth", "true");  
		    props.put("mail.smtp.port", "465");  
		    props.put("mail.debug", "true");  
		    props.put("mail.smtp.socketFactory.port", "465");  
		    props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");  
		    props.put("mail.smtp.socketFactory.fallback", "false"); 
		    
		    return props;
		    
	}
	
	public static Session createSession(Properties props, String from, String password) {
		Session session = Session.getDefaultInstance(props,  
			    new javax.mail.Authenticator() {
			       protected PasswordAuthentication getPasswordAuthentication() {  
			       return new PasswordAuthentication(from,password);  
			   }  
			   });  
		return session;
	}
	
	public static boolean sendEmail(String from, String password, String subject, String msg, String[] to) throws Exception {
		Session session = createSession(initializeProps(), from, password);
		Transport transport = session.getTransport();  
		   InternetAddress addressFrom = new InternetAddress(from);  

		   MimeMessage message = new MimeMessage(session);  
		   message.setSender(addressFrom);  
		   message.setSubject(subject);  
		   message.setContent(msg, "text/plain");  
		   for(String toEmail: to)
			   message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));  

		   transport.connect();  
		   Transport.send(message);  
		   transport.close();
		return false;
		
	}
}
