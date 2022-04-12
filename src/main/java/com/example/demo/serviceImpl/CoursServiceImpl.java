package com.example.demo.serviceImpl;

import com.example.demo.model.Cours;
import com.example.demo.model.OutlinesCours;
import com.example.demo.repository.CoursRepo;
import com.example.demo.repository.OutlinesCoursRepo;
import com.example.demo.service.CoursService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class CoursServiceImpl implements CoursService {
    private final CoursRepo coursRepo;
    private final OutlinesCoursRepo outlinesRepo;

    public CoursServiceImpl(CoursRepo coursRepo, OutlinesCoursRepo outlinesRepo) {
        this.coursRepo = coursRepo;
        this.outlinesRepo = outlinesRepo;
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
