package com.fooddelivery.quickbite.restaurant_service.dto;

import lombok.Data;

@Data
public class MenuItemResponseDTO {
	private Long id;
	private String name;
	private double price;
}
