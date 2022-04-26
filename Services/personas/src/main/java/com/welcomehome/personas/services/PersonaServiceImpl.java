package com.welcomehome.personas.services;

import com.welcomehome.personas.data.entities.PersonaEntity;
import com.welcomehome.personas.data.repositories.PersonaRepository;
import com.welcomehome.personas.data.entities.Login;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public List<PersonaEntity> getAllCustomer(){

        List<PersonaEntity> customers= new ArrayList<>();
        try {
            customers = personaRepository.findAll();
        } catch (Exception e){
            log.error(e.getMessage());
            customers = new ArrayList<>();
        }
        return customers;
    }

    @Override
    public List<PersonaEntity> getCustomersByType(String type){

        List<PersonaEntity> customers= new ArrayList<>();
        try {
            customers = personaRepository.findByType(type);
        } catch (Exception e){
            log.error(e.getMessage());
            customers = new ArrayList<>();
        }
        return customers;
    }

    @Override
    public Optional<PersonaEntity> getCustomersByEmail(String email){

        Optional<PersonaEntity> customer = null;
        try {
            customer = personaRepository.findByEmail(email);
        } catch (Exception e){
            log.error(e.getMessage());
        }
        return customer;
    }

    @Override
    public String validateLogin(Login login){
        String status = "login failed.";
        try {
            Optional<PersonaEntity> personaEntity = personaRepository.findByEmail(login.getEmail());
            if(personaEntity.isPresent()){
                if(login.getPassword().equals(personaEntity.get().getPassword())){
                    status = "login success.";
                } else {
                    status = "login failed. Invalid password.";
                }
            } else {
                status = "login failed. Invalid email.";
            }
        } catch(Exception e){
            log.error(e.getMessage());
            status = "login failed. Server error.";
        }
        return status;
    }
}