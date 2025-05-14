package com.eci.arep.redis_service.service;

import com.example.redisprocessor.model.RequestPayload;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
    private final RedisTemplate<String, RequestPayload> redisTemplate;
    private final static String KEY = "pendingPayloads";

    public RedisService(RedisTemplate<String, RequestPayload> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void savePayload(RequestPayload payload) {
        redisTemplate.opsForList().rightPush(KEY, payload);
    }

    public RequestPayload popNextPayload() {
        return redisTemplate.opsForList().leftPop(KEY);
    }
}
