package main.java.domain;

import javax.persistence.*;

/**
 *
 *@author Kapitoha
 *
 */
@Entity
@IdClass(OpinionInstance.class)
@Table (name = "Opinions")
public final class OpinionInstance implements JDInstance {
    private static final long serialVersionUID = 1L;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_poll")
    @Id
    private UserPollInstance userPoll;
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn (name = "id_question")
//    @Id
    @Transient
    private QuestionInstance question;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "id_answer")
    @Id
    private AnswerInstance answer;
    @Transient
    private int id;
    
    @Transient
    private int userId;
    
//    @ManyToOne(targetEntity = QuestionInstance.class)
//    @JoinColumn (name = "id_question")
//    @JoinTable(name = "Questions")
    @Transient
    private int questionId;
    
//    @ManyToOne(targetEntity = AnswerInstance.class)
//    @JoinColumn (name = "id_answer")
//    @JoinTable(name = "Answers")
    @Transient
    private int answerId;
    
    public OpinionInstance(){}
    public OpinionInstance(UserPollInstance userPoll, AnswerInstance answer)
    {
	this.userPoll = userPoll;
	this.answer = answer;
    }
    public OpinionInstance(UserPollInstance userPoll, QuestionInstance question, AnswerInstance answer)
    {
	this.userPoll = userPoll;
	this.question = question;
	this.answer = answer;
    }
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
    
    public boolean equals(Object ob)
    {
	return ob instanceof OpinionInstance
		&& ((OpinionInstance)ob).userId == this.userId
		&& ((OpinionInstance)ob).questionId == this.questionId
		&& ((OpinionInstance)ob).answerId == this.answerId;
    }
    
    public int hashCode()
    {
	return (userId+questionId+answerId) >> 17;
    }
    

}
