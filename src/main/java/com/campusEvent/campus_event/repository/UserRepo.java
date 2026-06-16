package com.campusEvent.campus_event.repository;

import com.campusEvent.campus_event.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findById(Long id);
    User findByEmail(String email);
    boolean existsByEmail(String email);
}
