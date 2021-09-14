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
@SequenceGenerator(allocationSize = 1, name = "survey_seq", sequenceName = "survey_seq")
@Accessors(chain = true)
@Table(name = "survey")
public class SurveyEntity {
    @Id
    @GeneratedValue(generator = "survey_seq")
    @Column(name = "survey_id")
    private Long surveyId;

    @OneToMany
    @JoinColumn(name = "survey_id")
    private List<QuestionEntity> questions;

    private String name;

    private String description;
    @Column(name = "start_date")
    private Long startDate;
    @Column(name = "end_date")
    private Long endDate;


}
