package com.eci.arep.delivery_service.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {
    private int count = 0;
    private int countPremium = 0;

    private static final Logger log = LoggerFactory.getLogger(MessageListener.class);


    // Escucha en la cola definida en application.properties (a través de la config)
    @RabbitListener(queues = "${app.rabbitmq.queue}")
    public void receiveMessage(String message) {
        log.info("Mensaje recibido a normal: '{}'", message);
        // Aquí puedes añadir la lógica para procesar el mensaje
        // Por ejemplo: guardar en base de datos, llamar a otra API, etc.
        count++;
        System.out.println(">>> Mensaje Procesado a normal van: "+ count +" y el mensaje es:" + message);
    }

    // Escucha en la cola definida en application.properties (a través de la config)
    @RabbitListener(queues = "${app.rabbitmq.queuePremium}") 
    public void receiveMessageQueuePremium(String message) {
        log.info("Mensaje recibido a la COLA PREMIUM: '{}'", message);
        // Aquí puedes añadir la lógica para procesar el mensaje
        // Por ejemplo: guardar en base de datos, llamar a otra API, etc.
        countPremium++;
        System.out.println(">>> Mensaje Procesado por la COLA PREMIUM: " + countPremium +" y el mensaje es:" + message);
    }
}