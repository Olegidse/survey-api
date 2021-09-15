package com.oleg.surveyapi.data.repository;

import com.oleg.surveyapi.data.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository <PersonEntity, Long> {
    Optional<PersonEntity> findPersonEntitiesByExternalId(Long id);
}
