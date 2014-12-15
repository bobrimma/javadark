package main.java.service;

import main.java.dao.JDInstanceDAO;
import main.java.domain.JDInstance;
import main.java.domain.SurveyInstance;
import main.java.domain.UserInstance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;


/**
 * Created by Admin on 30.11.14.
 */
@Service
public class JDInstanceService implements Retrievable {
    
    public static JDInstanceService getInstance()
    {
	return new JDInstanceService();
    }

        @Autowired
        private JDInstanceDAO instanceDAO;

        @Transactional
        public void addInstance(JDInstance instance) {

            JDInstanceDAO.saveIntoDB(instance);

        }

        @Transactional
        public List<JDInstance> listInstance(String table) {

            return JDInstanceDAO.listJDInstance(table);
        }

        @Transactional
        public void removeInstance(int id, Class<JDInstance> instanceClass) {

            JDInstanceDAO.removeSurveyInstance(id, instanceClass);

        }

	@Override
	public List<UserInstance> getAllUsers()
	{
	    // TODO Auto-generated method stub
	    return null;
	}

	@Override
	public List<UserInstance> getUsers(String login, String name,
		String lastName, String email)
	{
	    // TODO Auto-generated method stub
	    return null;
	}

	@Override
	public UserInstance getUser(Integer id)
	{
	    return (UserInstance) JDInstanceDAO.retrieveFromDB(UserInstance.class, id);
	}

	@Override
	public Integer getUser(String login)
	{
	    // TODO Auto-generated method stub
	    return null;
	}

	@Override
	public List<SurveyInstance> getSurveys()
	{
	    // TODO Auto-generated method stub
	    return null;
	}

	@Override
	public List<SurveyInstance> getSurveys(String keyword)
	{
	    // TODO Auto-generated method stub
	    return null;
	}

	@Override
	public List<SurveyInstance> getSurveys(boolean isPublished)
	{
	    // TODO Auto-generated method stub
	    return null;
	}

	@Override
	public SurveyInstance getSurvey(Integer id)
	{
	    // TODO Auto-generated method stub
	    return null;
	}
    }

