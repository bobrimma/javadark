package domain;

import java.util.Arrays;

import javax.persistence.*;

import service.email.Protocol;
import utils.CryptUtils;

/**
 *
 *@author Kapitoha
 *
 */
@Entity
@Table (name = "email_configs")
public class JDEmailConfigInstance implements JDInstance{
    private static final long serialVersionUID = 1L;
    @Id
    int id;
    @Column (nullable = false)
    private byte[] email;
    @Column (nullable = false)
    private byte[] username;
    @Column (nullable = false)
    private byte[] password;
    @Column (nullable = false)
    private byte[] host;
    @Column (nullable = false)
    private byte[] port;
    @Column (nullable = false)
    private byte[] protocol;
    
    public JDEmailConfigInstance()
    {}
    public JDEmailConfigInstance(String email, String username, String password, String host, int port, Protocol protocol, String key)
    {
	this.email = CryptUtils.encode(email, key);
	this.username = CryptUtils.encode(username, key);
	this.password = CryptUtils.encode(password, key);
	this.host = CryptUtils.encode(host, key);
	this.port = CryptUtils.encode(String.valueOf(port), key);
	this.protocol = CryptUtils.encode(protocol.toString(), key);
    }
    
    public String getEmail(String pKey)
    {
	return CryptUtils.decode(email, pKey);
    }
    public String getUsername(String pKey)
    {
	return CryptUtils.decode(username, pKey);
    }
    public char[] getPassword(String pKey)
    {
	return CryptUtils.decode(password, pKey).toCharArray();
    }
    public String getHost(String pKey)
    {
	return CryptUtils.decode(host, pKey);
    }
    public int getPort(String pKey)
    {
	int p = 465;
	try
	{
	    p = new Integer(CryptUtils.decode(port, pKey));
	}
	catch (NumberFormatException e)
	{
	    System.err.println("cannot parse port. Use default 465");
	}
	return p;
    }
    
    public Protocol getProtocol(String pKey)
    {
	String str = CryptUtils.decode(protocol, pKey);
	Protocol p = null;
	for (Protocol protocol : Protocol.values())
	{
	    if (protocol.toString().equalsIgnoreCase(str))
		p = protocol;
	}
	return p;
    }
    public String toString()
    {
	String key = "javadark";
	StringBuilder sb = new StringBuilder();
	sb.append(getEmail(key)+"\n");
	sb.append(getUsername(key)+"\n");
	sb.append(getHost(key)+"\n");
	sb.append(Arrays.toString(getPassword(key))+"\n");
	sb.append(getPort(key)+"\n");
	sb.append(getProtocol(key)+"\n");
	return sb.toString();
    }

}
