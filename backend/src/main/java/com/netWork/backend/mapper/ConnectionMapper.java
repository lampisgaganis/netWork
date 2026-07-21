package com.netWork.backend.mapper;

import com.netWork.backend.dto.ConnectionResponse;
import com.netWork.backend.entity.Connection;

public class ConnectionMapper {
    public static ConnectionResponse toResponse(Connection connection) {
        
        return new ConnectionResponse(
            connection.getId(),
            connection.getSender().getId(),
            connection.getSender().getFirstName() + " " + connection.getSender().getLastName(),
            connection.getSender().getPhotoUrl(),
            connection.getStatus(),
            connection.getCreatedAt()
        );
    }
}
