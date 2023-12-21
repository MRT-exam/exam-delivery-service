package com.mtgo.exam.deliveryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class DeliveryItemDto {
    private int id;
    private String itemId;
    private String itemName;
    private BigDecimal price;
    private int quantity;
}
