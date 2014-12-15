package main.java.dao;

import main.java.domain.JDInstance;
import main.java.utils.HibernateUtils;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Admin on 30.11.14.
 */
@Repository
public class JDInstanceDAO {

        @Autowired
        private static SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

        @javax.transaction.Transactional
        public static void saveIntoDB(JDInstance instance) {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(instance);
            session.getTransaction().commit();
            if (session.isOpen())
	    {
		session.close();
	    }
        }
        
        public static void updateInDB(JDInstance instance) throws HibernateException {
            Session session = null;
	    try
	    {
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(instance);
		session.getTransaction().commit();
	    }
	    finally
	    {
		if (null != session && session.isOpen())
		{
		    session.close();
		}
	    }
        }
        
        /**
         * Read and get JDInstance object from database where id = object's id 
         * and id must have required class type - the same as in DB;
         * @param instanceClass
         * @param id (requires only Integer or Long)
         * @return
         */
        public static <T extends JDInstance, N extends Number> JDInstance retrieveFromDB(Class<T> instanceClass, N id)
        {
            JDInstance inst = null;
            if ((id instanceof Double) || (id instanceof Float))
        	throw new UnsupportedOperationException("Current argument's class is: "+
            id.getClass().getCanonicalName() + ". Argument 'id' requires only Integer or Long type");
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            inst = (JDInstance) session.get(instanceClass, id);
            if (session.isOpen())
	    {
		session.close();
	    }
            return inst;
        }


        @SuppressWarnings("unchecked")
        public static List<JDInstance> listJDInstance(String query) {

            return sessionFactory.getCurrentSession().createQuery(query)
                    .list();
        }
        
        @SuppressWarnings("unchecked")
	public static<T extends JDInstance> List<T> getJDInstanceList(Class<T> instance)
        {
            String query = "Select u FROM " + instance.getSimpleName() + " u";
	    Session session = HibernateUtils.getSessionFactory().openSession();
            Query que = session.createQuery(query);
            List<T>list = que.list();
            session.close();
	    return (List<T>) list;
        }
        

        public static void removeSurveyInstance(int id, Class<JDInstance> instanceClass) throws HibernateException {
            try
	    {
		JDInstance instance = (JDInstance) sessionFactory.getCurrentSession().load(
		        instanceClass, id);
		if (null != instance) {
		    sessionFactory.getCurrentSession().delete(instance);
		}
	    }
            finally{}

        }
    }
