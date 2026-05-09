package com.netWork.backend.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;

public record EducationRequest(
    @NotBlank String institution,
    @NotBlank String degree,
    String fieldOfStudy,
    LocalDate startDate,
    LocalDate endDate,
    boolean currentlyStudying,
    boolean isPublic
) {}