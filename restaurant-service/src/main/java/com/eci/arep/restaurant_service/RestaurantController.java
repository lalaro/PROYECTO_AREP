package com.eci.arep.restaurant_service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {

    private final List<String> restaurants = new ArrayList<>();

    @GetMapping
    public List<String> listRestaurants() {
        return restaurants;
    }

    @PostMapping
    public String createRestaurant() {
        restaurants.add("Nuevo Restaurante " + (restaurants.size() + 1));
        return "Restaurant created";
    }

    @GetMapping("/test")
    public String test() {
        return "Restaurant service OK";
    }
}