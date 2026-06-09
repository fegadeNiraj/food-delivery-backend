package com.niraj.food_delivery_backend.repository;

import com.niraj.food_delivery_backend.entity.MenuItem;
import com.niraj.food_delivery_backend.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem,Long> {

    boolean existsByNameAndRestaurant(String name, Restaurant restaurant);

}
