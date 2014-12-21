

import java.util.Random;

import dao.JDInstanceDAO;
import domain.AnswerInstance;
import domain.JDInstance;
import domain.OpinionInstance;
import domain.QuestionInstance;
import domain.SurveyInstance;
import domain.UserInstance;
import domain.UserPollInstance;
import service.JDInstanceService;
import service.Retrievable;
import utils.HibernateUtils;

public class TestRun {
    
    public static String randomName(int length)
    {
	Random rand = new Random();
	StringBuilder sb = new StringBuilder();
	for (int i = 0; i < length; i++)
	{
	    int res = 0;
	    while (true)
	    {
		res = rand.nextInt(122);
		if (!(res < 48 || (res > 57 && res < 65) || (res > 90 && res < 97) || res > 122))
		{
		    sb.append((char)res);
		    break;
		}
	    }
	}
	return sb.toString();
    }

    /**
     * Create Opinion poll with random questions count
     * @return
     */
    public static SurveyInstance createPoll()
    {
	Random rand = new Random();
	SurveyInstance survey = new SurveyInstance();
	survey.setName("Survey_" + randomName(15));
	for (int j = 0; j < rand.nextInt(5); j++)
	{
	    QuestionInstance question = new QuestionInstance(survey);
	    question.setName("question_" + randomName(15));
	    question.setDescription("descr_" + randomName(15));
	    question.setAllowMultipleAnswers(rand.nextBoolean());
	    for (int i = 0; i < rand.nextInt(5); i++)
	    {
		AnswerInstance answer = new AnswerInstance(question);
		answer.setAnswerDescription("ans_descr_" + randomName(15));
		question.addAnswer(answer);
	    }
	    survey.addQuestion(question);
	}
	return survey;
    }
    
    public static UserInstance randomUser()
    {
	UserInstance user = new UserInstance("login_"+randomName(15), randomName(10).toCharArray(), randomName(10)+"@email.com");
	user.setFirstName("First_" + randomName(10));
	user.setLastName("Last_" + randomName(10));
	return user;
    }
    public static void main(String[] args)
    {
	HibernateUtils.getSessionFactory();
	Retrievable ret = JDInstanceService.getInstance();
	for (int i = 0; i < 5; i++)
	{
	    JDInstanceDAO.saveIntoDB(createPoll());
	}
	//generate users
	for (int i = 0; i < 50; i++)
	{
	    JDInstanceDAO.saveIntoDB(randomUser());
	}
	int usersCount = ret.getAllUsers().size();
	//retrieve objects
	JDInstance inst = null;
	for (int i = 1; 
		(inst = JDInstanceDAO.retrieveFromDB(SurveyInstance.class, i)) != null; i++)
	{
	    if (inst instanceof SurveyInstance)
	    {
		SurveyInstance survey = (SurveyInstance) inst;
		if (!survey.getQuestions().isEmpty())
		{
		    //get survey's questions
		    QuestionInstance quest = survey.getQuestions().get(
			    new Random().nextInt(survey.getQuestions().size()));
		    if (null != quest)
		    {
			if (!quest.getAnswerList().isEmpty())
			{
			    //get question's answers
			    AnswerInstance answer = quest.getAnswerList().get(
				    new Random().nextInt(quest.getAnswerList()
					    .size()));
			    if (null != answer)
			    {
				//get existed random user
				UserInstance us = ret.getUser(new Random().nextInt(usersCount));
				//user starts answers the question
				UserPollInstance poll = new UserPollInstance(us, survey, false);
				//save poll into db
				OpinionInstance opinion = new OpinionInstance(poll, answer);
				JDInstanceDAO.saveIntoDB(opinion);
			    }
			}
		    }
		}
	    }
	    System.out.println(inst);
	}
	System.out.println("==================================");
	System.out.println("Random user's id = " + ret.getUser(ret.getAllUsers().get(new Random().nextInt(ret.getAllUsers().size())).getLogin()));
	System.out.println("==================================");
	System.out.println("Users size = " +ret.getAllUsers().size());
	System.out.println("==================================");
	System.out.println("Surveys size = " + ret.getSurveys().size());
	System.out.println("==================================");
	System.out.println("Unpublished surveys count = " + ret.getSurveys(false).size());
	System.out.println("==================================");
	System.out.println("Published surveys count = " + ret.getSurveys(true).size());
	System.out.println("==================================");
	System.out.println("Max survey id = " + JDInstanceDAO.getMaximalExistedId(SurveyInstance.class, "id"));
	System.out.println("==================================");
	System.out.println("Next allowed id = " + ret.getNextAllowedSurveyUnicId());
	HibernateUtils.getSessionFactory().close();
    }

}
