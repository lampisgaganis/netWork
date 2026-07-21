package com.netWork.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netWork.backend.dto.UserResponse;
import com.netWork.backend.mapper.UserMapper;
import com.netWork.backend.security.CustomUserDetails;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    @GetMapping("/me")
    public UserResponse getCurrentUser(Authentication authentication) {
        
        CustomUserDetails details = (CustomUserDetails) authentication.getPrincipal();

        return UserMapper.toResponse(details.getUser());
    }
}
