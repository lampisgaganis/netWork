package com.netWork.backend.service;

import org.springframework.stereotype.Service;

import com.netWork.backend.dto.UserResponse;
import com.netWork.backend.entity.User;
import com.netWork.backend.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    public UserResponse getCurrentUser(User user) {
        
        return UserMapper.toResponse(user);

    }

}
