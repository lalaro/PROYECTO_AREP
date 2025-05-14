package com.eci.arcn.consumer_service.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @PostMapping("/receive")
    public String receiveMessage(@RequestBody String message) {
        System.out.println("Mensaje recibido en consumer: " + message);
        return "Mensaje recibido en consumer";
    }
}
