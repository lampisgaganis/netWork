package com.netWork.backend.controller;

import org.springframework.web.bind.annotation.*;

import com.netWork.backend.dto.AuthResponse;
import com.netWork.backend.dto.LoginRequest;
import com.netWork.backend.dto.RegisterRequest;
import com.netWork.backend.dto.UserResponse;
import com.netWork.backend.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public UserResponse register(@Valid @RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }

}
