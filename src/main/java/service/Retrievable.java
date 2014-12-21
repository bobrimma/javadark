package service;

import java.util.List;

import domain.AnswerInstance;
import domain.QuestionInstance;
import domain.SurveyInstance;
import domain.UserInstance;

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
   // public List<QuestionInstance> getUnsubsrQuestions();
   // public List<AnswerInstance> getUnsubsrAnswers();
    public List<QuestionInstance> getQuestions(Integer surveyId);
    public List<AnswerInstance> getAnswers(Integer questionId);
    public int getNextAllowedSurveyUnicId();
    public int getNextAllowedQuestionUnicId();
    public int getNextAllowedAnswerUnicId();
}
