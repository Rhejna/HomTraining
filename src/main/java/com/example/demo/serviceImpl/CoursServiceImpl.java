package com.example.demo.serviceImpl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.CoursService;
import com.example.demo.repository.EtudiantRepo;
import com.example.demo.repository.FiliereRepo;
import com.example.demo.repository.UERepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;

@Service
public class CoursServiceImpl implements CoursService {
    private final CoursRepo coursRepo;
    private final OutlinesCoursRepo outlinesRepo;
    private final EtudiantRepo etudiantRepo;
    private final FiliereRepo filiereRepo;
    private final UERepo ueRepo;

    public CoursServiceImpl(CoursRepo coursRepo, OutlinesCoursRepo outlinesRepo, EtudiantRepo etudiantRepo, FiliereRepo filiereRepo, UERepo ueRepo) {
        this.coursRepo = coursRepo;
        this.outlinesRepo = outlinesRepo;
        this.etudiantRepo = etudiantRepo;
        this.filiereRepo = filiereRepo;
        this.ueRepo = ueRepo;
    }

    @Override
    public List<Cours> allCours(){
        return coursRepo.findAll();
    }

    @Override
    public Cours getCours(String reference){
        return coursRepo.findByReference(reference);
    }

    @Override
    public Cours getCours(Long id){
        return coursRepo.findById(id).get();
    }

    @Override
    public List<OutlinesCours> getOutlines(Long id) {
        Cours bean = coursRepo.findById(id).orElse(new Cours());
        List<OutlinesCours> outlines = outlinesRepo.findByCours(bean);
        if (bean.getId() > 0) {
            return outlines;
        }
        return Collections.emptyList();
    }

    @Override
    public List<Cours> getCoursByEtudiant(Long etudiantId) {
        List<Cours> list = new ArrayList<Cours>();
        Etudiant etudiant = etudiantRepo.findById(etudiantId).get();
        Set<Filiere> filieres = etudiant.getFiliere();
        if (filieres.size() != 0){
            for (Filiere filiere: filieres){
                Set<UE> uniteEs = filiere.getUniteE();
                if (uniteEs.size() != 0){
                    for (UE ue: uniteEs){
                        Set<Cours> coursList = ue.getCours();
                        if (coursList.size() != 0){
                            for (Cours cours: coursList){
                                list.add(cours);
                            }
                        }
                    }
                }
            }
            return list;
        }

        return Collections.emptyList();
    }

    @Override
    @Transactional //substring sur ref
    public Cours saveCours(Cours cours){
        cours.setReference(UUID.randomUUID().toString());
        try {
            Cours bean = coursRepo.findByReference(cours.getReference());
            if (bean != null && bean.getId() > 0) {
                return new Cours();
            }
            return coursRepo.save(cours);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new Cours();
        }
    }

    @Override
    @Transactional
    public String deleteCours(Long id){
        Cours bean = coursRepo.findById(id).orElse(new Cours());
        if (bean.getId() > 0) {
            coursRepo.delete(bean);
            return "Supprimer !";
        }
        return "Erreur";
    }

    @Override
    @Transactional
    public Cours updateCours(Cours cours){
        try {
            Cours bean = coursRepo.findByReference(cours.getReference());
            if (bean != null && bean.getId() != cours.getId()) {
                return new Cours();
            }
            return coursRepo.save(cours);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new Cours();
        }
    }
}
