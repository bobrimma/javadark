package main.java.domain;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

/**
 * Created by Admin on 30.11.14.
 */
@Entity
@Table(name = "Surveys")
public class SurveyInstance implements JDInstance, Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "id", unique = true, nullable = false, length = 11)
    @GeneratedValue
    private int id;
    @Column(name = "name")
    private  String name;
    @Column (name = "published", columnDefinition = "bit(1) default false")
    private boolean isPublished;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_survey")
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
