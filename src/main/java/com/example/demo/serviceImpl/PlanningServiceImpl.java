package com.example.demo.serviceImpl;

import com.example.demo.model.Planning;
import com.example.demo.repository.PlanningRepo;
import com.example.demo.service.PlanningService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class PlanningServiceImpl implements PlanningService {
    private final PlanningRepo planningRepo;

    public PlanningServiceImpl(PlanningRepo planningRepo) {
        this.planningRepo = planningRepo;
    }

    @Override
    public List<Planning> allPlannings() {
        return planningRepo.findAll();
    }

    @Override
    public Planning getPlanning(String reference) {
        return planningRepo.findByReference(reference);
    }

    @Override
    @Transactional
    public Planning createPlanning(Planning planning) {
        planning.setReference(UUID.randomUUID().toString());
        try{
            Planning plan = planningRepo.findByReference(planning.getReference());
            if (plan != null && plan.getId()>0){
                return new Planning();
            }
            return planningRepo.save(planning);
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return new Planning();
        }
    }

    @Override
    @Transactional
    public String deletePlanning(Long id) {
        Planning plan = planningRepo.findById(id).orElse(new Planning());
        if (plan.getId() > 0){
            planningRepo.delete(plan);
            return "Planning Supprimé";

        }
        return "Erreur";
    }

    @Override
    @Transactional
    public Planning updatePlanning(Planning planning){
        try{
            Planning plan = planningRepo.findByReference(planning.getReference());
            if (plan != null && plan.getId() != planning.getId()) {
                return new Planning();
            }
            return planningRepo.save(planning);
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return new Planning();
        }
    }
}
