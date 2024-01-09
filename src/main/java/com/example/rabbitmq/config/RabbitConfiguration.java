package com.example.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

    @Value("${spring.rabbitmq.queue.name}")
    public String queueName;

    @Value("${spring.rabbitmq.routingKey.name}")
    public String routingKeyName;

    @Value("${spring.rabbitmq.exchange.name}")
    public String exchangeName;

    @Value("${spring.rabbitmq.queue.json.name}")
    public String queueJsonName;

    @Value("${spring.rabbitmq.exchange.json.name}")
    public String exchangeJsonName;

    @Value("${spring.rabbitmq.routingKey.json.name}")
    public String routingJsonKeyName;

    // COMMON RABBITMQ CONFIGURATION

    @Bean
    public Queue queue() {
        return new Queue(queueName, true);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchangeName);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder
                .bind(queue())
                .to(exchange())
                .with(routingKeyName);
    }

    // JSON CONFIGURATION

    @Bean
    public Queue jsonQueue() {
        return new Queue(queueJsonName);
    }

    @Bean
    public TopicExchange topicExchangeJson() {
        return new TopicExchange(exchangeJsonName);
    }

    @Bean
    public Binding bindingJson() {
        return BindingBuilder
                .bind(jsonQueue())
                .to(topicExchangeJson())
                .with(routingJsonKeyName);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());

        return rabbitTemplate;
    }

}
