package com.welcomehome.properties.services;

import com.welcomehome.properties.data.entities.PropertyDto;
import com.welcomehome.properties.data.entities.PropertyEntity;
import com.welcomehome.properties.data.repositories.PropertyCustomRepository;
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

    @Autowired
    private PropertyCustomRepository propertyCustomRepository;

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
    public String createProperty(PropertyDto propertyDto){
        Integer newPropertyId = 0;
        String status = null;
        try {

            newPropertyId = propertyCustomRepository.getMaxPropertyId();
            if (newPropertyId == null) {
                newPropertyId = 8000;
            } else {
                newPropertyId++;
            }
            PropertyEntity propertyEntity = new PropertyEntity(newPropertyId,
                    propertyDto.getPropertyType(),
                    propertyDto.getPropertyName(),
                    propertyDto.getOwnerId(),
                    propertyDto.getAmenities(),
                    propertyDto.getPropertyAddress());
            propertyRepository.save(propertyEntity);
            status = "Property advertised successfully";
        } catch (Exception e){
            log.error(e.getMessage());
            status = "Property advertisement failed.";

        }
        return status;
   }
}
