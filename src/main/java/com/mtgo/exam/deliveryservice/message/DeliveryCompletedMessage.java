package com.mtgo.exam.deliveryservice.message;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class DeliveryCompletedMessage {
    private int id;
    private String restaurantId;
}
