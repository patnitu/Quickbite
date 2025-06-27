package com.fooddelivery.payment_service.controller;

import com.fooddelivery.payment_service.dto.PaymentRequestDTO;
import com.fooddelivery.payment_service.dto.PaymentResponseDTO;
import com.fooddelivery.payment_service.service.PaymentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
@Tag(name = "Payment Controller", description = "APIs for processing and fetching payments")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public PaymentResponseDTO makePayment(@RequestBody PaymentRequestDTO request) {
        return paymentService.makePayment(request);
    }

    @GetMapping("/order/{orderId}")
    public PaymentResponseDTO getPaymentByOrderId(@PathVariable Long orderId) {
        return paymentService.getPaymentByOrderId(orderId);
    }
}

