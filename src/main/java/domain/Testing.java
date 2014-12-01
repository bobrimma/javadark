package main.java.domain;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * This class just for tests. It should be removed in release
 * @author kapitoha
 *
 */
public class Testing {

    public static void testPrint(QuestionInstance inst)
    {
        System.out.println("=====================");
        System.out.println("Instance id: " + inst.getId());
        System.out.println("Name: "+ inst.getName());
        System.out.println("Descr: " + inst.getDescription());
        for (AnswerInstance ans : inst.getAnswerList())
            System.out.println("Option: "+ans.getId() + " Description: " + ans.getAnswerDescription());
        System.out.println("=====================");
    }

    public static void testSleep(long milis)
    {
        try
        {
            Thread.sleep(milis);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
    public static void main(String[] args)
    {
        QuestionInstance in = null;
        Set<QuestionInstance> instSet = new HashSet<>();
        Random rand = new Random();
        System.out.println("Create new Surveys");
        for (int i = 0; i < 5; i++)
        {
            in = new QuestionInstance("Survey " + i, i, rand.nextInt(1000));
            for (int j = 0; j < rand.nextInt(10); j++)
            {
                in.addNewAnswer("Opinion test: " + j);
            }
            instSet.add(in);
        }
        //print results
        for (QuestionInstance surveyInstance : instSet)
        {
            testPrint(surveyInstance);
        }

    }


}
