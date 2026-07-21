package com.netWork.backend.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.netWork.backend.entity.User;
import com.netWork.backend.exception.ResourceNotFoundException;
import com.netWork.backend.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CurrentUserService {
    
    private final UserRepository userRepository;

    public User getCurrentUser(Authentication authentication){
        
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        return userRepository.findById(userDetails.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

    }

}
