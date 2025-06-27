package com.fooddelivery.quickbite.restaurant_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fooddelivery.quickbite.restaurant_service.dto.*;
import com.fooddelivery.quickbite.restaurant_service.model.*;
import com.fooddelivery.quickbite.restaurant_service.repository.*;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepo;
    private final MenuItemRepository menuItemRepo;

    // ✅ Create a new restaurant from DTO
    public RestaurantResponseDTO createRestaurant(RestaurantRequestDTO dto) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(dto.getName());
        restaurant.setLocation(dto.getLocation());
        restaurant.setCuisine(dto.getCuisine());

        Restaurant saved = restaurantRepo.save(restaurant);
        return mapToRestaurantDTO(saved);
    }

    // ✅ Get all restaurants
    public List<RestaurantResponseDTO> getAllRestaurants() {
        return restaurantRepo.findAll().stream()
                .map(this::mapToRestaurantDTO)
                .collect(Collectors.toList());
    }

    // ✅ Get one restaurant by ID
    public RestaurantResponseDTO getRestaurantById(Long id) {
        Restaurant restaurant = restaurantRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found with ID: " + id));
        return mapToRestaurantDTO(restaurant);
    }

    // ✅ Add a menu item to a restaurant
    public MenuItemResponseDTO addMenuItemToRestaurant(Long restaurantId, MenuItemRequestDTO dto) {
        Restaurant restaurant = restaurantRepo.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        MenuItem item = new MenuItem();
        item.setName(dto.getName());
        item.setPrice(dto.getPrice());
        item.setRestaurant(restaurant);

        MenuItem savedItem = menuItemRepo.save(item);
        return mapToMenuItemDTO(savedItem);
    }

    // ✅ Mapping logic
    private RestaurantResponseDTO mapToRestaurantDTO(Restaurant restaurant) {
        RestaurantResponseDTO dto = new RestaurantResponseDTO();
        dto.setId(restaurant.getId());
        dto.setName(restaurant.getName());
        dto.setLocation(restaurant.getLocation());
        dto.setCuisine(restaurant.getCuisine());

        if (restaurant.getMenuItems() != null) {
            List<MenuItemResponseDTO> menuItemDTOs = restaurant.getMenuItems().stream()
                    .map(this::mapToMenuItemDTO)
                    .collect(Collectors.toList());
            dto.setMenuItems(menuItemDTOs);
        }

        return dto;
    }

    private MenuItemResponseDTO mapToMenuItemDTO(MenuItem item) {
        MenuItemResponseDTO dto = new MenuItemResponseDTO();
        dto.setId(item.getId());
        dto.setName(item.getName());
        dto.setPrice(item.getPrice());
        return dto;
    }
}
