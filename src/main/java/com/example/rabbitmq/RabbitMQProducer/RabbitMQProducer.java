package com.example.rabbitmq.RabbitMQProducer;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class RabbitMQProducer {

    @Value("${spring.rabbitmq.queue.name}")
    public String queueName;

    @Value("${spring.rabbitmq.routingKey.name}")
    public String routingKeyName;

    @Value("${spring.rabbitmq.exchange.name}")
    public String exchangeName;

    private final RabbitMessagingTemplate rabbitMessagingTemplate;

    public void sendMessage(String payload) {
        log.info("hello from RabbitMQProducer: %s".formatted(payload));
        rabbitMessagingTemplate.convertAndSend(exchangeName, routingKeyName, payload);
    }

}
