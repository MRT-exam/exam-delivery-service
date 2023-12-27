package com.mtgo.exam.deliveryservice.controller;

import com.google.protobuf.Descriptors.*;
import com.mtgo.exam.deliveryservice.dto.DeliveryDto;
import com.mtgo.exam.deliveryservice.enums.DeliveryStatus;
import com.mtgo.exam.deliveryservice.grpc.client.DeliveryGrpcClientImpl;
import com.mtgo.exam.deliveryservice.message.DeliveryClaimedMessage;
import com.mtgo.exam.deliveryservice.message.DeliveryCompletedMessage;
import com.mtgo.exam.deliveryservice.producer.DeliveryClaimedMessageProducer;
import com.mtgo.exam.deliveryservice.producer.DeliveryCompletedMessageProducer;
import com.mtgo.exam.deliveryservice.service.DeliveryService;
import com.mtgo.exam.grpcinterface.Order;
import com.mtgo.exam.grpcinterface.OrderResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {

    private final DeliveryService deliveryService;
    private final DeliveryClaimedMessageProducer deliveryClaimedMessageProducer;
    private final DeliveryCompletedMessageProducer deliveryCompletedMessageProducer;
    private final DeliveryGrpcClientImpl deliveryGrpcClient;

    @PostMapping("")
    public DeliveryDto getClaimableOrders(@PathVariable DeliveryStatus status) {
        // TODO - Call gRPC in Order Service to receive all Orders with status: ACCEPTED


        return new DeliveryDto();
    }
    @PostMapping("/claim/{orderId}")
    public DeliveryDto claimOrderForDelivery(@PathVariable int orderId) {
        Map<FieldDescriptor, Object> orderFields = deliveryGrpcClient.getOrderById(orderId);
        String comment = (String) orderFields.get(OrderResponse.getDescriptor().findFieldByName("coment"));
        System.out.println(comment);
        // TODO - Call gRPC in Order Service to receive all Orders with status: ACCEPTED
        // Get all Accepted Orders
        // TODO - Verify that the provided pathVariable (orderId) is present in the list

        // TODO - Persist new Delivery Entity and update Order Status to CLAIMED in Order Service

        // Send event message to notification service
        DeliveryClaimedMessage deliveryClaimedMessage = DeliveryClaimedMessage.builder()
                        .id(1)
                        .restaurantId("restaurant1")
                        .build();
        deliveryClaimedMessageProducer.sendDeliveryClaimedMessage(deliveryClaimedMessage);
        return new DeliveryDto();
    }

    @PostMapping("/complete/{orderId}")
    public DeliveryDto completeDelivery(@PathVariable int deliveryId) {
        deliveryService.updateDeliveryStatus(deliveryId, DeliveryStatus.COMPLETED);

        // Send event message to notification service
        DeliveryCompletedMessage deliveryCompletedMessage = DeliveryCompletedMessage.builder()
                        .id(2)
                        .restaurantId("restaurant2")
                        .build();
        deliveryCompletedMessageProducer.sendDeliveryCompletedMessage(deliveryCompletedMessage);
        return new DeliveryDto();
    }

}
