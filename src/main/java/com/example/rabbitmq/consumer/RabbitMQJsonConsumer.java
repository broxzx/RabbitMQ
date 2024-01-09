package com.example.rabbitmq.consumer;

import com.example.rabbitmq.dto.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
@Log4j2
public class RabbitMQJsonConsumer {

    @RabbitListener(queues = {"${spring.rabbitmq.queue.json.name}"})
    public void consumeJsonMessage(User payload) {
        log.info("Message was received: %s".formatted(payload.toString()));
    }
}
