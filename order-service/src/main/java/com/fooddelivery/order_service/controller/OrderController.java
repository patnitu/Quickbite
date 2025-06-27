package com.fooddelivery.order_service.controller;


import com.fooddelivery.order_service.dto.OrderRequestDTO;
import com.fooddelivery.order_service.dto.OrderResponseDTO;
import com.fooddelivery.order_service.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Tag(name = "Order Controller", description = "APIs for placing and tracking orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @Operation(summary = "Place a new order")
    public OrderResponseDTO placeOrder(@RequestBody OrderRequestDTO request) {
        return orderService.placeOrder(request);
    }

    @GetMapping
    @Operation(summary = "Get all orders")
    public List<OrderResponseDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PutMapping("/{orderId}/status")
    @Operation(summary = "Update order status")
    public OrderResponseDTO updateOrderStatus(
            @PathVariable Long orderId,
            @RequestParam String status
    ) {
        return orderService.updateOrderStatus(orderId, status);
    }
}

