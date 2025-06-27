package com.fooddelivery.payment_service.service;

import com.fooddelivery.payment_service.dto.PaymentRequestDTO;
import com.fooddelivery.payment_service.dto.PaymentResponseDTO;

public interface PaymentService {
    PaymentResponseDTO makePayment(PaymentRequestDTO request);
    PaymentResponseDTO getPaymentByOrderId(Long orderId);
}
