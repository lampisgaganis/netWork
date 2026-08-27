package com.netWork.backend.dto;

import java.time.Instant;

import com.netWork.backend.entity.PostVisibility;

public record PostResponse(

    Long id,
    String content,
    String imageUrl,
    PostVisibility visibility,
    Instant createdAt,
    Long authorId,
    String authorName,
    String authorPhoto

) {}
