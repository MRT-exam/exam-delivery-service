package com.mtgo.exam.deliveryservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "delivery_item")
public class DeliveryItem {
    @Id
    @Column(name = "delivery_item_id")
    private int id;
    private String itemId;
    private String itemName;
    private int quantity;
}
