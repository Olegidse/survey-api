package com.oleg.surveyapi.data.repository;

import com.oleg.surveyapi.data.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository <PersonEntity, Long> {
}
