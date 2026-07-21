package com.netWork.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netWork.backend.dto.ConnectionResponse;
import com.netWork.backend.dto.UserResponse;
import com.netWork.backend.entity.User;
import com.netWork.backend.security.CurrentUserService;
import com.netWork.backend.service.ConnectionService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/v1/connections")
@RequiredArgsConstructor
public class ConnectionController {

    private final ConnectionService connectionService;
    private final CurrentUserService currentUserService;

    @PostMapping("/{receiverId}")
    public ResponseEntity<Void> sendConnectionRequest( @PathVariable Long receiverId,Authentication authentication) {
        connectionService.sendRequest(
            receiverId, 
            currentUserService.getCurrentUser(authentication)
        );

        return ResponseEntity.ok().build();

    }

    @GetMapping("/requests")
    public ResponseEntity<List<ConnectionResponse>> getRequests(Authentication authentication) {
        List<ConnectionResponse> pendingRequests = 
            connectionService.getPendingRequests
            (
                currentUserService.getCurrentUser(authentication)
            );
        return ResponseEntity.ok(pendingRequests);
    }

    @PutMapping("/{connectionId}/accept")
    public ResponseEntity<Void> acceptRequest(@PathVariable Long connectionId, Authentication authentication) {
        connectionService.acceptRequest(connectionId, currentUserService.getCurrentUser(authentication));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{connectionId}/reject")
    public ResponseEntity<Void> rejectRequest(@PathVariable Long connectionId, Authentication authentication) {
        connectionService.rejectRequest(connectionId, currentUserService.getCurrentUser(authentication));
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<UserResponse> getConnections(Authentication authentication) {
        User user = currentUserService.getCurrentUser(authentication);
        return connectionService.getConnections(user);
    }

    @DeleteMapping("/{connectionId}")
    public ResponseEntity<Void> removeConnection(@PathVariable Long connectionId, Authentication authentication){
        User user = currentUserService.getCurrentUser(authentication);
        connectionService.removeConnection(connectionId, user);
        return ResponseEntity.noContent().build();
    }
    
}
