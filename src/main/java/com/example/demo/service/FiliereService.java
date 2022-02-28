package com.example.demo.service;

import com.example.demo.model.Filiere;

import java.util.List;

public interface FiliereService {
    List<Filiere> allFilieres();

    Filiere getFiliere(String reference);

    Filiere saveFiliere(Filiere filiere);

    String deleteFiliere(Long id);

    Filiere updateFiliere(Filiere filiere);
}
