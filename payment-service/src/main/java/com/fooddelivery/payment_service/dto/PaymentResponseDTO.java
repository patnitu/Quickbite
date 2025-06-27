package com.fooddelivery.payment_service.dto;

import com.fooddelivery.payment_service.model.PaymentStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PaymentResponseDTO {
    private Long id;
    private Long orderId;
    private String paymentMethod;
    private Double amount;
    private PaymentStatus status;
    private LocalDateTime createdAt;
}
