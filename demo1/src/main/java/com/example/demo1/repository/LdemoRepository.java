package com.example.demo1.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo1.model.Ldemo;

public interface LdemoRepository extends JpaRepository<Ldemo, Long> {
    Optional<Ldemo> findById(long id);
    List<Ldemo> findByCourseName(String course_name);
    
}
