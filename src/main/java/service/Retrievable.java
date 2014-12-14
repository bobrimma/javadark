package main.java.service;

import java.util.List;

import main.java.domain.SurveyInstance;
import main.java.domain.UserInstance;

/**
 *
 *@author Kapitoha
 *
 */
public interface Retrievable {
    //************************************************** Users case
    public List<UserInstance> getAllUsers();
    public List<UserInstance> getUsers(String login, String name, String lastName, String email);
    public UserInstance getUser(Integer id);
    public Integer getUser(String login);
    //************************************************** Opinion Poll case
    public List<SurveyInstance> getSurveys();
    public List<SurveyInstance> getSurveys(String keyword);
    public List<SurveyInstance> getSurveys(boolean isPublished);
    public SurveyInstance getSurvey(Integer id);
}
