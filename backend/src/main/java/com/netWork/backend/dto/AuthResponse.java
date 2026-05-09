package com.netWork.backend.dto;

public record AuthResponse(
    String token,
    UserResponse user
) {}
