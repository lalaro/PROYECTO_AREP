package com.eci.arep.redis_service.config;

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
    @Value("${app.rabbitmq.queuePremium}")
    private String queuePremium;


    @Value("${app.rabbitmq.exchange}")
    private String exchangeName;


    @Value("${app.rabbitmq.routingkey}")
    private String routingKey;
    @Value("${app.rabbitmq.routingKeyQueuePremium}")
    private String routingKeyQueuePremium;




    @Bean
    Queue queue() {
        // durable: true - la cola sobrevive a reinicios del broker
        return new Queue(queueName, true);
    }
    @Bean
    Queue queuePremium() {
        return new Queue(queuePremium, true);
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
    @Bean
    Binding bindingPremium(Queue queuePremium, DirectExchange exchange) {
        return BindingBuilder.bind(queuePremium).to(exchange).with(routingKeyQueuePremium);
    }
}