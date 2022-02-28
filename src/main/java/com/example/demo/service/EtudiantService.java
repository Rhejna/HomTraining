package com.example.demo.service;

import com.example.demo.model.Etudiant;

import java.util.List;

public interface EtudiantService {
    List<Etudiant> allEtudiants();

    Etudiant getEtudiant(String matricule);

    Etudiant saveEtudiant(Etudiant etudiant);

    String deleteEtudiant(Long id);

    Etudiant updateEtudiant(Etudiant etudiant);
}