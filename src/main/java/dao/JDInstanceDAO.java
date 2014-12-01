package main.java.dao;

import main.java.domain.JDInstance;
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
        private SessionFactory sessionFactory; //it seems nullpointerexception here

        public void addSurveyInstance(JDInstance instance) {
            sessionFactory.getCurrentSession().save(instance);
        }

        @SuppressWarnings("unchecked")
        public List<JDInstance> listSurveyInstance(String from) {

            return sessionFactory.getCurrentSession().createQuery("from " + from)
                    .list();
        }

        public void removeSurveyInstance(int id, Class<JDInstance> instanceClass) {
            JDInstance instance = (JDInstance) sessionFactory.getCurrentSession().load(
                    instanceClass, id);
            if (null != instance) {
                sessionFactory.getCurrentSession().delete(instance);
            }

        }
    }
