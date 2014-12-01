package main.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.java.dao.JDInstanceDAO;
import main.java.domain.JDInstance;


/**
 * Created by Admin on 30.11.14.
 */
@Service
public class JDInstanceService {

        @Autowired
        private JDInstanceDAO instanceDAO;

        @Transactional
        public void addInstance(JDInstance instance) {

            instanceDAO.addSurveyInstance(instance);

        }

        @Transactional
        public List<JDInstance> listInstance(String table) {

            return instanceDAO.listSurveyInstance(table);
        }

        @Transactional
        public void removeInstance(int id, Class<JDInstance> instanceClass) {

            instanceDAO.removeSurveyInstance(id, instanceClass);

        }
    }

