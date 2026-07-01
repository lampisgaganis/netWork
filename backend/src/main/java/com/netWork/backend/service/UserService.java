package com.netWork.backend.service;

import org.springframework.stereotype.Service;

import com.netWork.backend.dto.UserResponse;
import com.netWork.backend.entity.User;
import com.netWork.backend.exception.ResourceNotFoundException;
import com.netWork.backend.mapper.UserMapper;
import com.netWork.backend.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponse getCurrentUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        return UserMapper.toResponse(user);

    }

}
