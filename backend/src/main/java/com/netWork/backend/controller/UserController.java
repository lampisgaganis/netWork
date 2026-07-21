package com.netWork.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.netWork.backend.dto.UserProfileResponse;
import com.netWork.backend.dto.UserResponse;
import com.netWork.backend.mapper.UserMapper;
import com.netWork.backend.security.CustomUserDetails;
import com.netWork.backend.service.UserService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public UserResponse getCurrentUser(Authentication authentication) {
        
        CustomUserDetails details = (CustomUserDetails) authentication.getPrincipal();

        return UserMapper.toResponse(details.getUser());
    }

    @GetMapping("/{id}")
    public UserProfileResponse getUserProfile(@PathVariable Long id){
        return userService.getProfile(id);
    }

    @GetMapping("/search")
    public List<UserProfileResponse> searchUsers(@RequestParam String q){
        return userService.searchUsers(q);
    }
}
