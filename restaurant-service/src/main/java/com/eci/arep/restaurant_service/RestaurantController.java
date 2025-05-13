package com.eci.arep.restaurant_service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {

    @GetMapping
    public String listRestaurants() {
        // Logic to list restaurants
        return "List of restaurants";
    }

    @PostMapping
    public String createRestaurant() {
        // Logic to create a restaurant
        return "Restaurant created";
    }
}
