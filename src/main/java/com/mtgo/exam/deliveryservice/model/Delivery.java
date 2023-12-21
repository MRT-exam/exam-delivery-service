package com.mtgo.exam.deliveryservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mtgo.exam.deliveryservice.enums.DeliveryStatus;
import com.mtgo.exam.deliveryservice.utils.DeliveryStatusConverter;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "delivery")
public class Delivery {
    @Id
    @Column(name = "delivery_id")
    private int id;
    private String restaurantId;
    @Convert(converter = DeliveryStatusConverter.class)
    private DeliveryStatus status;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "yyyy-MM-dd-HH:mm")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH:mm")
    private LocalDateTime deliveryDateTime;
    @OneToMany(cascade = CascadeType.ALL)
    private List<DeliveryItem> deliveryItems;
    private String comment;
    private CustomerInfo customerInfo;
}
