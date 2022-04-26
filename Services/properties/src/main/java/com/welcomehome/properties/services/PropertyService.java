package com.welcomehome.properties.services;

import com.welcomehome.properties.data.entities.PropertyEntity;

import java.util.List;
import java.util.Optional;

public interface PropertyService {
    List<PropertyEntity> getAllProperties();

    Optional<PropertyEntity> getPropertyById(Integer id);

    PropertyEntity createProperty();
}
