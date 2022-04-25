package com.example.demo.service;

import com.example.demo.model.Cours;
import com.example.demo.model.OutlinesCours;

import java.util.List;

public interface CoursService {
    List<Cours> allCours();

    Cours getCours(String reference);

    Cours getCours(Long id);

    List<Cours> getCoursByEtudiant(Long etudiantId);

    List<OutlinesCours> getOutlines(Long id);

    Cours saveCours(Cours cours);

    String deleteCours(Long id);

    Cours updateCours(Cours cours);
}
