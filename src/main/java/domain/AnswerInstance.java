package main.java.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * 
 * @author Kapitoha
 *
 */
@Entity
@Table (name = "Answers")
public final class AnswerInstance implements JDInstance, Serializable{

    private static final long serialVersionUID = -4814539795984372914L;
    @Id
    @GeneratedValue
    @Column (name = "id")
    private int id;
    @Column (name = "id_question")
    private int questionId;
    @Column (name = "description")
    private String opinionDescription;

    /**
     * Create new optional opinion
     * @param answer - new option
     * @param questionId - the parent SurveyInstance's id
     */
    public AnswerInstance(String answer, int questionId)
    {
	this.opinionDescription = answer;
        this.questionId = questionId;
    }
    /**
     * Create new optional opinion
     * @param answer - new option
     * @param id - set id
     * @param questionId - the parent SurveyInstance's id
     */
    public AnswerInstance(String answer, int id,  int questionId)
    {
        this(answer, questionId);
        this.id = id;
    }

    public String getAnswerDescription()
    {
        return opinionDescription;
    }

    public void setAnswerDescription(String opinionDescription)
    {
        this.opinionDescription = opinionDescription;
    }

    public boolean equals(Object ob)
    {
        //bla bla
        return false;
    }
    public int hashCode()
    {
        return (id + questionId) << 17;
    }

    public int getId()
    {
	return id;
    }


}
