package com.niraj.food_delivery_backend.controller;


import com.niraj.food_delivery_backend.dto.RestaurantRequestDto;
import com.niraj.food_delivery_backend.dto.RestaurantResponseDto;
import com.niraj.food_delivery_backend.service.RestaurantService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/GetAllRestaurants")
    public List<RestaurantResponseDto> getRestaurants() {
        return restaurantService.getAllRestaurants();
    }


    @PostMapping("/AddRestaurant")
    public RestaurantResponseDto createRestaurant(@Valid @RequestBody RestaurantRequestDto requestDto) {
        return restaurantService.createRestaurant(requestDto);
    }

    @GetMapping("/GetRestaurant/{id}")
    public RestaurantResponseDto getRestaurant(@PathVariable Long id)
    {
        return restaurantService.getRestaurant(id);
    }


}