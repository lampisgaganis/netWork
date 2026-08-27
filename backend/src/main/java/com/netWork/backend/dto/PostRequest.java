package com.netWork.backend.dto;

import com.netWork.backend.entity.PostVisibility;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PostRequest(

    @NotBlank
    @Size(max = 3000, message = "Content must not exceed 3000 characters")
    String content,

    String imageUrl,

    PostVisibility visibility
) {}
