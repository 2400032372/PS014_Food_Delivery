package com.quickbite.auth.dto;

public record AuthResponse(
        String message,
        String token
) {
}
