package com.niraj.food_delivery_backend.service;


import com.niraj.food_delivery_backend.dto.RestaurantRequestDto;
import com.niraj.food_delivery_backend.dto.RestaurantResponseDto;
import com.niraj.food_delivery_backend.entity.Restaurant;
import com.niraj.food_delivery_backend.exception.RestaurantNotFoundException;
import com.niraj.food_delivery_backend.repository.RestaurantRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Transactional
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

        return new RestaurantResponseDto(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getAddress()
        );

    }

    public void deleteRestaurant(Long id)
    {
        restaurantRepository.findById(id).orElseThrow(
                ()-> new RestaurantNotFoundException(
                        "Restaurant Not Found with ID : "+id
                )
        );
        restaurantRepository.deleteById(id);
    }

    public List<RestaurantResponseDto> searchRestaurants(String keyword)
    {
        List<Restaurant> restaurants = restaurantRepository.findByNameContainingIgnoreCase(keyword);

        return restaurants.stream()
                .map(restaurant -> new RestaurantResponseDto(
                        restaurant.getId(),
                        restaurant.getName(),
                        restaurant.getAddress()
                )).toList();

    }



//    public Integer testLazyLoading(Long restaurantId)
//    {
//        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(
//                ()->new RestaurantNotFoundException("No Restaurant found with ID : "+restaurantId)
//        );
//
//        System.out.println(restaurant.getName());
//        return restaurant.getMenuItems().size();
//
//    }


//    @Transactional
//    public void testPlusOne()
//    {
//        List<Restaurant> restaurants = restaurantRepository.findAllWithMenuItems();
//
//        for(Restaurant restaurant : restaurants)
//        {
//            System.out.println(restaurant.getMenuItems().size());
//        }
//    }
}
