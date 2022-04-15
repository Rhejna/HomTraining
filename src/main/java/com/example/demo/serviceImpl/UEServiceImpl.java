package com.example.demo.serviceImpl;

import com.example.demo.model.Cours;
import com.example.demo.model.UE;
import com.example.demo.repository.UERepo;
import com.example.demo.service.UEService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class UEServiceImpl implements UEService {
    private final UERepo ueRepo;

    public UEServiceImpl(UERepo ueRepo) {
        this.ueRepo = ueRepo;
    }

    @Override
    public List<UE> allUEs() {
        return ueRepo.findAll();
    }

    @Override
    public UE getUE(String reference) {
        return ueRepo.findByReference(reference);
    }

    @Override
    @Transactional
    public UE saveUE(UE uniteE) {
        uniteE.setReference(UUID.randomUUID().toString());
        try {
            UE bean = ueRepo.findByReference(uniteE.getReference());
            if (bean != null && bean.getId() > 0) {
                return new UE();
            }

            while(uniteE.getCoursId() != null){
                String[] ids = uniteE.getCoursId().split(",");
                Set<Cours> coursList = new HashSet<>();
                //System.out.println(ids);
                for(String i : ids){
                    if (i != ""){
                        //System.out.println(i);
                        coursList.add(new Cours(Long.valueOf(i)));
                    }
                }
                //System.out.println(coursList);
                uniteE.setCours(coursList);
                return ueRepo.save(uniteE);
            }


            return ueRepo.save(uniteE);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new UE();
        }
    }

    @Override
    @Transactional
    public UE updateUE(UE uniteE) {
        try {
            UE bean = ueRepo.findByReference(uniteE.getReference());
            if (bean != null && bean.getId() != uniteE.getId()) {
                return new UE();
            }
            return ueRepo.save(uniteE);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new UE();
        }
    }

    @Override
    public String deleteUE(Long id) {
        UE bean = ueRepo.findById(id).orElse(new UE());
        if (bean.getId() > 0) {
            ueRepo.delete(bean);
            return "Supprimer !";
        }
        return "Erreur";
    }
}
