package com.welcomehome.booking.data;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Booking {

    @Id
    @Field
    private Integer bookingId;
    private Integer customerId;
    private Integer agentId;
    private Integer propertyId;
}
