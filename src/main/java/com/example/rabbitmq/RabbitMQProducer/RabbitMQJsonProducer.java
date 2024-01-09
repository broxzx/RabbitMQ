package com.example.rabbitmq.RabbitMQProducer;

import com.example.rabbitmq.dto.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Log4j2
@RequiredArgsConstructor
public class RabbitMQJsonProducer {

    @Value("${spring.rabbitmq.queue.json.name}")
    public String queueJsonName;

    @Value("${spring.rabbitmq.exchange.json.name}")
    public String exchangeJsonName;

    @Value("${spring.rabbitmq.routingKey.json.name}")
    public String routingJsonKeyName;

    private final RabbitMessagingTemplate rabbitMessagingTemplate;

    public void sendJsonMessage(User payload) {
        rabbitMessagingTemplate.convertAndSend(exchangeJsonName, routingJsonKeyName, payload);
        log.info("message %s from class RabbitMQJsonProducer was sent".formatted(payload));
    }
}
