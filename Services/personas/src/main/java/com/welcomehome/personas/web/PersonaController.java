package com.welcomehome.personas.web;

import com.welcomehome.personas.data.entities.PersonaEntity;
import com.welcomehome.personas.services.PersonaService;
import com.welcomehome.personas.data.entities.Login;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1")
@Slf4j
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping("/personas")
    public ResponseEntity<List<PersonaEntity>> getAllPersonas(){

        ResponseEntity responseEntity = null;
        List<PersonaEntity> allPersonas = new ArrayList<>();
        try {

            allPersonas = personaService.getAllCustomer();
            responseEntity = new ResponseEntity<>(allPersonas, HttpStatus.OK);
        } catch (Exception e){
            log.error(e.getMessage());
            responseEntity = new ResponseEntity<>(allPersonas,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/customers")
    public ResponseEntity<List<PersonaEntity>> getAllCustomers(){

        ResponseEntity responseEntity = null;
        List<PersonaEntity> allPersonas = new ArrayList<>();
        try {
            allPersonas = personaService.getCustomersByType("Customer");
            responseEntity = new ResponseEntity<>(allPersonas, HttpStatus.OK);
        } catch (Exception e){
            log.error(e.getMessage());
            responseEntity = new ResponseEntity<>(allPersonas,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/agents")
    public ResponseEntity<List<PersonaEntity>> getAllAgents(){

        ResponseEntity responseEntity = null;
        List<PersonaEntity> allPersonas = new ArrayList<>();
        try {
            allPersonas = personaService.getCustomersByType("Agent");
            responseEntity = new ResponseEntity<>(allPersonas, HttpStatus.OK);
        } catch (Exception e){
            log.error(e.getMessage());
            responseEntity = new ResponseEntity<>(allPersonas,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/renters")
    public ResponseEntity<List<PersonaEntity>> getAllRenters(){

        ResponseEntity responseEntity = null;
        List<PersonaEntity> allPersonas = new ArrayList<>();
        try {
            allPersonas = personaService.getCustomersByType("Renter");
            responseEntity = new ResponseEntity<>(allPersonas, HttpStatus.OK);
        } catch (Exception e){
            log.error(e.getMessage());
            responseEntity = new ResponseEntity<>(allPersonas,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/login")
    public ResponseEntity<String> validateLogin(@RequestBody Login login){
            ResponseEntity responseEntity = null;
        try {
            responseEntity = new ResponseEntity<>(personaService.validateLogin(login),HttpStatus.ACCEPTED);
        } catch (Exception e){
            log.error(e.getMessage());
            responseEntity = new ResponseEntity<>("Login failed. Web error.",HttpStatus.ACCEPTED);
        }
        return responseEntity;
    }
}
