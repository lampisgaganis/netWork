package com.netWork.backend.mapper;

import com.netWork.backend.dto.WorkExperienceResponse;
import com.netWork.backend.entity.WorkExperience;

public class WorkExperienceMapper {
    public static WorkExperienceResponse toResponse(WorkExperience workExperience) {
        return new WorkExperienceResponse(
            workExperience.getId(),
            workExperience.getCompany(),
            workExperience.getPosition(),
            workExperience.getDescription(),
            workExperience.getStartDate(),
            workExperience.getEndDate(),
            workExperience.isCurrentlyWorking()
        );
    }
}
