package service.email;

import java.util.Date;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 *@author Kapitoha
 *
 */
public class EmailSession {
    private final Properties properties;
    
    /**
     * 
     * @param String host //smtp.example.com
     * @param Integer port //default 465
     * @param Protocol protocol //Protocol.SMTP
     */
    public EmailSession(String host, int port, Protocol protocol)
    {
	port = port <= 0? 465 : port;
	properties = getProperties(host, port, protocol);
    }
    
    /**
     * 
     * @param String host //smtp.example.com
     * @param Integer port //default 465
     * @param Protocol protocol //Protocol.SMTP
     */
    private Properties getProperties(String host, int port, Protocol protocol)
    {
	Properties props = new Properties();
	props.put("mail.smtp.host", host);
	props.put("mail.smtp.port", port);
	switch (protocol) {
	    case SMTPS:
	        props.put("mail.smtp.ssl.enable", true);
	        break;
	    case TLS:
	        props.put("mail.smtp.starttls.enable", true);
	        break;
	    default:
		props.put("mail.smtp.ssl.enable", true);
	}
	return props;
    }
    
    /**
     * 
     * @param String from "myaddress@mail.com"
     * @param String to "someone@mail.com"
     * @param subject
     * @param message
     * @param String username "myaddress"
     * @param password
     * @param Boolean debug True if show output
     */
    public void sendMail(final String from, 
	    final String to, 
	    String subject, 
	    String message, 
	    final String username, 
	    final char[] password, 
	    boolean debug) throws MessagingException
    {
	Authenticator authenticator = null;
	properties.put("mail.smtp.auth", true);
	authenticator = new Authenticator() {
	    private String parsePassword(char[]password)
	    {
		StringBuilder sb = new StringBuilder();
		for (char c : password)
		{
		    sb.append(c);
		}
		return sb.toString();
	    }
	    private PasswordAuthentication pa = new PasswordAuthentication(username, parsePassword(password));
	    @Override
	    public javax.mail.PasswordAuthentication getPasswordAuthentication() {
		return pa;
	    }
	};
	Session session = Session.getInstance(properties, authenticator);
	session.setDebug(debug);
	MimeMessage message1 = new MimeMessage(session);
	try {
	    message1.setFrom(new InternetAddress(from));
	    InternetAddress[] address = {new InternetAddress(to)};
	    message1.setRecipients(Message.RecipientType.TO, address);
	    message1.setSubject(subject);
	    message1.setSentDate(new Date());
	    message1.setText(message);
	    Transport.send(message1);
	}
	finally{}
    }

}
