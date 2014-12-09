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
    @ManyToOne
    @JoinColumn (name = "id_question")
    private QuestionInstance question;
    @Id
    @GeneratedValue
    @Column (name = "id", unique = true, nullable = false, length = 11)
    private int id;
    
    @Transient
    private int questionId;
    @Column (name = "description")
    private String answerDescription;
    @Column(name = "correct", columnDefinition = "bit(1) default false")
    private boolean isCorrect;
    
    public AnswerInstance(){}
    public AnswerInstance(QuestionInstance question)
    {
	this.question = question;
    }

    public String getAnswerDescription()
    {
        return answerDescription;
    }

    public void setAnswerDescription(String opinionDescription)
    {
        this.answerDescription = opinionDescription;
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
    public boolean isCorrect()
    {
	return isCorrect;
    }
    public void setCorrect(boolean isCorrect)
    {
	this.isCorrect = isCorrect;
    }
    @Override
    public String toString()
    {
	return "AnswerInstance [question=" + question + ", id=" + id
		+ ", opinionDescription=" + answerDescription + ", isCorrect="
		+ isCorrect + "]";
    }


}
