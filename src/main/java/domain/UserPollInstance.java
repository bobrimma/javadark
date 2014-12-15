package main.java.domain;

import javax.persistence.*;

/**
 *
 *@author Kapitoha
 *
 */
@Entity
@Table(name = "User_polls")
public class UserPollInstance implements JDInstance {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
    @Column(nullable = false, unique = true, length = 11)
    private int id;
    
    @ManyToOne @JoinColumn(name = "id_user")
    private UserInstance user;
    @ManyToOne @JoinColumn(name = "id_survey")
    private SurveyInstance survey;
    @Column(name = "finished")
    private boolean isFinished;
    
    public UserPollInstance(){}
    public UserPollInstance(UserInstance user, SurveyInstance survey, boolean isFinished)
    {
	this.user = user;
	this.survey = survey;
	this.isFinished = isFinished;
    }
    public boolean isFinished()
    {
	return isFinished;
    }
    public void setFinished(boolean isFinished)
    {
	this.isFinished = isFinished;
    }
    @Override
    public int hashCode()
    {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((survey == null) ? 0 : survey.hashCode());
	result = prime * result + ((user == null) ? 0 : user.hashCode());
	return result;
    }
    @Override
    public boolean equals(Object obj)
    {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (!(obj instanceof UserPollInstance))
	    return false;
	UserPollInstance other = (UserPollInstance) obj;
	if (survey == null)
	{
	    if (other.survey != null)
		return false;
	}
	else if (!survey.equals(other.survey))
	    return false;
	if (user == null)
	{
	    if (other.user != null)
		return false;
	}
	else if (!user.equals(other.user))
	    return false;
	return true;
    }

}
