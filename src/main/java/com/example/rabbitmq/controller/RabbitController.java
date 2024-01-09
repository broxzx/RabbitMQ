package com.example.rabbitmq.controller;

import com.example.rabbitmq.RabbitMQProducer.RabbitMQJsonProducer;
import com.example.rabbitmq.RabbitMQProducer.RabbitMQProducer;
import com.example.rabbitmq.dto.User;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RabbitController {

    private final RabbitMQProducer rabbitMQProducer;
    private final RabbitMQJsonProducer rabbitMQJsonProducer;


    @GetMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message) {
        rabbitMQProducer.sendMessage(message);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @PostMapping("/publish/json")
    public ResponseEntity<User> sendJsonMessage(@RequestBody User user) {
        rabbitMQJsonProducer.sendJsonMessage(user);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
