package com.eci.arcn.producer_service.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${app.rabbitmq.queue}")
    private String queueName;

    @Value("${app.rabbitmq.exchange}")
    private String exchangeName;

    @Value("${app.rabbitmq.routingkey}")
    private String routingKey;

    @Bean
    Queue queue() {
        // durable: true - la cola sobrevive a reinicios del broker
        return new Queue(queueName, true);
    }

    @Bean
    DirectExchange exchange() {
        // DirectExchange: Enruta mensajes basados en la routing key exacta
        return new DirectExchange(exchangeName);
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        // Vincula la cola al exchange con la routing key especificada
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }
}