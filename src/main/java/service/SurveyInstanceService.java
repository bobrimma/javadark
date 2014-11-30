package main.java.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.java.dao.SurveyInstanceDAO;
import main.java.domain.SurveyInstance;
import main.java.domain.QuestionInstance;


/**
 * Created by Admin on 30.11.14.
 */
@Service
public class SurveyInstanceService {

        @Autowired
        private SurveyInstanceDAO instanceDAO;

        @Transactional
        public void addInstance(SurveyInstance instance) {

            instanceDAO.addSurveyInstance(instance);

        }

        @Transactional
        public List<QuestionInstance> listInstance() {

            return instanceDAO.listSurveyInstance();
        }

        @Transactional
        public void removeInstance(Integer id) {

            instanceDAO.removeSurveyInstance(id);

        }
    }

