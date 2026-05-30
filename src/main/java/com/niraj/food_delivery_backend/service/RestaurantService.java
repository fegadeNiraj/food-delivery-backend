package com.niraj.food_delivery_backend.service;


import com.niraj.food_delivery_backend.dto.RestaurantRequestDto;
import com.niraj.food_delivery_backend.dto.RestaurantResponseDto;
import com.niraj.food_delivery_backend.entity.Restaurant;
import com.niraj.food_delivery_backend.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository)
    {
        this.restaurantRepository = restaurantRepository;
    }


    public RestaurantResponseDto createRestaurant(RestaurantRequestDto requestDto) {
        Restaurant restaurant = new Restaurant();

        restaurant.setName(requestDto.getName());
        restaurant.setAddress(requestDto.getAddress());
        restaurant.setPhoneNumber(requestDto.getPhoneNumber());

        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        return new RestaurantResponseDto(
                savedRestaurant.getId(),
                savedRestaurant.getName(),
                savedRestaurant.getAddress()
        );
    }

}
