package com.niraj.food_delivery_backend.controller;


import com.niraj.food_delivery_backend.dto.RestaurantRequestDto;
import com.niraj.food_delivery_backend.dto.RestaurantResponseDto;
import com.niraj.food_delivery_backend.service.RestaurantService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping()
    public RestaurantResponseDto getRestaurants() {
        return new RestaurantResponseDto(1L, "Dominos Pizza", "Jalgaon");
    }


    @PostMapping
    public RestaurantResponseDto createRestaurant(@RequestBody RestaurantRequestDto requestDto) {
        return restaurantService.createRestaurant(requestDto);
    }


}
