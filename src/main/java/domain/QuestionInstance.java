package main.java.domain;


import java.io.Serializable;
import java.util.*;

/**
 *
 * @author Kapitoha
 *
 */
public class QuestionInstance implements Serializable {
    private static final long serialVersionUID = 1L;
    //It's a primary key which should be inserted into DB. *** It must be changed
    private final int id;
    private String name;
    //Optional description of this Survey
    private String description;
    private final int authorId;
    private final Date creationDate;
    //This optional field is a tags holder that allows survey's tag search
    private Set<String> tagList = Collections.emptySet();
    //This field keeps all options of this Survey
    private final List<OpinionInstance> opinions = new ArrayList<>();
    //This field keeps users IDs which has voted
    private final Set<Integer> subscribers = new HashSet<>();

    public QuestionInstance(String name, int id, int authorId)
    {
        this.setName(name);
        this.id = id;
        this.authorId = authorId;
        creationDate = new Date();
    }

    /**
     * Add new optionally answer to the survey
     * @param newOption
     * @return
     */
    public boolean addNewAnswer(String newOption)
    {
        if (null != newOption)
        {
            OpinionInstance ans = new OpinionInstance(newOption, opinions.size(), this.id);
            opinions.add(ans);
            return true;
        }
        return false;
    }

    /**
     * Vote for the particular opinion option
     * @param option
     * @param userId
     */

    public void vote (OpinionInstance option, int userId)
    {
        if (!subscribers.contains((int) userId))
        {
            subscribers.add(userId);
            option.addVote();
        }
        else
            System.out.println("You have already voted to this option");
    }

    /**
     * Set new searching tags through the spaces or commas
     * @param tags
     */
    public void setTags(String tags)
    {
        tagList = new HashSet<String>(Arrays.asList(tags.trim().replaceAll("\\W+", " ").replaceAll("\\s+", ",").split("\\,")));

    }

    /**
     * Get searching tags
     * @return
     */
    public String getTags()
    {
        return (!tagList.isEmpty())? tagList.toString() : null;
    }

    /**
     * Get survey's id
     * @return
     */
    public int getId()
    {
        return id;
    }

    /**
     * get survey's name
     * @return
     */
    public String getName()
    {
        return name;
    }

    /**
     * Set surveys name
     * @param name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Get surveys description
     * @return
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Set survey's description
     * @param description
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * Get survey author's id
     * @return
     */
    public int getAuthorId()
    {
        return authorId;
    }

    /**
     * Get creation date of this survey
     * @return
     */
    public Date getCreationDate()
    {
        return creationDate;
    }
    public List<OpinionInstance> getOpinionList()
    {
        return opinions;
    }

    /**
     * This class optionally could be replaced out from the nested state
     * @author Kapitoha
     *
     */

    public static class OpinionInstance implements Serializable {
        private static final long serialVersionUID = -4814539795984372914L;
        private final int id;
        private final int parentId;
        private String opinionDescription;
        private AtomicInteger votesCount = new AtomicInteger(0);

        /**
         * Create new optional opinion
         * @param opinion - new option
         * @param parentId - the parent SurveyInstance's id
         */
        public OpinionInstance(String opinion, int id,  int parentId)
        {
            this.opinionDescription = opinion;
            this.id = id;
            this.parentId = parentId;
        }

        /**
         * Vote for this option
         * @param userId
         */
        public void addVote()
        {
            votesCount.incrementAndGet();
        }

        public String getOpinionDescription()
        {
            return opinionDescription;
        }

        public void setOpinionDescription(String opinionDescription)
        {
            this.opinionDescription = opinionDescription;
        }

        /**
         * Get all votes count for this option
         * @return
         */
        public int getVotesCount()
        {
            return votesCount.get();
        }

        public boolean equals(Object ob)
        {
            //bla bla
            return false;
        }
        public int hashCode()
        {
            return (id + parentId) << 17;
        }
    }



    //============================* Test zone *======================= All that below this line is must be removed
    public static void testPrint(QuestionInstance inst)
    {
        System.out.println("=====================");
        System.out.println("Instance id: " + inst.getId());
        System.out.println("Name: "+ inst.getName());
        System.out.println("Descr: " + inst.getDescription());
        System.out.println("Author id: " + inst.getAuthorId());
        System.out.println("Tags: " + inst.getTags());
        System.out.println("Date: " + inst.getCreationDate());
        for (OpinionInstance ans : inst.getOpinionList())
            System.out.println("Option: "+ans.id + " Description: " + ans.getOpinionDescription() + " Votes: " + ans.getVotesCount());
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
            // TODO Auto-generated catch block
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
            in.setTags("asdasd dfgdfg, dfgfdg, dfg ");
            for (int j = 0; j < rand.nextInt(10); j++)
            {
                in.addNewAnswer("Opinion test: " + j);
            }
            instSet.add(in);
        }
        //Random votings
        for (QuestionInstance surveyInstance : instSet)
        {
            if (!surveyInstance.getOpinionList().isEmpty())
            {
                for (int j = 0; j < 50; j++)
                {
                    OpinionInstance ans = surveyInstance.getOpinionList()
                            .get(rand.nextInt(surveyInstance
                                    .getOpinionList().size()));
                    surveyInstance.vote(ans, rand.nextInt(1000));
                }
            }
        }
        //print results
        for (QuestionInstance surveyInstance : instSet)
        {
            testPrint(surveyInstance);
        }

    }

}
