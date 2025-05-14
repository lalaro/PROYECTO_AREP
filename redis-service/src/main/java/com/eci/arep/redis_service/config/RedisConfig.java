package com.eci.arep.redis_service.config;

import com.eci.arep.redis_service.model.RequestPayload;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, RequestPayload> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, RequestPayload> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        // Serializador para claves
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());

        // Serializador para valores usando GenericJackson2JsonRedisSerializer en lugar de Jackson2JsonRedisSerializer
        GenericJackson2JsonRedisSerializer serializer = createSecureSerializer();
        
        template.setValueSerializer(serializer);
        template.setHashValueSerializer(serializer);

        template.afterPropertiesSet();
        return template;
    }
    
    private GenericJackson2JsonRedisSerializer createSecureSerializer() {
        ObjectMapper mapper = new ObjectMapper();
        
        // Crear un validador de tipo polimórfico seguro y más restrictivo
        BasicPolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder()
                // Permitir explícitamente solo los paquetes que necesitas serializar
                .allowIfBaseType("com.eci.arep.redis_service.model")
                // Opcional: permitir paquetes de Java necesarios
                .allowIfBaseType("java.util")
                .allowIfBaseType("java.lang")
                .build();
        
        // Configurar el serializador con validación de tipos
        mapper.setPolymorphicTypeValidator(ptv);
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        
        return new GenericJackson2JsonRedisSerializer(mapper);
    }
}