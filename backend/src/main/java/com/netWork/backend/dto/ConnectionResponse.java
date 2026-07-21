package com.netWork.backend.dto;

import java.time.LocalDateTime;

import com.netWork.backend.entity.ConnectionStatus;

public record ConnectionResponse(
    Long id,
    Long senderId,
    String senderName,
    String senderPhoto,
    ConnectionStatus status,
    LocalDateTime createdAt
) {

}
