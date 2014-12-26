package service;

import dao.JDInstanceDAO;
import domain.AnswerInstance;
import domain.JDEmailConfigInstance;
import domain.JDInstance;
import domain.QuestionInstance;
import domain.SurveyInstance;
import domain.UserInstance;
import service.email.EmailSession;
import utils.HibernateUtils;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by Admin on 30.11.14.
 */
@Service
public class JDInstanceService implements Retrievable, Actionable {
    
    public static JDInstanceService getInstance()
    {
	return new JDInstanceService();
    }

        @Autowired
        private JDInstanceDAO instanceDAO;

        @Transactional
        public boolean saveInstance(JDInstance instance) {
            
            synchronized (JDInstanceService.class)
	    {
        	return JDInstanceDAO.saveIntoDB(instance);
	    }
        }
        
        /**
         * If jdEmailConfigs == null, there would be used existed configs with 0 Id, if they are existed :)
         * @param to
         * @param subject
         * @param message
         * @param jdEmailConfigs
         */
        public boolean sendEmail(String to, String subject, String message, JDEmailConfigInstance jdEmailConfigs)
        {
            String pKey = "javadark";
            JDEmailConfigInstance jdEmail = jdEmailConfigs;
            if (null == jdEmail)
            {
        	jdEmail = (JDEmailConfigInstance) JDInstanceDAO.retrieveFromDB(JDEmailConfigInstance.class, 0);
        	if (jdEmail == null)
		{
		    System.err.println("There is now such EmailConfigs in DB");
		    return false;
		}
            }
            EmailSession es = new EmailSession(jdEmail.getHost(pKey), jdEmail.getPort(pKey), jdEmail.getProtocol(pKey));
        	try
        	{
        	    es.sendMail(jdEmail.getEmail(pKey), to, subject, message, jdEmail.getUsername(pKey), jdEmail.getPassword(pKey) , false);
        	    return true;
        	}
        	catch (MessagingException e)
        	{
        	    System.err.println("cannot sent email, cause: " + e.getLocalizedMessage());
        	    return false;
        	}
        }

        @Transactional
        public <T extends JDInstance> boolean removeInstance(int id, Class<T> instanceClass) {

            try
	    {
		JDInstanceDAO.removeSurveyInstance(id, instanceClass);
	    }
	    catch (Exception e)
	    {
		System.err.println("Cannot remove instance, cause: " + e.getLocalizedMessage());
		return false;
	    }
            return true;
        }
        
        public boolean updateInstance(JDInstance instance)
        {
           return JDInstanceDAO.updateInDB(instance);
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
	public Integer getUserId(String login)
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

/*	@Override
	public List<QuestionInstance> getUnsubsrQuestions() {
	    String query = "Select q FROM " +QuestionInstance.class.getSimpleName()+" q WHERE q.survey = "+null;
	    Session session = HibernateUtils.getSessionFactory().openSession();
            Query que = session.createQuery(query);
            List<QuestionInstance> list = que.list();
            session.close();
	    return list;
	}
	
	@Override
	public List<AnswerInstance> getUnsubsrAnswers() {
	    String query = "Select a FROM " +AnswerInstance.class.getSimpleName()+" a WHERE a.question = "+null;
	    Session session = HibernateUtils.getSessionFactory().openSession();
            Query que = session.createQuery(query);
            List<AnswerInstance> list = que.list();
            session.close();
	    return list;
	}*/
	
	@SuppressWarnings("unchecked")
	@Override
	public List<QuestionInstance> getQuestions(Integer surveyId) {
	    String query = "Select q FROM " +QuestionInstance.class.getSimpleName()+" q WHERE q.survey = "+surveyId;
	    Session session = HibernateUtils.getSessionFactory().openSession();
            Query que = session.createQuery(query);
            List<QuestionInstance> list = que.list();
            session.close();
	    return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AnswerInstance> getAnswers(Integer questionId) {
	    String query = "Select q FROM " +AnswerInstance.class.getSimpleName()+" q WHERE q.question = "+questionId;
	    Session session = HibernateUtils.getSessionFactory().openSession();
            Query que = session.createQuery(query);
            List<AnswerInstance> list = que.list();
            session.close();
	    return list;
	}
	@SuppressWarnings("unchecked")
	
	public int getCorAnswers(Integer questionId) {
	    String query = "Select q FROM " +AnswerInstance.class.getSimpleName()+" q WHERE q.question = "+questionId+" and q.isCorrect=true";
	    Session session = HibernateUtils.getSessionFactory().openSession();
            Query que = session.createQuery(query);
            List<AnswerInstance> list = que.list();
            session.close();
	    return list.size();
	}
	
	public<T extends JDInstance> int getNextAllowedUnicId(Class<T> instanceClass)
	{
	    return JDInstanceDAO.getNextAllowedUnicId(instanceClass, "id");
	}
	
	public boolean validateUser(UserInstance user)
	{
	    Session session = HibernateUtils.getSessionFactory().openSession();
	    Query query = session.
		    createQuery(String.format("SELECT u FROM %s u WHERE u.login=:login AND u.password=:password", 
			    user.getClass().getSimpleName()));
	    query.setParameter("login", user.getLogin());
	    query.setParameter("password", user.getPassword());
	    UserInstance u = (UserInstance) query.uniqueResult();
	    session.close();
	    return u != null;
	}

	@Override
	public QuestionInstance getQuestion(Integer id) {
		return (QuestionInstance) JDInstanceDAO.retrieveFromDB(QuestionInstance.class, id);
	}
	

    }

