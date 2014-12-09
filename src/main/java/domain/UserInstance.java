package main.java.domain;

import javax.persistence.*;

/**
 *
 *@author Kapitoha
 *
 */
@Entity
@Table (name = "Users")
public class UserInstance implements JDInstance {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(nullable = false, unique = true, length = 11)
    private int id;
    
    @Column(name = "login", unique = true, nullable = false, columnDefinition = "CHAR(32) not null")
    private String login;
    @Column(nullable = false)
    private char[]password;
    @Column(nullable = false, columnDefinition = "CHAR(32)")
    private String firstName;
    @Column(nullable = false, columnDefinition = "CHAR(32)")
    private String lastName;
    @Column(nullable = false, unique = true, columnDefinition = "CHAR(50) not null")
    private String email;
    @Column (name = "admin", columnDefinition = "bit(1) default false")
    private boolean isAdmin;
    
    public UserInstance(){}
    
    public UserInstance(String login, char[] password, String email)
    {
	this.login = login;
	this.password = password;
	this.email = email;
    }
    

    @Override
    public int hashCode()
    {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((email == null) ? 0 : email.hashCode());
	result = prime * result
		+ ((firstName == null) ? 0 : firstName.hashCode());
	result = prime * result
		+ ((lastName == null) ? 0 : lastName.hashCode());
	result = prime * result + ((login == null) ? 0 : login.hashCode());
	return result;
    }
    @Override
    public boolean equals(Object obj)
    {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (!(obj instanceof UserInstance))
	    return false;
	UserInstance other = (UserInstance) obj;
	if (email == null)
	{
	    if (other.email != null)
		return false;
	}
	else if (!email.equals(other.email))
	    return false;
	if (firstName == null)
	{
	    if (other.firstName != null)
		return false;
	}
	else if (!firstName.equals(other.firstName))
	    return false;
	if (lastName == null)
	{
	    if (other.lastName != null)
		return false;
	}
	else if (!lastName.equals(other.lastName))
	    return false;
	if (login == null)
	{
	    if (other.login != null)
		return false;
	}
	else if (!login.equals(other.login))
	    return false;
	return true;
    }
    public boolean isAdmin()
    {
	return isAdmin;
    }
    public void setAdmin(boolean isAdmin)
    {
	this.isAdmin = isAdmin;
    }
    public String getLogin()
    {
        return login;
    }
    public void setLogin(String login)
    {
        this.login = login;
    }
    public char[] getPassword()
    {
        return password;
    }
    public void setPassword(char[] password)
    {
        this.password = password;
    }
    public String getFirstName()
    {
        return firstName;
    }
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
    public String getLastName()
    {
        return lastName;
    }
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }

}
