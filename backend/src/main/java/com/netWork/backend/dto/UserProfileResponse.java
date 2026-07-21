package com.netWork.backend.dto;

import java.util.List;

public record UserProfileResponse(
    Long id,
    String firstName,
    String lastName,
    String photoUrl,
    List<WorkExperienceResponse> experiences,
    List<EducationResponse> education,
    List<String> skills
) {} 