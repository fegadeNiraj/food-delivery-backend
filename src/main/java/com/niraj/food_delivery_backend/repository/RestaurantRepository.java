package com.niraj.food_delivery_backend.repository;

import com.niraj.food_delivery_backend.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
