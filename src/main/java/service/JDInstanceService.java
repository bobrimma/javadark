package main.java.service;

import main.java.dao.JDInstanceDAO;
import main.java.domain.JDInstance;
import main.java.domain.SurveyInstance;
import main.java.domain.UserInstance;
import main.java.utils.HibernateUtils;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.Iterator;
import java.util.LinkedList;
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
        public void saveInstance(JDInstance instance) {

            JDInstanceDAO.saveIntoDB(instance);

        }

//        @Transactional
//        public List<JDInstance> listInstance(String table) {
//
//            return JDInstanceDAO.listJDInstance(table);
//        }

        @Transactional
        public boolean removeInstance(int id, Class<JDInstance> instanceClass) {

            try
	    {
		JDInstanceDAO.removeSurveyInstance(id, instanceClass);
	    }
	    catch (Exception e)
	    {
		return false;
	    }
            return true;
        }
        
        public boolean updateInstance(JDInstance instance)
        {
            try
	    {
		JDInstanceDAO.updateInDB(instance);
	    }
	    catch (Exception e)
	    {
		System.err.println("Cannot update instance "+instance.getClass().getName() 
			+ " cause: " + e.getLocalizedMessage());
		return false;
	    }
            return true;
        }

	@Override
	public List<UserInstance> getAllUsers()
	{
	    return JDInstanceDAO.getJDInstanceList(UserInstance.class);
	}

	@Override
	public List<UserInstance> getUsers(String login, String name,
		String lastName, String email)
	{
	    //Пока, временно побудет так. Потом попробую переделать. Очень спать охота.
	    List<UserInstance> list = new LinkedList<>(getAllUsers());
	    Iterator<UserInstance> iterator = list.iterator();
	    while (iterator.hasNext())
	    {
		UserInstance s = iterator.next();
		boolean has = false;
		if (null != login && s.getLogin().equals(login)) has = true;
		else if (null != name && s.getFirstName().equalsIgnoreCase(name)) has = true;
		else if (null != lastName && s.getLastName().equalsIgnoreCase(lastName)) has = true;
		else if (null != email && s.getEmail().equalsIgnoreCase(email)) has = true;
		if (!has)
		    iterator.remove();
	    }
	    return list;
	}

	@Override
	public UserInstance getUser(Integer id)
	{
	    return (UserInstance) JDInstanceDAO.retrieveFromDB(UserInstance.class, id);
	}

	@Override
	public Integer getUser(String login)
	{
	    String query = "Select u FROM UserInstance u where u.login=:login";
	    Session session = null;
	    Integer rez = null;
	    UserInstance user = null;
	    try
	    {
		session = HibernateUtils.getSessionFactory().openSession();
		Query que = session.createQuery(query);
		que.setString("login", login);
		user = (UserInstance) que.uniqueResult();
		rez = user.getId();
	    }
	    catch (HibernateException e)
	    {
		System.err.println("Cannot get user's Id cause: " + e.getLocalizedMessage());
	    }
	    finally
	    {
		if (null != session && session.isOpen())
		{
		    session.close();
		}
	    }
	    return rez;
	}

	@Override
	public List<SurveyInstance> getSurveys()
	{
	    return JDInstanceDAO.getJDInstanceList(SurveyInstance.class);
	}

	@Override
	public List<SurveyInstance> getSurveys(String keyword)
	{
	    List<SurveyInstance> list = new LinkedList<>(getSurveys());
	    Iterator<SurveyInstance> iterator = list.iterator();
	    while (iterator.hasNext())
	    {
		SurveyInstance s = iterator.next();
		if (!s.getName().toLowerCase().contains(keyword))
		    iterator.remove();
	    }
	    return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SurveyInstance> getSurveys(boolean isPublished)
	{
	    String query = "Select u FROM " +SurveyInstance.class.getSimpleName()+" u WHERE u.isPublished = " + String.valueOf(isPublished);
	    Session session = HibernateUtils.getSessionFactory().openSession();
            Query que = session.createQuery(query);
            List<SurveyInstance> list = que.list();
            session.close();
	    return list;
	}

	@Override
	public SurveyInstance getSurvey(Integer id)
	{
	    return (SurveyInstance) JDInstanceDAO.retrieveFromDB(SurveyInstance.class, id);
	}
    }

