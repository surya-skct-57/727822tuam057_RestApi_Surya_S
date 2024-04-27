package com.example.signup.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.signup.model.User;

public interface Repository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String username);
}
