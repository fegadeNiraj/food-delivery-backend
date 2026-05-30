package com.niraj.food_delivery_backend.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantRequestDto {

    private String name;
    private String address;
    private String phoneNumber;
}
