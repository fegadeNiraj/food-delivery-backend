package com.niraj.food_delivery_backend.repository;

import com.niraj.food_delivery_backend.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Query("""
            SELECT DISTINCT r
            FROM Restaurant r
            LEFT JOIN FETCH r.menuItems
            """)
//    @EntityGraph(attributePaths = "menuItems")
    List<Restaurant> findAllWithMenuItems();
}
