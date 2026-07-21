package com.netWork.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.netWork.backend.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);

    @Query("""
            SELECT u FROM User u
            WHERE LOWER(CONCAT(u.firstName, ' ', u.lastName))
            LIKE LOWER(CONCAT('%', :query, '%'))
            """)
    List<User> searchUsers(String query);

}
