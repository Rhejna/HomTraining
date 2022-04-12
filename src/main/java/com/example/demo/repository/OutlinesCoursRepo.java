package com.example.demo.repository;

import com.example.demo.model.Cours;
import com.example.demo.model.OutlinesCours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OutlinesCoursRepo extends JpaRepository<OutlinesCours, Long> {
    List<OutlinesCours> findByCours(Cours cours);
    //List<OutlinesCours> findByCoursId(Cours cours);
    OutlinesCours findByReference(String reference);
}
