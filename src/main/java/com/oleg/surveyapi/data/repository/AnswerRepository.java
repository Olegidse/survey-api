package com.oleg.surveyapi.data.repository;

import com.oleg.surveyapi.data.entity.AnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<AnswerEntity,Long> {
}
