package com.mtgo.exam.deliveryservice.controller;

import com.mtgo.exam.deliveryservice.dto.DeliveryDto;
import com.mtgo.exam.deliveryservice.enums.DeliveryStatus;
import com.mtgo.exam.deliveryservice.message.DeliveryClaimedMessage;
import com.mtgo.exam.deliveryservice.message.DeliveryCompletedMessage;
import com.mtgo.exam.deliveryservice.model.Delivery;
import com.mtgo.exam.deliveryservice.producer.DeliveryClaimedMessageProducer;
import com.mtgo.exam.deliveryservice.producer.DeliveryCompletedMessageProducer;
import com.mtgo.exam.deliveryservice.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {

    private final DeliveryService deliveryService;
    private final DeliveryClaimedMessageProducer deliveryClaimedMessageProducer;
    private final DeliveryCompletedMessageProducer deliveryCompletedMessageProducer;
    // private final DeliveryGrpcClientImpl deliveryGrpcClient;

    @GetMapping("")
    public DeliveryDto getClaimableOrders() {
        // TODO: GraphQL


        return new DeliveryDto();
    }
    @PostMapping("/claim/{orderId}")
    public ResponseEntity<Delivery> claimOrderForDelivery(@PathVariable int orderId) throws IOException {
        // GRPC attempt (Not Working)
        // TODO - Call gRPC in Order Service to receive the order if status: ACCEPTED
        // Map<FieldDescriptor, Object> orderFields = deliveryGrpcClient.getOrderById(orderId);
        // TODO - Persist new Delivery Entity and update Order Status to CLAIMED in Order Service

        // TODO: GraphQL - Lets gooo
        Delivery delivery = deliveryService.claimOrderForDelivery(orderId);

        // Send event message to notification service
        DeliveryClaimedMessage deliveryClaimedMessage = DeliveryClaimedMessage.builder()
                        .id(1)
                        .restaurantId("restaurant1")
                        .build();
        deliveryClaimedMessageProducer.sendDeliveryClaimedMessage(deliveryClaimedMessage);
        return new ResponseEntity<>(delivery, HttpStatus.OK);
    }

    @PostMapping("/complete/{deliveryId}")
    public DeliveryDto completeDelivery(@PathVariable int deliveryId) {
        deliveryService.updateDeliveryStatus(deliveryId, DeliveryStatus.COMPLETED);

        // TODO: Make order-service update order status so that status is consistent

        // Send event message to notification service
        DeliveryCompletedMessage deliveryCompletedMessage = DeliveryCompletedMessage.builder()
                        .id(2)
                        .restaurantId("restaurant2")
                        .build();
        deliveryCompletedMessageProducer.sendDeliveryCompletedMessage(deliveryCompletedMessage);
        return new DeliveryDto();
    }

}
