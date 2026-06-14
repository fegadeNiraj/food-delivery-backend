package com.niraj.food_delivery_backend.controller;


import com.niraj.food_delivery_backend.dto.MenuItemResponseDto;
import com.niraj.food_delivery_backend.dto.RestaurantRequestDto;
import com.niraj.food_delivery_backend.dto.RestaurantResponseDto;
import com.niraj.food_delivery_backend.service.MenuItemService;
import com.niraj.food_delivery_backend.service.RestaurantService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final MenuItemService menuItemService;

    public RestaurantController(RestaurantService restaurantService,MenuItemService menuItemService) {
        this.restaurantService = restaurantService;
        this.menuItemService = menuItemService;
    }

    @GetMapping()
    public List<RestaurantResponseDto> getRestaurants() {
        return restaurantService.getAllRestaurants();
    }


    @PostMapping()
    public RestaurantResponseDto createRestaurant(@Valid @RequestBody RestaurantRequestDto requestDto) {
        return restaurantService.createRestaurant(requestDto);
    }

    @GetMapping("/{id}")
    public RestaurantResponseDto getRestaurant(@PathVariable Long id)
    {
        return restaurantService.getRestaurant(id);
    }

    @PutMapping("/{id}")
    public RestaurantResponseDto updateRestaurant(@Valid @RequestBody RestaurantRequestDto requestDto,@PathVariable Long id)
    {
        return restaurantService.updateRestaurant(requestDto,id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id)
    {
        restaurantService.deleteRestaurant(id);
        return ResponseEntity.noContent().build();
    }


//    @GetMapping("/lazy/{restaurantId}")
//    public Integer testLazy(@PathVariable Long restaurantId)
//    {
//        return restaurantService.testLazyLoading(restaurantId);
//    }

//    @GetMapping("/testPlusOne")
//    public void testPlusOne()
//    {
//        restaurantService.testPlusOne();
//    }

}