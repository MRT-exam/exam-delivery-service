package com.mtgo.exam.deliveryservice.repository;

import com.mtgo.exam.deliveryservice.enums.DeliveryStatus;
import com.mtgo.exam.deliveryservice.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDeliveryRepository extends JpaRepository<Delivery, Integer> {
    List<Delivery> findByDeliveryStatus(DeliveryStatus deliveryStatus);
}
