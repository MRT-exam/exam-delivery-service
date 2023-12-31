package com.mtgo.exam.deliveryservice.service;

import com.mtgo.exam.deliveryservice.dto.DeliveryDto;
import com.mtgo.exam.deliveryservice.enums.DeliveryStatus;
import com.mtgo.exam.deliveryservice.model.Delivery;

import java.io.IOException;
import java.util.List;

public interface IDeliveryService {

    DeliveryDto updateDeliveryStatus(int deliveryId, DeliveryStatus deliveryStatus);
    Delivery claimOrderForDelivery(int orderId) throws IOException;
    List<DeliveryDto> getDeliveriesByStatus(DeliveryStatus deliveryStatus);
}
