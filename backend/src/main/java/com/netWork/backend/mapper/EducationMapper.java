package com.netWork.backend.mapper;

import com.netWork.backend.dto.EducationResponse;
import com.netWork.backend.entity.Education;

public class EducationMapper {

    public static EducationResponse toResponse(Education education) {
        return new EducationResponse(
            education.getId(),
            education.getInstitution(),
            education.getDegree(),
            education.getFieldOfStudy(),
            education.getStartDate(),
            education.getEndDate(),
            education.isCurrentlyStudying(),
            education.isPublic()
        );
    }

}
