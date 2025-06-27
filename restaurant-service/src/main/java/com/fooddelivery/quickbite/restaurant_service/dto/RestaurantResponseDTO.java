package com.fooddelivery.quickbite.restaurant_service.dto;

import java.util.List;

import lombok.Data;

@Data
public class RestaurantResponseDTO {
	private Long id;
    private String name;
    private String location;
    private String cuisine;
    private List<MenuItemResponseDTO> menuItems;
}
