package com.example.demo.repository;

import com.example.demo.model.Planning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanningRepo extends JpaRepository<Planning, Long> {
    Planning findByReference(String reference);
}
