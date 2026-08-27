package com.netWork.backend.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EducationRequest(
    @NotBlank String institution,

    @NotBlank String degree,

    String fieldOfStudy,

    @NotNull(message = "Start date is required")
    LocalDate startDate,

    LocalDate endDate,

    boolean currentlyStudying
) {}