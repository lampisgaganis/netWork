package com.netWork.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(
    @NotBlank String firstName,
    @NotBlank String lastName,
    @Email @NotBlank String email,
    @NotBlank String phone,
    String photoUrl,
    @NotBlank String password,
    @NotBlank String confirmPassword
) {}
