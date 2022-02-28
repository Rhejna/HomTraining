package com.example.demo.serviceImpl;

import com.example.demo.model.Etudiant;
import com.example.demo.repository.EtudiantRepo;
import com.example.demo.service.EtudiantService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EtudiantServiceImpl implements EtudiantService {
    private EtudiantRepo etudiantRepo;

    public EtudiantServiceImpl(EtudiantRepo etudiantRepo){
        this.etudiantRepo = etudiantRepo;
    }

    // chercher la liste des notes d'un étudiant

    // liste des cours suivis


    @Override
    public List<Etudiant> allEtudiants() {
        return etudiantRepo.findAll();
    }

    @Override
    public Etudiant getEtudiant(String matricule) {
        return etudiantRepo.findByMatricule(matricule);
    }

    @Override
    @Transactional
    public Etudiant saveEtudiant(Etudiant etudiant) {
        try {
            Etudiant bean = etudiantRepo.findByMatricule(etudiant.getMatricule());
            if (bean != null && bean.getId() > 0) {
                return new Etudiant();
            }
            return etudiantRepo.save(etudiant);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new Etudiant();
        }
    }

    @Override
    @Transactional
    public Etudiant updateEtudiant(Etudiant etudiant) {
        try {
            Etudiant bean = etudiantRepo.findByMatricule(etudiant.getMatricule());
            if (bean != null && bean.getId() != etudiant.getId()) {
                return new Etudiant();
            }
            return etudiantRepo.save(etudiant);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new Etudiant();
        }
    }

    @Override
    public String deleteEtudiant(Long id) {
        Etudiant bean = etudiantRepo.findById(id).orElse(new Etudiant());
        if (bean.getId() > 0) {
            etudiantRepo.delete(bean);
            return "Supprimer !";
        }
        return "Erreur";
    }
}
