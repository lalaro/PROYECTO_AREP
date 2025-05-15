package com.eci.arep.redis_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableScheduling
public class RedisApplication {
	@Autowired
    private RedisTemplate<?, ?> redisTemplate;


	public static void main(String[] args) {
		SpringApplication.run(RedisApplication.class, args);
	}
	@EventListener(ApplicationReadyEvent.class)
    public void clearRedisOnStartup() {
        redisTemplate.getConnectionFactory().getConnection().flushDb();
        System.out.println("Redis database cleared on startup.");
    }
    @Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
