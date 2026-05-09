package com.netWork.backend.dto;

import com.netWork.backend.entity.Role;

public record UserResponse(
    Long id,
    String firstName,
    String lastName,
    String email,
    String phone,
    String photoUrl,
    Role role
) {}
