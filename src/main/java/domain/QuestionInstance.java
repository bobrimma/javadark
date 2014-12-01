package main.java.domain;
import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 *
 * @author Kapitoha
 *
 */
@Entity
@Table(name = "Questions")
public final class QuestionInstance implements JDInstance, Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column (name = "id")
    @GeneratedValue
    private int id;
    
    @Column (name = "name")
    private String name;
    
    //Optional description of this question
    @Column (name = "description")
    private String description;
    
    @Column (name = "id_survey")
    private int surveyId;
    
    //This field keeps all options of this question
    private final List<AnswerInstance> answers = new ArrayList<>();
    
    public QuestionInstance(String name, int surveyId)
    {
        this.name = name;
        this.surveyId = surveyId;
    }
    
    public QuestionInstance(String name, int id, int surveyId)
    {
        this(name, surveyId);
        this.id = id;
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
            AnswerInstance ans = new AnswerInstance(newOption, answers.size(), this.id);
            return answers.add(ans);
        }
        return false;
    }

    /**
     * Add new optionally answer to the question
     * @param answer
     * @return True if successful
     */
    public boolean addAnswer(AnswerInstance answer)
    {
	return answers.add(answer);
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
     * Get question's description
     * @return
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Set question's description
     * @param description
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    public List<AnswerInstance> getAnswerList()
    {
        return answers;
    }
    

}
