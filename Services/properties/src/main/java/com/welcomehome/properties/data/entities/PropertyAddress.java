package com.welcomehome.properties.data.entities;

import lombok.*;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class PropertyAddress {

    private String number;
    private String street;
    private String city;
    private Integer pincode;
}
