package com.mtgo.exam.deliveryservice.controller;

import com.mtgo.exam.deliveryservice.dto.DeliveryDto;
import com.mtgo.exam.deliveryservice.enums.DeliveryStatus;
import com.mtgo.exam.deliveryservice.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {

    private final DeliveryService deliveryService;

    @PostMapping("/claim/{orderId}")
    public DeliveryDto claimOrderForDelivery(@PathVariable int deliveryId) {
        // TODO - Call gRPC in Order Service to receive all Orders with status: ACCEPTED
        // TODO - Verify that the provided pathVariable (orderId) is present in the list
        // TODO - Persist new Delivery Entity and send message to Order Service to update status to CLAIMED

        return new DeliveryDto();
    }

    @PostMapping("/complete/{orderId}")
    public DeliveryDto completeDelivery(@PathVariable int deliveryId) {
        deliveryService.updateDeliveryStatus(deliveryId, DeliveryStatus.COMPLETED);
        return new DeliveryDto();
    }

}
