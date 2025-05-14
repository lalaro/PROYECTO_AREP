package com.eci.arcn.producer_service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private static final Logger log = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${app.rabbitmq.exchange}")
    private String exchangeName;

    @Value("${app.rabbitmq.routingkey}")
    private String routingKey;

    @PostMapping("/send")
    public String sendMessage(@RequestParam String message) {
        log.info("Enviando mensaje: '{}' a exchange '{}' con routing key '{}'", message, exchangeName, routingKey);
        // Enviar el mensaje al exchange con la routing key definida
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
        // Llama al consumer-service
        restTemplate.postForObject("http://localhost:8081/api/messages/receive", message, String.class);
        // Llama al payment-service
        restTemplate.postForObject("http://localhost:8082/api/payments/process", message, String.class);
        return "Mensaje '" + message + "' enviado a consumer y payment!";
    }
}