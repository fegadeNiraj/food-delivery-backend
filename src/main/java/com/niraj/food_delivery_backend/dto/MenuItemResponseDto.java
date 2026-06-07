package com.niraj.food_delivery_backend.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemResponseDto {

    @NotNull
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    @Positive
    private Double price;

    @NotNull
    private Long restaurantId;

}
