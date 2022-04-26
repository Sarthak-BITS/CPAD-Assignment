package com.welcomehome.properties.web;

import com.welcomehome.properties.data.entities.PropertyEntity;
import com.welcomehome.properties.services.PropertyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1")
@Slf4j
/**
 * Administrator/Crossplatform@123
 * apiuser/apiusercpad@123
 */
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @GetMapping("/properties")
    public ResponseEntity<List<PropertyEntity>> getAllProperties(){

        ResponseEntity responseEntity = null;
        List<PropertyEntity> propertyEntities = new ArrayList<>();
        try {
            propertyEntities = propertyService.getAllProperties();
            responseEntity = new ResponseEntity(propertyEntities, HttpStatus.OK);
        } catch(Exception e){
            log.error(e.getMessage());
            propertyEntities = null;
            responseEntity = new ResponseEntity(propertyEntities, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/create")
    public ResponseEntity<PropertyEntity> createProperty(){

        ResponseEntity responseEntity = null;
        PropertyEntity propertyEntity = null;
        try {
            propertyEntity = propertyService.createProperty();
            responseEntity = new ResponseEntity(propertyEntity, HttpStatus.OK);
        } catch(Exception e){
            log.error(e.getMessage());
            propertyEntity = null;
            responseEntity = new ResponseEntity(propertyEntity, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
