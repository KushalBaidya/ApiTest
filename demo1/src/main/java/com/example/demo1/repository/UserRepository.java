package com.example.demo1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo1.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}