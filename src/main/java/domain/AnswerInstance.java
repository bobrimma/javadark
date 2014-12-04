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
    @Column (name = "id", unique = true, nullable = false, length = 11)
    private int id;
    @JoinColumn (name = "id_question")
    @Transient
    private int questionId;
    @Column (name = "description")
    private String opinionDescription;

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
