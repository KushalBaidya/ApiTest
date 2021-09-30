package com.example.demo1.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo1.model.Ldemo;

public interface LCourseContentRepository extends JpaRepository<Ldemo, Long> {

    
}
