package com.fooddelivery.order_service.dto;

import lombok.Data;

@Data
public class OrderRequestDTO {
	private Long userId;
	private Long restaurantId;
	private String items;
	private Double totalAmount;
}
