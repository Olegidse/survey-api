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
@SequenceGenerator(allocationSize = 1, name = "application_user_seq", sequenceName = "application_user_seq")
@Accessors(chain = true)
@Table(name = "person")
public class PersonEntity {

    @Id
    @GeneratedValue(generator = "person_seq")
    @Column(name = "person_id")
    private Long personId;

    @Column(name = "external_id", unique = true)
    private Long externalId;

    @JoinColumn(name = "person_id")
    @OneToMany(cascade = CascadeType.ALL)
    private List<AnswerEntity> answers;

}
