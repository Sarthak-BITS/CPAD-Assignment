package com.welcomehome.personas.services;

import com.welcomehome.personas.data.entities.Login;
import com.welcomehome.personas.data.entities.PersonaEntity;

import java.util.List;
import java.util.Optional;

public interface PersonaService {
    List<PersonaEntity> getAllCustomer();

    List<PersonaEntity> getCustomersByType(String type);

    Optional<PersonaEntity> getCustomersByEmail(String email);

    String validateLogin(Login login);
}
