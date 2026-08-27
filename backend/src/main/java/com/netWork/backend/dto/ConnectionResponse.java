package com.netWork.backend.dto;

import java.time.Instant;

import com.netWork.backend.entity.ConnectionStatus;

public record ConnectionResponse(
    Long id,
    Long senderId,
    String senderName,
    String senderPhoto,
    ConnectionStatus status,
    Instant createdAt
) {}