package com.fooddelivery.quickbite.restaurant_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fooddelivery.quickbite.restaurant_service.model.MenuItem;
import com.fooddelivery.quickbite.restaurant_service.model.Restaurant;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long>{

	List<MenuItem> findByRestaurantId(Long restaurantId);

}
