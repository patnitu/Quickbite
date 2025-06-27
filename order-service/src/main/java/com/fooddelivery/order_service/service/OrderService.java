package com.fooddelivery.order_service.service;

import java.util.List;

import com.fooddelivery.order_service.dto.OrderRequestDTO;
import com.fooddelivery.order_service.dto.OrderResponseDTO;
import com.fooddelivery.order_service.model.Order;

public interface OrderService {
	OrderResponseDTO placeOrder(OrderRequestDTO request);

	List<OrderResponseDTO> getAllOrders();

	OrderResponseDTO updateOrderStatus(Long orderId, String status);
}
