package main.java.dao;

import main.java.domain.QuestionInstance;
import main.java.domain.SurveyInstance;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Created by Admin on 30.11.14.
 */
@Repository
public class SurveyInstanceDAO {

        @Autowired
        private SessionFactory sessionFactory;

        public void addSurveyInstance(SurveyInstance instance) {
            sessionFactory.getCurrentSession().save(instance);
        }

        @SuppressWarnings("unchecked")
        public List<QuestionInstance> listSurveyInstance() {

            return sessionFactory.getCurrentSession().createQuery("from SurveyInstance")
                    .list();
        }

        public void removeSurveyInstance(Integer id) {
            SurveyInstance instance = (SurveyInstance) sessionFactory.getCurrentSession().load(
                    SurveyInstance.class, id);
            if (null != instance) {
                sessionFactory.getCurrentSession().delete(instance);
            }

        }
    }
