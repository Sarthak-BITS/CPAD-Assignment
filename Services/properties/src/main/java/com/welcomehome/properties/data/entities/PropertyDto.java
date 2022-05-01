package com.welcomehome.properties.data.entities;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PropertyDto {
    private String propertyType;
    private String propertyName;
    private Integer ownerId;
    private List<String> amenities;
    private PropertyAddress propertyAddress;
}
