package com.eci.arep.redis_service.controller;
import com.eci.arep.redis_service.model.*;
import com.eci.arep.redis_service.service.*;

import com.eci.arep.redis_service.model.RequestPayload;
import com.eci.arep.redis_service.service.RedisService;

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
        if (payload.getData() == null || payload.getData().isEmpty() || payload.getPaied() == null) {
            return ResponseEntity.badRequest().body("Invalid payload");
        }
        redisService.savePayload(payload);
        return ResponseEntity.ok("Received and stored");
    }
}