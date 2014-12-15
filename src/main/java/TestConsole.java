import java.util.Scanner;

import dao.JDInstanceDAO;
import domain.AnswerInstance;
import domain.JDInstance;
import domain.QuestionInstance;
import domain.SurveyInstance;
import domain.UserInstance;

/**
 *
 *@author Kapitoha
 *
 */
public class TestConsole {


    public static void main(String[] args)
    {
	Scanner scan = new Scanner(System.in);
	System.out.println("HELLO! THIS IS TESTING DEMO PRESENTATION");
	System.out.println("Please, enter users count do you want to create");
	int usersCount = scan.nextInt();
	for (int i = 0; i < usersCount; i++)
	{
	    System.out.println("USER "+ (i+1));
	    UserInstance user = new UserInstance();
	    System.out.println("Enter login");
	    user.setLogin(scan.nextLine());
	    System.out.println("Enter email");
	    user.setEmail(scan.nextLine());
	}
	
	System.out.println("Now you can create your Opinion poll");
	System.out.println("Enter survey's name");
	String surveyName = scan.nextLine();
	System.out.println("Enter survey's description");
	String surveyDesc = scan.nextLine();
	SurveyInstance survey = new SurveyInstance();
	survey.setName(surveyName);
	survey.setDescription(surveyDesc);
	
	System.out.println("How many questions do you want your Opinion Poll consists of? Enter integer");
	int questCount = scan.nextInt();
	for (int i = 0; i < questCount; i++)
	{
	    System.out.println("Enter question's short name");
	    String qName = scan.nextLine();
	    System.out.println("Enter question's description");
	    String qDesc = scan.nextLine();
	    System.out.println("Is this question allows multianswer? (y/N)");
	    boolean multianswer = "y".equalsIgnoreCase(scan.nextLine())? true : false;
	    QuestionInstance quest = new QuestionInstance(survey);
	    quest.setAllowMultipleAnswers(multianswer);
	    quest.setName(qName);
	    quest.setDescription(qDesc);
	    System.out.println("How many optional answers it contains? (Integer)");
	    
	    int answerCount = scan.nextInt();
	    for (int j = 0; j < answerCount; j++)
	    {
		System.out.println("Enter answer's description");
		String aDesc = scan.nextLine();
		System.out.println("Is this answer correct? (y/N)");
		boolean correct = "y".equalsIgnoreCase(scan.nextLine())? true : false;
		AnswerInstance answer = new AnswerInstance(quest);
		answer.setCorrect(correct);
		answer.setAnswerDescription(aDesc);
		quest.addAnswer(answer);
	    }
	    survey.addQuestion(quest);
	}
	JDInstanceDAO.saveIntoDB(survey);
	System.out.println("****************************************************************************\n");
	System.out.println("Printing result");
	JDInstance inst = null;
	for (int i = 0; null != (inst = JDInstanceDAO.retrieveFromDB(SurveyInstance.class, i)); i++)
	{
	    if (null != inst && inst instanceof SurveyInstance)
	    {
		SurveyInstance surv = (SurveyInstance) inst;
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		System.out.println("Survey's id: " + surv.getId());
		System.out.println("Survey's name: " + surv.getName());
		System.out.println("Survey's description: " + surv.getDescription());
		for (QuestionInstance que : surv.getQuestions())
		{
			System.out.println("\tQuestion's id: " + que.getId());
			System.out.println("\tQuestion's name: " + que.getName());
			System.out.println("\tQuestion's description: " + que.getDescription());
			System.out.println("\tIs multianswer?: "+que.isAllowMultipleAnswers());
			for (AnswerInstance ans : que.getAnswerList())
			{
			    System.out.println("\t\tAnswer's id: " + ans.getId());
			    System.out.println("\t\tAnswer's description: " + ans.getAnswerDescription());
			    System.out.println("\t\tIs correct?: "+ ans.isCorrect());
			}
		}
	    }
	    System.out.println("##############################################################");
	}
	scan.close();
    }

}
