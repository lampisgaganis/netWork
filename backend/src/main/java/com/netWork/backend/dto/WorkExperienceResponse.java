package com.netWork.backend.dto;

import java.time.LocalDate;

public record WorkExperienceResponse(
    Long id,
    String company,
    String position,
    String description,
    LocalDate startDate,
    LocalDate endDate,
    boolean currentlyWorking
) {}
