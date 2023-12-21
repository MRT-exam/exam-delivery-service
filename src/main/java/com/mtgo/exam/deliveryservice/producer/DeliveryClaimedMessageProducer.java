package com.mtgo.exam.deliveryservice.producer;

import com.mtgo.exam.deliveryservice.config.MQConfig;
import com.mtgo.exam.deliveryservice.message.DeliveryClaimedMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeliveryClaimedMessageProducer {
    @Autowired
    private RabbitTemplate template;
    public String sendDeliveryClaimedMessage(DeliveryClaimedMessage deliveryClaimedMessage) {
        template.convertAndSend(MQConfig.DELIVERY_CLAIMED_EXCHANGE,
                MQConfig.DELIVERY_CLAIMED_ROUTING_KEY, deliveryClaimedMessage);
        return "Delivery Claimed Message Sent";
    }
}
