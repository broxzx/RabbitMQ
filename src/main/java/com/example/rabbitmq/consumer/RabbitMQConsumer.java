package com.example.rabbitmq.consumer;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class RabbitMQConsumer {


    @RabbitListener(queues = {"${spring.rabbitmq.queue.name}"})
    public void consume(String message) {
        log.info("Message was obtained: %s".formatted(message));
    }
}
