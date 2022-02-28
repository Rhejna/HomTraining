package com.example.demo.repository;

import com.example.demo.model.Cours;
import com.example.demo.model.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EtudiantRepo extends JpaRepository<Etudiant, Long> {
    Etudiant findByMatricule(String matricule);
    List<Etudiant> findByCours(Cours cours);
}
