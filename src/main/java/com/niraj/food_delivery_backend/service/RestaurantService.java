package com.niraj.food_delivery_backend.service;


import com.niraj.food_delivery_backend.dto.RestaurantRequestDto;
import com.niraj.food_delivery_backend.dto.RestaurantResponseDto;
import com.niraj.food_delivery_backend.entity.Restaurant;
import com.niraj.food_delivery_backend.exception.RestaurantNotFoundException;
import com.niraj.food_delivery_backend.repository.RestaurantRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<RestaurantResponseDto> getAllRestaurants(){
        List<Restaurant> restaurants = restaurantRepository.findAll();

        return  restaurants.stream()
                .map(restaurant -> new RestaurantResponseDto(
                        restaurant.getId(),
                        restaurant.getName(),
                        restaurant.getAddress()
                )).toList();
        //map is used to convert restaurant object in restaurant response DTO objects.

    }

    public RestaurantResponseDto getRestaurant(Long id)
    {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(
                () -> new RestaurantNotFoundException(
                        "Restaurant Not Found with ID : "+id
                )
        );
        // optional is used because findById resource may not be present
        //so to encounter NPE we will use Optional methods like orElse, orElseThrow, ifPresent.
        return new RestaurantResponseDto(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getAddress()
        );

    }

    public RestaurantResponseDto updateRestaurant(RestaurantRequestDto requestDto, Long id)
    {


        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(
                ()-> new RestaurantNotFoundException(
                        "Restaurant Not Found with ID : " +id
                )
        );

        restaurant.setName(requestDto.getName());
        restaurant.setAddress(requestDto.getAddress());
        restaurant.setPhoneNumber(requestDto.getPhoneNumber());

        Restaurant updatedRestaurant = restaurantRepository.save(restaurant);

        return new RestaurantResponseDto(
                updatedRestaurant.getId(),
                updatedRestaurant.getName(),
                updatedRestaurant.getAddress()
        );

    }

    public ResponseEntity<Void> deleteRestaurant(Long id)
    {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(
                ()-> new RestaurantNotFoundException(
                        "Restaurant Not Found with ID : "+id
                )
        );
        restaurantRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
