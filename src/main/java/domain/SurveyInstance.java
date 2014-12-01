package main.java.domain;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

/**
 * Created by Admin on 30.11.14.
 */
@Entity
@Table(name = "SurveyInstances")
public class SurveyInstance implements JDInstance, Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;
    @Column(name = "name")
    private  String name;
    @Column (name = "published")
    private boolean isPublished;
    //? @Column(name = "SurveyInstance")
    private final List<QuestionInstance> questions = new ArrayList<>();


    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
    public List<QuestionInstance> getQuestions () {

        return questions;

    }

    public boolean addQuestion (String question) {
	if (null != question)
	{
	    QuestionInstance que = new QuestionInstance(question, questions.size(), this.id);
	    questions.add(que);
	    return true;
	}
	return false;
    }
    
    /**
     * Add new question to the survey
     * @param question
     * @return
     */
    public boolean addQuestion (QuestionInstance question)
    {
	return questions.add(question);
    }
    
    /**
     * Check a published state
     * @return True if published
     */
    public boolean isPublished()
    {
	return isPublished;
    }

    /**
     * Set published state
     * @param isPublished
     */
    public void setPublished(boolean isPublished)
    {
	this.isPublished = isPublished;
    }

}
