package com.mtgo.exam.deliveryservice.model;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "delivery_item_id")
    private int id;
    private String itemId;
    private String itemName;
    private int quantity;
}
