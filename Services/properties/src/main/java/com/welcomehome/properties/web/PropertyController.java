package com.welcomehome.properties.web;

import com.welcomehome.properties.data.entities.PropertyDto;
import com.welcomehome.properties.data.entities.PropertyEntity;
import com.welcomehome.properties.services.PropertyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/properties")
@Slf4j
/**
 * Administrator/Crossplatform@123
 * apiuser/apiusercpad@123
 */
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @GetMapping("/listings")
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

    @PostMapping("/create")
    public ResponseEntity<PropertyEntity> createProperty(@RequestBody PropertyDto propertyDto){

        ResponseEntity responseEntity = null;
        String status = null;
        try {
            status = propertyService.createProperty(propertyDto);
            if(status.toLowerCase().contains("fail")){
                responseEntity = new ResponseEntity(status, HttpStatus.NOT_IMPLEMENTED);
            } else {
                responseEntity = new ResponseEntity(status, HttpStatus.CREATED);
            }
        } catch(Exception e){
            log.error(e.getMessage());
            responseEntity = new ResponseEntity("Propert creation failed.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
