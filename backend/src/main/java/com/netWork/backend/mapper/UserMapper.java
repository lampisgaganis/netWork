package com.netWork.backend.mapper;

import com.netWork.backend.dto.UserResponse;
import com.netWork.backend.entity.User;

public class UserMapper {

    public static UserResponse toResponse(User user) {
        return new UserResponse(
            user.getId(),
            user.getFirstName(),
            user.getLastName(),
            user.getEmail(),
            user.getPhone(),
            user.getPhotoUrl(),
            user.getRole()
        );
    }

}
