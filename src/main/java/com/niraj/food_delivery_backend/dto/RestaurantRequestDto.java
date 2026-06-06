package com.niraj.food_delivery_backend.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantRequestDto {

    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @Size(min = 10, max = 11)
    private String phoneNumber;
}
