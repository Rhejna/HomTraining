package com.example.demo.serviceImpl;

import com.example.demo.model.Cours;
import com.example.demo.model.Filiere;
import com.example.demo.model.Etudiant;
import com.example.demo.model.UE;
import com.example.demo.repository.CoursRepo;
import com.example.demo.repository.EtudiantRepo;
import com.example.demo.repository.FiliereRepo;
import com.example.demo.repository.UERepo;
import com.example.demo.service.EtudiantService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class EtudiantServiceImpl implements EtudiantService {
    private final EtudiantRepo etudiantRepo;
    private final CoursRepo coursRepo;
    private final FiliereRepo filiereRepo;
    private final UERepo ueRepo;

    public EtudiantServiceImpl(EtudiantRepo etudiantRepo,
                               CoursRepo coursRepo,
                               FiliereRepo filiereRepo,
                               UERepo ueRepo) {
        this.etudiantRepo = etudiantRepo;
        this.coursRepo = coursRepo;
        this.filiereRepo = filiereRepo;
        this.ueRepo = ueRepo;
    }

    // chercher la liste des notes d'un étudiant

    // liste des Filiere suivis


    @Override
    public List<Etudiant> allEtudiants() {
        return etudiantRepo.findAll();
    }

    @Override
    public Etudiant getEtudiant(String matricule) {
        return etudiantRepo.findByMatricule(matricule);
    }

    @Override
    public List<Etudiant> getEtudiantsByCours(Long coursId){
        Cours cours = coursRepo.findById(coursId).get();
        UE ue = ueRepo.findByCours(cours);
        Filiere filiere = filiereRepo.findByUniteE(ue);
        List<Etudiant> bean = etudiantRepo.findByFiliere(filiere);
        if (bean == null) {
            return Collections.emptyList();
        }
        return bean;
    }

    @Override
    @Transactional
    public Etudiant saveEtudiant(Etudiant etudiant) {
        try {
            Etudiant bean = etudiantRepo.findByMatricule(etudiant.getMatricule());
            if (bean != null && bean.getId() > 0) {

                return new Etudiant();
            }

            //while(etudiant.getFiliereId() != null){
                String[] ids = etudiant.getFiliereId().split(",");
                Set<Filiere> filiereList = new HashSet<>();
                //System.out.println(ids);
                for(String i : ids){
                    if (i != ""){
                        //System.out.println(i);
                        filiereList.add(new Filiere(Long.valueOf(i)));
                    }
                }
                //System.out.println(filiereList);
                etudiant.setFiliere(filiereList);
            //}
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
