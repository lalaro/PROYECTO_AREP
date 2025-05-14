package com.eci.arep.redis_service.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import com.example.redisprocessor.model.RequestPayload;

@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String, RequestPayload> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, RequestPayload> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setDefaultSerializer(new Jackson2JsonRedisSerializer<>(RequestPayload.class));
        return template;
    }
}