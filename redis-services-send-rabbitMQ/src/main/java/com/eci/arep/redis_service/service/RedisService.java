package com.eci.arep.redis_service.service;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;  
import com.eci.arep.redis_service.model.RequestPayload;
@Service
public class RedisService {

    private final RedisTemplate<String, RequestPayload> redisTemplate;
    private final static String KEY = "pendingPayloads";

    @Autowired
    private ObjectMapper objectMapper;

    public RedisService(RedisTemplate<String, RequestPayload> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void savePayload(RequestPayload payload) {
        System.out.println("Saving payload: " + payload);
        redisTemplate.opsForList().rightPush(KEY, payload);
    }

    public RequestPayload popNextPayload() {
        Object raw = redisTemplate.opsForList().leftPop(KEY);
        if (raw == null) return null;

        if (raw instanceof RequestPayload) {
            return (RequestPayload) raw;
        } else {
            // Fallback para datos viejos mal serializados
            System.out.println("Objeto deserializado como " + raw.getClass().getName());
            return objectMapper.convertValue(raw, RequestPayload.class);
        }
    }
}
