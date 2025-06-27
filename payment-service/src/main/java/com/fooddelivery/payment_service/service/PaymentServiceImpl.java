package com.fooddelivery.payment_service.service;

import com.fooddelivery.payment_service.dto.PaymentRequestDTO;
import com.fooddelivery.payment_service.dto.PaymentResponseDTO;
import com.fooddelivery.payment_service.model.Payment;
import com.fooddelivery.payment_service.model.PaymentStatus;
import com.fooddelivery.payment_service.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public PaymentResponseDTO makePayment(PaymentRequestDTO request) {
        Payment payment = Payment.builder()
                .orderId(request.getOrderId())
                .paymentMethod(request.getPaymentMethod())
                .amount(request.getAmount())
                .status(PaymentStatus.COMPLETED) // Simulated payment logic
                .build();

        return mapToDTO(paymentRepository.save(payment));
    }

    @Override
    public PaymentResponseDTO getPaymentByOrderId(Long orderId) {
        Payment payment = paymentRepository.findAll().stream()
                .filter(p -> p.getOrderId().equals(orderId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        return mapToDTO(payment);
    }

    private PaymentResponseDTO mapToDTO(Payment payment) {
        return PaymentResponseDTO.builder()
                .id(payment.getId())
                .orderId(payment.getOrderId())
                .paymentMethod(payment.getPaymentMethod())
                .amount(payment.getAmount())
                .status(payment.getStatus())
                .createdAt(payment.getCreatedAt())
                .build();
    }
}

