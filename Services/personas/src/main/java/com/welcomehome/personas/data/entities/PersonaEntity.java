package com.welcomehome.personas.data.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

@Document
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonaEntity {

    @Id
    private Integer id;
    private String name;
    private String email;
    private String password;
    private String type;
}
