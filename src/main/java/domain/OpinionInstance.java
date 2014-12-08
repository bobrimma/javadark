package main.java.domain;

import javax.persistence.*;

/**
 *
 *@author Kapitoha
 *
 */
@Embeddable
@Table (name = "Opinions")
public final class OpinionInstance implements JDInstance {
    private static final long serialVersionUID = 1L;
    @ManyToOne
    @JoinColumn (name = "id_user")
    private int userId;
    @ManyToOne
    @JoinColumn (name = "id_question")
    private int questionId;
    @ManyToOne
    @JoinColumn (name = "id_answer")
    private int answerId;
    
    public OpinionInstance(){}
    public OpinionInstance(int userId, int questionId, int answerId)
    {
	this.userId = userId;
	this.questionId = questionId;
	this.answerId = answerId;
    }

    public int getQuestionId()
    {
        return questionId;
    }

    public int getAnswerId()
    {
        return answerId;
    }

    public int getUserId()
    {
	return userId;
    }
    

}
