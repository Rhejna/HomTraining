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

    @Override
    public Etudiant getEtudiant(Long id){
        return etudiantRepo.findById(id).get();
    }

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
        List<Etudiant> list = new ArrayList<Etudiant>();
        Cours cours = coursRepo.findById(coursId).get();
        List<UE> ueList = ueRepo.findByCours(cours);
        if (ueList.size() != 0) {
            for (UE ue : ueList) {
                List<Filiere> filieres = filiereRepo.findByUniteE(ue);
                if (filieres.size() != 0) {
                    for (Filiere filiere : filieres) {
                        List<Etudiant> etudiantList = etudiantRepo.findByFiliere(filiere);
                        if (etudiantList.size() != 0){
                            for (Etudiant etudiant: etudiantList){
                                list.add(etudiant);
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
    @Transactional
    public Etudiant saveEtudiant(Etudiant etudiant) {
        try {
            Etudiant bean = etudiantRepo.findByMatricule(etudiant.getMatricule());
            if (bean != null && bean.getId() > 0) {

                return new Etudiant();
            }

            while(etudiant.getFiliereId() != null){
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
                return etudiantRepo.save(etudiant);

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
