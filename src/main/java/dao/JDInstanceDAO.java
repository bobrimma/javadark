package main.java.dao;

import main.java.domain.JDInstance;
import main.java.utils.HibernateUtils;

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

        public static synchronized void saveIntoDB(JDInstance instance) {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(instance);
            session.getTransaction().commit();
            session.close();
        }
        
        /**
         * Read and get JDInstance object from database where id = object's id;
         * @param instanceClass
         * @param id
         * @return
         */
        public static <T extends JDInstance> JDInstance retrieveFromDB(Class<T> instanceClass, int id)
        {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            return (JDInstance) session.get(instanceClass, id);
        }

        @SuppressWarnings("unchecked")
        public static List<JDInstance> listSurveyInstance(String from) {

            return sessionFactory.getCurrentSession().createQuery("from " + from)
                    .list();
        }

        public static void removeSurveyInstance(int id, Class<JDInstance> instanceClass) {
            JDInstance instance = (JDInstance) sessionFactory.getCurrentSession().load(
                    instanceClass, id);
            if (null != instance) {
                sessionFactory.getCurrentSession().delete(instance);
            }

        }
    }
