package com.fooddelivery.payment_service.dto;

import lombok.Data;

@Data
public class PaymentRequestDTO {
    private Long orderId;
    private String paymentMethod;
    private Double amount;
}

