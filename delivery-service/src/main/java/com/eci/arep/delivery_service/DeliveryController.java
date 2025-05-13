package com.eci.arep.delivery_service;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/delivery")
public class DeliveryController {

    @GetMapping
    public String listDeliveries() {
        // Logic to list deliveries
        return "List of deliveries";
    }

    @PostMapping
    public String createDelivery() {
        // Logic to create a delivery
        return "Delivery created";
    }
}
