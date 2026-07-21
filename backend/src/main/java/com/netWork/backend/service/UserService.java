package com.netWork.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.netWork.backend.dto.UserProfileResponse;
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

    public UserResponse getCurrentUser(User user) {
        
        return UserMapper.toResponse(user);

    }

    public UserProfileResponse getProfile(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        return UserMapper.toProfileResponse(user);
    }

    public List<UserProfileResponse> searchUsers(String query) {
        return userRepository.searchUsers(query)
            .stream()
            .map(UserMapper::toProfileResponse)
            .toList();
    }

}
