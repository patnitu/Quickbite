package com.fooddelivery.quickbite.restaurant_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fooddelivery.quickbite.restaurant_service.dto.*;
import com.fooddelivery.quickbite.restaurant_service.service.RestaurantService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    // ✅ 1. Add a new restaurant
    @PostMapping
    public ResponseEntity<RestaurantResponseDTO> createRestaurant(@RequestBody RestaurantRequestDTO requestDTO) {
        RestaurantResponseDTO responseDTO = restaurantService.createRestaurant(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    // ✅ 2. Get all restaurants
    @GetMapping
    public ResponseEntity<List<RestaurantResponseDTO>> getAllRestaurants() {
        return ResponseEntity.ok(restaurantService.getAllRestaurants());
    }

    // ✅ 3. Get one restaurant by ID
    @GetMapping("/{restaurantId}")
    public ResponseEntity<RestaurantResponseDTO> getRestaurantById(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(restaurantService.getRestaurantById(restaurantId));
    }

    // ✅ 4. Add a menu item to a restaurant
    @PostMapping("/{restaurantId}/menu-items")
    public ResponseEntity<MenuItemResponseDTO> addMenuItem(
            @PathVariable Long restaurantId,
            @RequestBody MenuItemRequestDTO menuItemRequest
    ) {
        return ResponseEntity.ok(restaurantService.addMenuItemToRestaurant(restaurantId, menuItemRequest));
    }

    // ✅ 5. (Optional) If needed: Get all menu items separately
    // This can be skipped if menu items are included in restaurant response
    @GetMapping("/{restaurantId}/menu-items")
    public ResponseEntity<List<MenuItemResponseDTO>> getMenuItems(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(
                restaurantService.getRestaurantById(restaurantId).getMenuItems()
        );
    }
}
