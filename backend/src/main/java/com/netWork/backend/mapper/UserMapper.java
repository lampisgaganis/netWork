package com.netWork.backend.mapper;

import com.netWork.backend.dto.UserProfileResponse;
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

    public static UserProfileResponse toProfileResponse(User user){
        return new UserProfileResponse(
            user.getId(),
            user.getFirstName(),
            user.getLastName(),
            user.getPhotoUrl(),
            
            user.getWorkExperiences()
                .stream()
                .map(WorkExperienceMapper::toResponse)
                .toList(),
            
            user.getEducations()
                .stream()
                .map(EducationMapper::toResponse)
                .toList(),
            
            user.getSkills()
                .stream()
                .map(skill -> skill.getName())
                .toList()
        );
    }

}
