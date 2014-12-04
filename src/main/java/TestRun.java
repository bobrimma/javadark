package main.java;

import java.util.Random;

import main.java.dao.JDInstanceDAO;
import main.java.domain.AnswerInstance;
import main.java.domain.QuestionInstance;
import main.java.domain.SurveyInstance;
import main.java.utils.HibernateUtils;

public class TestRun {

    /**
     * Create Opinion poll with random questions count
     * @return
     */
    public static SurveyInstance createPoll()
    {
	Random rand = new Random();
	SurveyInstance survey = new SurveyInstance();
	survey.setName("First Poll");
	for (int j = 0; j < rand.nextInt(5); j++)
	{
	    QuestionInstance question = new QuestionInstance();
	    question.setName("question");
	    question.setDescription("descr");
	    for (int i = 0; i < rand.nextInt(5); i++)
	    {
		AnswerInstance answer = new AnswerInstance();
		answer.setAnswerDescription("answer description");
		question.addAnswer(answer);
	    }
	    survey.addQuestion(question);
	}
	return survey;
    }
    public static void main(String[] args)
    {
	HibernateUtils.getSessionFactory();
//	SurveyInstance survey = new SurveyInstance();
//	QuestionInstance question = new QuestionInstance();
//	AnswerInstance answer = new AnswerInstance();
//	question.addAnswer(answer);
//	survey.addQuestion(question);
//	JDInstanceDAO.addSurveyInstance(survey);
	for (int i = 0; i < 5; i++)
	{
	    JDInstanceDAO.addSurveyInstance(createPoll());
	}
	HibernateUtils.getSessionFactory().close();
    }

}
