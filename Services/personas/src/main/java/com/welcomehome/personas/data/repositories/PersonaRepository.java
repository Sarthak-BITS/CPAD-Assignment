package com.welcomehome.personas.data.repositories;

import com.welcomehome.personas.data.entities.PersonaEntity;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonaRepository extends CouchbaseRepository<PersonaEntity, Integer> {

    List<PersonaEntity> findByType(String type);
    Optional<PersonaEntity> findByEmail(String email);

}
