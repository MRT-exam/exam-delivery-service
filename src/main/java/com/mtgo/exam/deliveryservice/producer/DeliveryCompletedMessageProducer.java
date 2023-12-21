package com.mtgo.exam.deliveryservice.producer;

import com.mtgo.exam.deliveryservice.config.MQConfig;
import com.mtgo.exam.deliveryservice.message.DeliveryCompletedMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeliveryCompletedMessageProducer {
    @Autowired
    private RabbitTemplate template;
    public String sendDeliveryCompletedMessage(DeliveryCompletedMessage deliveryCompletedMessage) {
        template.convertAndSend(MQConfig.DELIVERY_COMPLETED_EXCHANGE,
                MQConfig.DELIVERY_COMPLETED_ROUTING_KEY, deliveryCompletedMessage);
        return "Delivery Completed Message Sent";
    }
}
