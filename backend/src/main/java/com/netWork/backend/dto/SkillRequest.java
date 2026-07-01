package com.netWork.backend.dto;

import jakarta.validation.constraints.NotBlank;

public record SkillRequest(
        @NotBlank String name
) {}
