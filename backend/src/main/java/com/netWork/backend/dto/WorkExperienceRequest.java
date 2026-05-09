package com.netWork.backend.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;

public record WorkExperienceRequest(
    @NotBlank String company,
    @NotBlank String position,
    String description,
    LocalDate startDate,
    LocalDate endDate,
    boolean currentlyWorking,
    boolean isPublic
) 
{}
