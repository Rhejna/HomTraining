package com.example.demo.serviceImpl;

import com.example.demo.model.Cours;
import com.example.demo.model.OutlinesCours;
import com.example.demo.repository.CoursRepo;
import com.example.demo.repository.OutlinesCoursRepo;
import com.example.demo.service.OutlinesCoursService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class OutlinesCoursServiceImpl implements OutlinesCoursService {
    private final OutlinesCoursRepo outlinesRepo;
    private final CoursRepo coursRepo;


    public OutlinesCoursServiceImpl(OutlinesCoursRepo outlinesRepo, CoursRepo coursRepo) {
        this.outlinesRepo = outlinesRepo;
        this.coursRepo = coursRepo;
    }

    @Override
    public List<OutlinesCours> allOutlines(){
        return outlinesRepo.findAll();
    }

    @Override
    public List<OutlinesCours> findByCoursId(Long coursId){
        Cours cours = coursRepo.findById(coursId).get();
        List<OutlinesCours> outlineList = outlinesRepo.findByCours(cours);
        return outlineList;
    }

    @Override
    @Transactional
    public OutlinesCours saveOutline(OutlinesCours outline, Long coursId){
        outline.setReference(UUID.randomUUID().toString());
        try {
            Cours cours = coursRepo.findById(coursId).get();
            OutlinesCours bean = outlinesRepo.findByReference(outline.getReference());
            if (bean != null && bean.getId() > 0) {
                return new OutlinesCours();
            }
            outline.setCours(cours);
            return outlinesRepo.save(outline);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new OutlinesCours();
        }
    }

    @Override
    public String deleteOutline(Long id){
        OutlinesCours bean = outlinesRepo.findById(id).orElse(new OutlinesCours());
        if (bean.getId() > 0) {
            outlinesRepo.delete(bean);
            return "Supprimer !";
        }
        return "Erreur";
    }

    @Override
    @Transactional
    public OutlinesCours updateOutline(OutlinesCours outline){
        try {
            OutlinesCours bean = outlinesRepo.findByReference(outline.getReference());
            if (bean != null && bean.getId() != outline.getId()) {
                return new OutlinesCours();
            }
            return outlinesRepo.save(outline);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new OutlinesCours();
        }

    }
}
