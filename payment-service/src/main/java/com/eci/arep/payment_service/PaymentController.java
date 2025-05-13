package com.eci.arep.payment_service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @PostMapping
    public String processPayment() {
        // Logic to process payment
        return "Payment processed";
    }

    @GetMapping("/{id}")
    public String getPaymentStatus(@PathVariable String id) {
        // Logic to get payment status
        return "Payment status for id: " + id;
    }
}
