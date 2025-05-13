package com.eci.arcn.consumer_service.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    private static final Logger log = LoggerFactory.getLogger(MessageListener.class);

    // Escucha en la cola definida en application.properties (a través de la config)
    @RabbitListener(queues = "${app.rabbitmq.queue}")
    public void receiveMessage(String message) {
        log.info("Mensaje recibido: '{}'", message);
        // Aquí puedes añadir la lógica para procesar el mensaje
        // Por ejemplo: guardar en base de datos, llamar a otra API, etc.
        System.out.println(">>> Mensaje Procesado: " + message);
    }
}