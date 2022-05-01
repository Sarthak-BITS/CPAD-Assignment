package com.welcomehome.personas.services;

import com.welcomehome.personas.data.entities.Login;
import com.welcomehome.personas.data.entities.LoginResponse;
import com.welcomehome.personas.data.entities.PersonaEntity;
import com.welcomehome.personas.data.entities.UserRegistration;
import com.welcomehome.personas.data.repositories.PersonaCustomRepository;
import com.welcomehome.personas.data.repositories.PersonaRepository;
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

    @Autowired
    private PersonaCustomRepository personaCustomRepository;

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
    public LoginResponse validateLogin(Login login){
        LoginResponse loginResponse = new LoginResponse();
        try {
            Optional<PersonaEntity> personaEntity = personaRepository.findByEmail(login.getEmail().toLowerCase());
            if(personaEntity.isPresent()){
                if(login.getPassword().equals(personaEntity.get().getPassword())){
                    loginResponse.setStatus("success");
                    loginResponse.setCustomerId(personaEntity.get().getId());
                    loginResponse.setMessage("login success.");
                } else {
                    loginResponse.setStatus("failed");
                    loginResponse.setMessage("login failed. Invalid password.");
                }
            } else {
                loginResponse.setStatus("failed");
                loginResponse.setMessage("login failed. Invalid email.");
            }
        } catch(Exception e){
            log.error(e.getMessage());
            loginResponse.setStatus("failed");
            loginResponse.setMessage("login failed. Server error.");
        }
        return loginResponse;
    }
    @Override
    public String registerUser(UserRegistration userRegistration){
        String status = "Signup failed.";
        PersonaEntity personaEntity = new PersonaEntity();
        Integer newId = 0;
        try {
            personaEntity.setName(userRegistration.getName());
            personaEntity.setEmail(userRegistration.getEmail());
            personaEntity.setPassword(userRegistration.getPassword());
            personaEntity.setType(userRegistration.getType());

            newId = personaCustomRepository.getMaxPersonaId();
            if(newId == null){
                newId = 8000;
            }
            personaEntity.setId(newId+1);
            PersonaEntity personaEntity1 = personaRepository.save(personaEntity);
            if (personaEntity1 != null){
                status = "Signup successful";
            }
        } catch(Exception e){
            log.error(e.getMessage());
            status = "Signup failed.";
        }
        return status;
    }
}