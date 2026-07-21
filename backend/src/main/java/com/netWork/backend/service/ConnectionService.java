package com.netWork.backend.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.netWork.backend.dto.ConnectionResponse;
import com.netWork.backend.entity.Connection;
import com.netWork.backend.entity.ConnectionStatus;
import com.netWork.backend.entity.User;
import com.netWork.backend.exception.DuplicateResourceException;
import com.netWork.backend.exception.InvalidRequestException;
import com.netWork.backend.exception.ResourceNotFoundException;
import com.netWork.backend.exception.UnauthorizedActionException;
import com.netWork.backend.mapper.ConnectionMapper;
import com.netWork.backend.repository.ConnectionRepository;
import com.netWork.backend.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConnectionService {
    private final ConnectionRepository connectionRepository;
    private final UserRepository userRepository;

    public void sendRequest(Long receiverId, User sender){
        
        User receiver = userRepository.findById(receiverId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        if(sender.getId().equals(receiver.getId())) {
            throw new InvalidRequestException("Cannot send connection request to yourself");
        }

        if(connectionRepository
                .findConnectionBetweenUsers(sender, receiver)
                .isPresent()
        ) {
            throw new DuplicateResourceException("Connection already exists");
        }

        Connection connection = Connection.builder()
            .sender(sender)
            .receiver(receiver)
            .status(ConnectionStatus.PENDING)
            .createdAt(LocalDateTime.now())
            .build();
        
        connectionRepository.save(connection);
    }
    
    public List<ConnectionResponse> getPendingRequests(User user) {
        
        List<Connection> pendingConnections = connectionRepository.findByReceiverAndStatus(user, ConnectionStatus.PENDING);
        
        return pendingConnections.stream()
            .map(ConnectionMapper::toResponse)
            .toList();
    }

    public void acceptRequest(Long connectionId, User user) {
        
        Connection connection = connectionRepository.findById(connectionId)
            .orElseThrow(() -> new ResourceNotFoundException("Connection request not found"));
        
        if(!connection.getReceiver().getId().equals(user.getId())) {
            throw new UnauthorizedActionException("You cannot accept this request");
        }

        if(connection.getStatus() != ConnectionStatus.PENDING) {
            throw new InvalidRequestException("Connection request is not pending");
        }

        connection.setStatus(ConnectionStatus.ACCEPTED);
        connectionRepository.save(connection);

    }

    public void rejectRequest(Long connectionId, User user) {
        
        Connection connection = connectionRepository.findById(connectionId)
                .orElseThrow(() -> new ResourceNotFoundException("Connection request not found"));
        
        if(!connection.getReceiver().getId().equals(user.getId())) {
            throw new UnauthorizedActionException("You cannot reject this request");
        }

        if(connection.getStatus() != ConnectionStatus.PENDING) {
            throw new InvalidRequestException("Connection request is not pending");
        }

        connection.setStatus(ConnectionStatus.REJECTED);

        connectionRepository.save(connection);
    }
}
