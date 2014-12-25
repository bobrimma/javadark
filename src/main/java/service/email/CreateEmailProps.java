package service.email;

import service.JDInstanceService;
import dao.JDInstanceDAO;
import domain.JDEmailConfigInstance;
import domain.JDInstance;

/**
 * It's a support class to insert emailProperties into DB
 *@author Kapitoha
 *
 */
public class CreateEmailProps {
    	String email = "javadark.mail@gmail.com";
    	String username ="javadark.mail";
    	String password = "javadarkpassword";
    	String host = "smtp.gmail.com";
    	private int port = 465;
    	private Protocol protocol = Protocol.SMTPS;
    	
	public void create(String email, String username, String password,
		String host, int port, Protocol protocol)
	{
	    if (email != null) this.email = email;
	    if (username != null) this.username = username;
	    if (password != null) this.password = password;
	    if (host != null) this.host = host;
	    if (port > 0) this.port = port;
	    if (protocol != null) this.protocol = protocol;
	    JDEmailConfigInstance ec = new JDEmailConfigInstance(this.email, this.username, this.password, this.host, this.port, this.protocol, "javadark");
		JDInstanceService.getInstance().saveInstance(ec);
	}
	
	public void deleteConfigs()
	{
	    JDInstanceService serv = JDInstanceService.getInstance();
	    serv.removeInstance(0, JDEmailConfigInstance.class);
	}

}
