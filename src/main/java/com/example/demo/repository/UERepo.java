package com.example.demo.repository;

import com.example.demo.model.UE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UERepo extends JpaRepository<UE, Long> {
}
