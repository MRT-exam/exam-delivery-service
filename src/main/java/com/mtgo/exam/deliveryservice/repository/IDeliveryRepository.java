package com.mtgo.exam.deliveryservice.repository;

import com.mtgo.exam.deliveryservice.enums.DeliveryStatus;
import com.mtgo.exam.deliveryservice.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDeliveryRepository extends JpaRepository<Delivery, Integer> {
    List<Delivery> findByDeliveryStatus(DeliveryStatus deliveryStatus);
}
