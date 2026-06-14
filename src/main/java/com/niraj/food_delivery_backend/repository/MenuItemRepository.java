package com.niraj.food_delivery_backend.repository;

import com.niraj.food_delivery_backend.dto.MenuItemResponseDto;
import com.niraj.food_delivery_backend.entity.MenuItem;
import com.niraj.food_delivery_backend.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem,Long> {

    boolean existsByNameAndRestaurant(String name, Restaurant restaurant);

    List<MenuItem> findByRestaurantId(Long id);

}
