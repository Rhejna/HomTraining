package com.example.demo.service;

import com.example.demo.model.Cours;

import java.util.List;

public interface CoursService {
    List<Cours> allCours();

    Cours getCours(String reference);

    Cours saveCours(Cours cours);

    String deleteCours(Long id);

    Cours updateCours(Cours cours);
}
