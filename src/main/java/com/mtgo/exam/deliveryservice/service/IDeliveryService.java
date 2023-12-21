package com.mtgo.exam.deliveryservice.service;

import com.mtgo.exam.deliveryservice.dto.DeliveryDto;
import com.mtgo.exam.deliveryservice.enums.DeliveryStatus;

import java.util.List;

public interface IDeliveryService {

    DeliveryDto updateDeliveryStatus(int deliveryId, DeliveryStatus deliveryStatus);
    DeliveryDto createDelivery(DeliveryDto deliveryDto);
    List<DeliveryDto> getDeliveriesByStatus(DeliveryStatus deliveryStatus);
}
