package com.welcomehome.properties.data.repositories;

import com.welcomehome.properties.data.entities.PropertyEntity;
import org.springframework.data.couchbase.repository.CouchbaseRepository;


public interface PropertyRepository extends CouchbaseRepository<PropertyEntity, Integer> {
}
