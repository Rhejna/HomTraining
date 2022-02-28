package com.example.demo.repository;

import com.example.demo.model.Cours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursRepo extends JpaRepository<Cours, Long> {
    Cours findByReference(String reference);
}
