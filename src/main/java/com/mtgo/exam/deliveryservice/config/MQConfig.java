package com.mtgo.exam.deliveryservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    // Delivery Claimed
    public static final String DELIVERY_CLAIMED_QUEUE = "delivery_claimed_queue";
    public static final String DELIVERY_CLAIMED_EXCHANGE = "delivery_claimed_exchange";
    public static final String DELIVERY_CLAIMED_ROUTING_KEY = "delivery_claimed_routingKey";

    // Delivery Completed
    public static final String DELIVERY_COMPLETED_QUEUE = "delivery_completed_queue";
    public static final String DELIVERY_COMPLETED_EXCHANGE = "delivery_completed_exchange";
    public static final String DELIVERY_COMPLETED_ROUTING_KEY = "delivery_completed_routingKey";

    // Queues
    @Bean
    public Queue deliveryClaimedQueue() {
        return new Queue(DELIVERY_CLAIMED_QUEUE);
    }

    @Bean
    public Queue deliveryCompletedQueue() {
        return new Queue(DELIVERY_COMPLETED_QUEUE);
    }

    // Exchanges
    @Bean
    public TopicExchange deliveryClaimedExchange() {
        return new TopicExchange(DELIVERY_CLAIMED_EXCHANGE);
    }
    @Bean
    public TopicExchange deliveryCompletedExchange() {
        return new TopicExchange(DELIVERY_COMPLETED_EXCHANGE);
    }

    // Bindings
    @Bean
    public Binding deliveryClaimedBinding() {
        return BindingBuilder
                .bind(deliveryClaimedQueue())
                .to(deliveryClaimedExchange())
                .with(DELIVERY_CLAIMED_ROUTING_KEY);
    }

    @Bean
    public Binding deliveryCompletedbinding() {
        return BindingBuilder
                .bind(deliveryCompletedQueue())
                .to(deliveryCompletedExchange())
                .with(DELIVERY_COMPLETED_ROUTING_KEY);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }
}
