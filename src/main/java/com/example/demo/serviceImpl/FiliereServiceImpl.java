package com.example.demo.serviceImpl;

import com.example.demo.model.Filiere;
import com.example.demo.repository.FiliereRepo;
import com.example.demo.service.FiliereService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class FiliereServiceImpl implements FiliereService {
    private FiliereRepo filiereRepo;

    public FiliereServiceImpl(FiliereRepo filiereRepo) {
        this.filiereRepo = filiereRepo;
    }

    @Override
    public List<Filiere> allFilieres() {
        return filiereRepo.findAll();
    }

    @Override
    public Filiere getFiliere(String reference) {
        return filiereRepo.findByReference(reference);
    }

    @Override
    @Transactional
    public Filiere saveFiliere(Filiere filiere) {
        try {
            Filiere bean = filiereRepo.findByReference(filiere.getReference());
            if (bean != null && bean.getId() > 0) {
                return new Filiere();
            }
            return filiereRepo.save(filiere);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new Filiere();
        }
    }

    @Override
    @Transactional
    public Filiere updateFiliere(Filiere filiere) {
        try {
            Filiere bean = filiereRepo.findByReference(filiere.getReference());
            if (bean != null && bean.getId() != filiere.getId()) {
                return new Filiere();
            }
            return filiereRepo.save(filiere);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new Filiere();
        }
    }

    @Override
    public String deleteFiliere(Long id) {
        Filiere bean = filiereRepo.findById(id).orElse(new Filiere());
        if (bean.getId() > 0) {
            filiereRepo.delete(bean);
            return "Supprimer !";
        }
        return "Erreur";
    }
}
