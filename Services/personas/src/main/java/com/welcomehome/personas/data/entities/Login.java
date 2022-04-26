package com.welcomehome.personas.data.entities;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Login {

    private String email;
    private String password;
}
