package com.netWork.backend.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.netWork.backend.dto.AuthResponse;
import com.netWork.backend.dto.LoginRequest;
import com.netWork.backend.dto.RegisterRequest;
import com.netWork.backend.dto.UserResponse;
import com.netWork.backend.entity.Role;
import com.netWork.backend.entity.User;
import com.netWork.backend.mapper.UserMapper;
import com.netWork.backend.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    public UserResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new RuntimeException("Email already in use");
        }

        if (!request.password().equals(request.confirmPassword())) {
            throw new RuntimeException("Passwords do not match");
        }

        User user = User.builder()
                    .firstName(request.firstName())
                    .lastName(request.lastName())
                    .email(request.email())
                    .phone(request.phone())
                    .photoUrl(request.photoUrl())
                    .password(passwordEncoder.encode(request.password()))
                    .role(Role.PROFESSIONAL)
                    .build();
        
        User savedUser = userRepository.save(user);

        return UserMapper.toResponse(savedUser);
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email())
                    .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if(!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        return new AuthResponse(
                jwtService.generateToken(user.getEmail()), 
                UserMapper.toResponse(user)
        );
    }

}
