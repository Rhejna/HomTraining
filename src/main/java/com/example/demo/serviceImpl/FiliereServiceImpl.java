package com.example.demo.serviceImpl;

import com.example.demo.model.Cours;
import com.example.demo.model.Filiere;
import com.example.demo.model.UE;
import com.example.demo.repository.FiliereRepo;
import com.example.demo.service.FiliereService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class FiliereServiceImpl implements FiliereService {
    private final FiliereRepo filiereRepo;

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
        filiere.setReference(UUID.randomUUID().toString());
        try {
            Filiere bean = filiereRepo.findByReference(filiere.getReference());
            if (bean != null && bean.getId() > 0) {
                return new Filiere();
            }

            //while(filiere.getUniteEId() != null){
                String[] ids = filiere.getUniteEId().split(",");
                Set<UE> uniteEList = new HashSet<>();
                //System.out.println(ids);
                for(String i : ids){
                    if (i != ""){
                        //System.out.println(i);
                        uniteEList.add(new UE(Long.valueOf(i)));
                    }
                }
                //System.out.println(coursList);
                filiere.setUniteE(uniteEList);
            //}



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
