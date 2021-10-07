package com.example.demo1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo1.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
}