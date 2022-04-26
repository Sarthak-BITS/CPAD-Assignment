package com.welcomehome.properties.data.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.stereotype.Service;

import java.util.List;

@Document
@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class PropertyEntity {

    @Id
    @Field
    private Integer propertyId;
    private String propertyType;
    private String propertyName;
    private Integer ownerId;
    private List<String> amenities;
    private PropertyAddress propertyAddress;

}
