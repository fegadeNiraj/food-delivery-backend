package com.niraj.food_delivery_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {
                        "name",
                        "restaurant_id"
                })
        }
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double price;

    @ManyToOne
    private Restaurant restaurant;

}
