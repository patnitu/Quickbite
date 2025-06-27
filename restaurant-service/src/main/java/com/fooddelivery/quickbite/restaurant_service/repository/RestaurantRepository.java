package com.fooddelivery.quickbite.restaurant_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fooddelivery.quickbite.restaurant_service.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{

}
