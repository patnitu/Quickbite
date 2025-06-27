package com.fooddelivery.order_service.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

import com.fooddelivery.order_service.model.OrderStatus;

@Data
@Builder
public class OrderResponseDTO {
	private Long orderId;
	private Long userId;
	private Long restaurantId;
	private String items;
	private Double totalAmount;
	private OrderStatus status;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
