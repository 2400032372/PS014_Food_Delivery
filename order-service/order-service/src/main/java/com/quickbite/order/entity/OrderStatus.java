package com.quickbite.order.entity;

public enum OrderStatus {
    CREATED,
    PAYMENT_PENDING,
    PAID,
    ACCEPTED,
    REJECTED,
    PREPARING,
    OUT_FOR_DELIVERY,
    DELIVERED,
    CANCELLED
}
