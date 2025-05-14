package com.eci.arep.payment_service.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
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
}
