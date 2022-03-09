package com.example.demo.serviceImpl;

import com.example.demo.model.Cours;
import com.example.demo.repository.CoursRepo;
import com.example.demo.service.CoursService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.UUID;

@Service
public class CoursServiceImpl implements CoursService {
    private CoursRepo coursRepo;

    public CoursServiceImpl(CoursRepo coursRepo) {
        this.coursRepo = coursRepo;
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
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRES_NEW, rollbackFor = {Throwable.class})
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
