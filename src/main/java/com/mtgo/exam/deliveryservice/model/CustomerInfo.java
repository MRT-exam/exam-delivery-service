package com.mtgo.exam.deliveryservice.model;

import jakarta.persistence.Embeddable;
import lombok.*;

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
