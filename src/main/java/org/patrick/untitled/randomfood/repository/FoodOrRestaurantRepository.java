package org.patrick.untitled.randomfood.repository;

import org.patrick.untitled.randomfood.entity.FoodOrRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodOrRestaurantRepository extends JpaRepository<FoodOrRestaurant, String> {
}
