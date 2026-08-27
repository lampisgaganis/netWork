package com.netWork.backend.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
    @NotBlank String firstName,

    @NotBlank String lastName,

    @Email @NotBlank String email,

    @NotBlank String phone,

    String photoUrl,

    @NotBlank
    @Size(min = 12 , max = 64, 
        message = "Password must be between 12 and 64 characters")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{12,64}$", 
        message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character")
    
    String password,

    @NotBlank 
    @Size(max = 64)
    String confirmPassword
) {
    @AssertTrue(message = "Passwords do not match")
    boolean isPasswordConfirmed() {
        return password != null && password.equals(confirmPassword);
    }
}
