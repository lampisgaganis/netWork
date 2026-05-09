package com.netWork.backend.dto;

import java.time.LocalDate;

public record EducationResponse(
    Long id,
    String institution,
    String degree,
    String fieldOfStudy,
    LocalDate startDate,
    LocalDate endDate,
    boolean currentlyStudying,
    boolean isPublic
) {}
