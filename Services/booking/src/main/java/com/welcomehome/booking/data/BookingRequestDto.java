package com.welcomehome.booking.data;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookingRequestDto {

    private Integer customerId;
    private Integer agentId;
    private Integer propertyId;
}
