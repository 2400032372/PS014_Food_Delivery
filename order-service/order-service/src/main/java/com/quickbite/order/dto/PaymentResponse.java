package com.quickbite.order.dto;

public record PaymentResponse(
        boolean success,
        String message
) {
}
