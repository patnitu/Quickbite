package com.fooddelivery.order_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fooddelivery.order_service.dto.OrderRequestDTO;
import com.fooddelivery.order_service.dto.OrderResponseDTO;
import com.fooddelivery.order_service.dto.PaymentRequestDTO;
import com.fooddelivery.order_service.model.Order;
import com.fooddelivery.order_service.model.OrderStatus;
import com.fooddelivery.order_service.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
	private final OrderRepository orderRepository;
	private final WebClient.Builder webClientBuilder;

	@Override
	public OrderResponseDTO placeOrder(OrderRequestDTO request) {
		Order order = Order.builder().userId(request.getUserId()).restaurantId(request.getRestaurantId())
				.items(request.getItems()).totalAmount(request.getTotalAmount()).status(OrderStatus.PLACED).build();
		Order savedOrder = orderRepository.save(order);

		// 🔁 Call payment-service using WebClient
		PaymentRequestDTO paymentRequest = new PaymentRequestDTO();
		paymentRequest.setOrderId(savedOrder.getId());
		paymentRequest.setAmount(savedOrder.getTotalAmount());
		paymentRequest.setPaymentMethod("UPI"); // hardcoded for now

		webClientBuilder.build().post().uri("http://payment-service/api/payments").bodyValue(paymentRequest).retrieve()
				.bodyToMono(Void.class).subscribe(); // fire and forget (non-blocking)

		return mapToDTO(orderRepository.save(savedOrder));
	}

	private OrderResponseDTO mapToDTO(Order order) {
		return OrderResponseDTO.builder().orderId(order.getId()).userId(order.getUserId())
				.restaurantId(order.getRestaurantId()).items(order.getItems()).totalAmount(order.getTotalAmount())
				.status(order.getStatus()).createdAt(order.getCreatedAt()).updatedAt(order.getUpdatedAt()).build();
	}

	@Override
	public List<OrderResponseDTO> getAllOrders() {
		List<Order> orders = orderRepository.findAll();
		return orders.stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	@Override
	public OrderResponseDTO updateOrderStatus(Long orderId, String status) {
		Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));

		order.setStatus(OrderStatus.valueOf(status.toUpperCase()));
		Order updatedOrder = orderRepository.save(order);

		return mapToDTO(updatedOrder);
	}

}
