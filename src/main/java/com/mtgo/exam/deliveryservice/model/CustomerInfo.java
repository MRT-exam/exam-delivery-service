package com.mtgo.exam.deliveryservice.model;

import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@Value
public class CustomerInfo {

    // TODO - Implement value object validation of fields
    int userId;
    String firstName;
    String lastName;
    int phone;
    String address;
}
