package com.fooddelivery.payment_service.repository;

import com.fooddelivery.payment_service.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}

