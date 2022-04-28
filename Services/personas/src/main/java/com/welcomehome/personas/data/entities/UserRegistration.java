package com.welcomehome.personas.data.entities;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRegistration {

    private String name;
    private String email;
    private String password;
    private String type;
}
