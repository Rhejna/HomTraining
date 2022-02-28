package com.example.demo.repository;

import com.example.demo.model.Filiere;
import com.example.demo.model.UE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FiliereRepo extends JpaRepository<Filiere, Long> {
    Filiere findByReference(String reference);
}
