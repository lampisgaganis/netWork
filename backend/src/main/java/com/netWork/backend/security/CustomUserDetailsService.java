package com.netWork.backend.security;

import com.netWork.backend.entity.User;

import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.netWork.backend.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        
        Long userId = Long.parseLong(id);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        
        return new CustomUserDetails(user);
    }
}
