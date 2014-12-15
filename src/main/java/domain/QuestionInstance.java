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
    @ManyToOne
    @JoinColumn (name = "id_survey")
    private SurveyInstance survey;
    
    @Id
    @Column (name = "id", unique = true, nullable = false, length = 11)
    @GeneratedValue
    private int id;
    
    @Column (name = "name")
    private String name;
    
    //Optional description of this question
    @Column (name = "description")
    private String description;
    
    @Column (name = "multianswer", columnDefinition = "bit(1) default false")
    private boolean allowMultipleAnswers;
    
//    @ManyToOne
//    @JoinColumn (name = "id_survey")
    @Transient
    private int surveyId;
    
    //This field keeps all options of this question
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question", fetch = FetchType.EAGER)
    private final List<AnswerInstance> answers = new ArrayList<>();

    public QuestionInstance()
    {
	// TODO Auto-generated constructor stub
    }
    public QuestionInstance(SurveyInstance survey)
    {
	this.survey = survey;
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
    /**
     * Get full answers list
     * @return
     */
    public List<AnswerInstance> getAnswerList()
    {
        return answers;
    }

    /**
     * Check if able to get more than one answer per one question
     * @return
     */
    public boolean isAllowMultipleAnswers()
    {
	return allowMultipleAnswers;
    }

    /**
     * Set able to give more than one answer to one question
     * @param Boolean allowMultipleAnswers
     */
    public void setAllowMultipleAnswers(boolean allowMultipleAnswers)
    {
	this.allowMultipleAnswers = allowMultipleAnswers;
    }
    @Override
    public String toString()
    {
	return "QuestionInstance [survey=" + survey + ", id=" + id + ", name="
		+ name + ", description=" + description
		+ ", allowMultipleAnswers=" + allowMultipleAnswers + "]";
    }
    

}
