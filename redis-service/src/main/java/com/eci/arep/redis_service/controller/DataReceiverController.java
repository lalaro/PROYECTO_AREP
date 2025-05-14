package com.eci.arep.redis_service.controller;

import com.example.redisprocessor.model.RequestPayload;
import com.example.redisprocessor.service.RedisService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/data")
public class DataReceiverController {

    private final RedisService redisService;

    public DataReceiverController(RedisService redisService) {
        this.redisService = redisService;
    }

    @PostMapping
    public ResponseEntity<String> receive(@RequestBody RequestPayload payload) {
        redisService.savePayload(payload);
        return ResponseEntity.ok("Received and stored");
    }
}