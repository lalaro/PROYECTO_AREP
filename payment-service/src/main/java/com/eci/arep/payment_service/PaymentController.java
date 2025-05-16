package com.eci.arep.payment_service.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final List<String> payments = new ArrayList<>();

    @GetMapping
    public List<String> listPayments() {
        return payments;
    }

    @PostMapping
    public String createPayment() {
        payments.add("Nuevo Pago " + (payments.size() + 1));
        return "Payment created";
    }

    @PostMapping("/process")
    public String processPayment(@RequestBody String message) {
        System.out.println("Procesando pago para: " + message);
        return "Pago procesado";
    }

    @GetMapping("/test")
    public String test() {
        RestTemplate restTemplate = new RestTemplate();
        String deliveryResponse = restTemplate.getForObject("http://localhost:8083/api/delivery/test", String.class);
        String restaurantResponse = restTemplate.getForObject("http://localhost:8084/api/restaurant/test", String.class);
        return "Payment service OK | " + deliveryResponse + " | " + restaurantResponse;
    }
}
