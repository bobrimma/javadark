package main.java.domain;

import java.util.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Admin on 30.11.14.
 */
@Entity
@Table(name = "SurveyInstances")
public class SurveyInstance {

    @Id
    @Column(name = "Id")
    @GeneratedValue
    private final int id;
    @Column(name = "Name")
    private  String name;
    @Column(name = "SurveyInstance")
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

        {
            if (null != question)
            {
                QuestionInstance que = new QuestionInstance(question, questions.size(), this.id);
                questions.add(que);
                return true;
            }
            return false;
        }



    }

}
