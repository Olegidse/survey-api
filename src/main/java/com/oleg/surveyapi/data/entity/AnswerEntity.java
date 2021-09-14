package com.oleg.surveyapi.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SequenceGenerator(allocationSize = 1, name = "answer_seq", sequenceName = "answer_seq")
@Accessors(chain = true)
@Table(name = "answer")
public class AnswerEntity {

    @Id
    @GeneratedValue(generator = "answer_seq")
    @Column(name = "answer_id")
    private Long answerId;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private PersonEntity person;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private QuestionEntity question;

    private String answer;

}
