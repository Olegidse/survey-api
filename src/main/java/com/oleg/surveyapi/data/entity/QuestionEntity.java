package com.oleg.surveyapi.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SequenceGenerator(allocationSize = 1, name = "answer_seq", sequenceName = "answer_seq")
@Accessors(chain = true)
@Table(name = "question")
public class QuestionEntity {
    @Id
    @GeneratedValue(generator = "question_seq")
    @Column(name = "question_id")
    private Long questionId;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private SurveyEntity survey;

    @OneToMany
    @JoinColumn(name = "question_id")
    private List<AnswerEntity> answer;

    private String text;

    private String type;
}
