package com.eci.arcn.consumer_service.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${app.rabbitmq.queue}")
    private String queueName;

    // Declarar la cola aquí asegura que exista si el consumidor inicia primero.
    // Es importante que los parámetros (nombre, durabilidad, etc.) coincidan
    // con la declaración en el productor.
    @Bean
    Queue queue() {
        return new Queue(queueName, true); // Debe ser durable=true igual que en productor
    }
    // Nota: No es estrictamente necesario declarar el Exchange y Binding aquí
    // si el Productor ya lo hace, pero no hace daño y aumenta la resiliencia.
    // Si los declaras, asegúrate que los nombres y tipos coincidan.
}