package com.eci.arep.redis_service.service;

import com.eci.arep.redis_service.model.RequestPayload;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ScriptProcessorService {
    private final RedisService redisService;
    //private final RabbitPublisherService rabbitPublisher;
    private static final Logger log = LoggerFactory.getLogger(ScriptProcessorService.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${app.rabbitmq.exchange}")
    private String exchangeName;

    @Value("${app.rabbitmq.routingkey}")
    private String routingKey;

    @Autowired
    private ObjectMapper objectMapper;

    public ScriptProcessorService(RedisService redisService){ //RabbitPublisherService rabbitPublisher) {
        this.redisService = redisService;
        //this.rabbitPublisher = rabbitPublisher;
    }

    @Scheduled(fixedDelay = 5000)
    public void processPendingPayloads() {
        RequestPayload payload;
        while ((payload = redisService.popNextPayload()) != null) {
            String result = runScript(payload);
            //rabbitPublisher.sendToQueue(result);
        }
    }

    private String runScript(RequestPayload payload) {
        System.out.println("Processing payload: " + payload);
        try {
            String json = objectMapper.writeValueAsString(payload);
            log.info("Enviando mensaje JSON: '{}' a exchange '{}' con routing key '{}'", json, exchangeName, routingKey);
            rabbitTemplate.convertAndSend(exchangeName, routingKey, json);
        } catch (JsonProcessingException e) {
            log.error("Error serializando payload a JSON", e);
        }
        return "processed of script of 5000 ml: " + payload.getData();
    }
}