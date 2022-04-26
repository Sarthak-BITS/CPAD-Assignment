package com.welcomehome.properties.services;

import com.welcomehome.properties.data.entities.PropertyAddress;
import com.welcomehome.properties.data.entities.PropertyEntity;
import com.welcomehome.properties.data.repositories.PropertyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Override
    public List<PropertyEntity> getAllProperties(){
        List<PropertyEntity> propertyEntities = new ArrayList<>();
        try {
            propertyEntities = propertyRepository.findAll();

            for (PropertyEntity propertyEntity : propertyEntities) {
                System.out.println(propertyEntity.getPropertyName());
                System.out.println(propertyEntity.getOwnerId());
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return propertyEntities;
    }

    @Override
    public Optional<PropertyEntity> getPropertyById(Integer id){
        return propertyRepository.findById(id);
    }

    @Override
    public PropertyEntity createProperty(){

        List<String> amenitiesList = new ArrayList<>();
        amenitiesList.add("bath");
        amenitiesList.add("kitchen");
        amenitiesList.add("shower");

        PropertyAddress propertyAddress = new PropertyAddress("221C","Baker Road","Imola",10000);
        PropertyEntity propertyEntity = new PropertyEntity(3000,"Home","Test",20000,
                amenitiesList,propertyAddress);

        return propertyRepository.save(propertyEntity);
   }
}
