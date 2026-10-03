package com.quickbite.payment.service;

import com.quickbite.payment.dto.PaymentRequest;
import com.quickbite.payment.dto.PaymentResponse;
import com.quickbite.payment.entity.Payment;
import com.quickbite.payment.entity.PaymentStatus;
import com.quickbite.payment.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentService {

    private final PaymentRepository repository;

    public PaymentService(PaymentRepository repository) {
        this.repository = repository;
    }

    public PaymentResponse processPayment(PaymentRequest request) {
        if (request.getAmount() == null ||
                request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            return new PaymentResponse(false, "Invalid payment amount");
        }

        Payment payment = new Payment();
        payment.setOrderId(request.getOrderId());
        payment.setUserId(request.getUserId());
        payment.setAmount(request.getAmount());
        payment.setStatus(PaymentStatus.SUCCESS);

        repository.save(payment);

        return new PaymentResponse(true, "Payment successful");
    }

    public Payment getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found: " + id));
    }

    public Payment getByOrderId(Long orderId) {
        return repository.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Payment not found for order: " + orderId));
    }
}
