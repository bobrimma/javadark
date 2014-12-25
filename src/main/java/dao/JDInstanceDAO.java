package dao;

import domain.JDInstance;
import utils.HibernateUtils;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Admin on 30.11.14.
 */
@Repository
public class JDInstanceDAO {

        @Autowired
        private static SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

        @javax.transaction.Transactional
        public static boolean saveIntoDB(JDInstance instance) {
            Session session = null;
            Transaction trans = null;
	    try
	    {
		session = sessionFactory.openSession();
		trans = session.beginTransaction();
		session.save(instance);
		trans.commit();
		return true;
	    }
	    catch (HibernateException e)
	    {
		if (trans != null)
		{
		    trans.rollback();
		}
		System.err.println(instance.getClass().getName() +" Save was unsuccessful. Rollback");
		return false;
	    }
            finally 
            { 
        	if(session.isOpen())
        	    {
        		session.close();
        	    }
            }
        }
        
        public static boolean updateInDB(JDInstance instance) throws HibernateException {
            Session session = null;
            Transaction trans = null;
	    try
	    {
		session = sessionFactory.openSession();
		trans = session.beginTransaction();
		session.update(instance);
		trans.commit();
		return true;
	    }
	    catch(Exception e)
	    {
		if (trans != null)
		{
		    trans.rollback();
		}
		System.err.println("Save was unsuccessful. Rollback");
		return false;
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
            Session session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            Query query2 = session.createQuery(query);
            List<JDInstance> list = query2.list();
            session.close();
            return list;
        }
        
        /**
         * Return list with all JDInstances in DB
         * @param instance
         * @return
         */
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
        
        /**
         * Return maximal Integer value in the column where InstanceClass.class and column name with Integer values
         * @param instanceClass
         * @param columnName
         * @return int
         */
        public static<T extends JDInstance> int getMaximalExistedId(Class<T> instanceClass, String columnName)
        {
            Session session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            Criteria criteria = session.createCriteria(instanceClass);
            criteria.setProjection(Projections.max(columnName));
            criteria.setMaxResults(1);
            int rez = (int) criteria.uniqueResult();
            session.close();
            return rez;
        }
        
        /**
         * Returns maximal existed ID + 1;
         * @param instanceClass
         * @param columnName
         * @return
         */
        public static<T extends JDInstance> int getNextAllowedUnicId(Class<T> instanceClass, String columnName)
        {
            AtomicInteger id = new AtomicInteger(getMaximalExistedId(instanceClass, columnName));
            return id.incrementAndGet();
        }
        

        public static<T extends JDInstance> void removeSurveyInstance(int id, Class<T> instanceClass) throws HibernateException {
            Session session = null;
            try
	    {
        	session = HibernateUtils.getSessionFactory().openSession();
		JDInstance instance = (JDInstance) session.load(instanceClass, id);
		if (null != instance) {
		    Transaction transaction = session.beginTransaction();
		    session.delete(instance);
		    transaction.commit();
		}
	    }
            finally{
        	if (null != session && session.isOpen())
        	    session.close();
            }

        }
    }
