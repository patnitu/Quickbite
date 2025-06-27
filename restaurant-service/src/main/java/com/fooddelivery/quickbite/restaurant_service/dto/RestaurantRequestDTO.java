package com.fooddelivery.quickbite.restaurant_service.dto;

import lombok.Data;

@Data
public class RestaurantRequestDTO {
	private String name;
	private String location;
	private String cuisine;
}
