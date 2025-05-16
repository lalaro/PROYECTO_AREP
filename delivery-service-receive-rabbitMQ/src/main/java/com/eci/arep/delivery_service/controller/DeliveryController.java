package com.eci.arep.delivery_service.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/delivery")
public class DeliveryController {

    private final List<String> deliveries = new ArrayList<>();

    @GetMapping
    public List<String> listDeliveries() {
        return deliveries;
    }

    @PostMapping
    public String createDelivery() {
        deliveries.add("Nueva Entrega " + (deliveries.size() + 1));
        return "Delivery created";
    }
}