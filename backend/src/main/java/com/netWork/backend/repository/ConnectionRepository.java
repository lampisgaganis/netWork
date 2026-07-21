package com.netWork.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.netWork.backend.entity.Connection;
import com.netWork.backend.entity.ConnectionStatus;
import com.netWork.backend.entity.User;

public interface ConnectionRepository extends JpaRepository<Connection, Long> {

    List<Connection> findByReceiverAndStatus(User receiver, ConnectionStatus status);

    List<Connection> findBySenderAndStatus(User sender, ConnectionStatus status);

    @Query("""
        SELECT c 
        FROM Connection c
        WHERE
        (c.sender = :user1 AND c.receiver = :user2)
        OR
        (c.sender = :user2 AND c.receiver = :user1)
    """)
    Optional<Connection> findConnectionBetweenUsers(@Param("user1") User user1, @Param("user2") User user2);

    Optional<Connection> findById(Long id);
}
