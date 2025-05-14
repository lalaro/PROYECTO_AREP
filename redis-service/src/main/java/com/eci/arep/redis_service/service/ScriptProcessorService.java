package com.eci.arep.redis_service.service;

import com.eci.arep.redis_service.model.RequestPayload;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScriptProcessorService {
    private final RedisService redisService;
    //private final RabbitPublisherService rabbitPublisher;

    public ScriptProcessorService(RedisService redisService){ //RabbitPublisherService rabbitPublisher) {
        this.redisService = redisService;
        //this.rabbitPublisher = rabbitPublisher;
    }

    @Scheduled(fixedRate = 5000)
    public void processPendingPayloads() {
        RequestPayload payload;
        while ((payload = redisService.popNextPayload()) != null) {
            String result = runScript(payload);
            //rabbitPublisher.sendToQueue(result);
        }
    }

    private String runScript(RequestPayload payload) {
        return "processed: " + payload.getData();
    }
}