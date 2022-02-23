package com.example.demo.repository;

import com.example.demo.model.Filiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FiliereRepo extends JpaRepository<Filiere, Long> {
}
