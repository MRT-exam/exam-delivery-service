package com.mtgo.exam.deliveryservice.message;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class DeliveryClaimedMessage {
    private int id;
    private String restaurantId;

}
