package com.welcomehome.personas.data.entities;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoginResponse {

    private Integer customerId;
    private String status;
    private String message;
}
