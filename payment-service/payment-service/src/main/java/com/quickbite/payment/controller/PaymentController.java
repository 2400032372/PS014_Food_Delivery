package com.quickbite.payment.controller;

import com.quickbite.payment.dto.PaymentRequest;
import com.quickbite.payment.dto.PaymentResponse;
import com.quickbite.payment.entity.Payment;
import com.quickbite.payment.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @PostMapping
    public PaymentResponse processPayment(
            @Valid @RequestBody PaymentRequest request) {
        return service.processPayment(request);
    }

    @GetMapping("/{id}")
    public Payment getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/order/{orderId}")
    public Payment getByOrderId(@PathVariable Long orderId) {
        return service.getByOrderId(orderId);
    }
}
