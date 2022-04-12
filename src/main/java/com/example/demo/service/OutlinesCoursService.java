package com.example.demo.service;

import com.example.demo.model.Cours;
import com.example.demo.model.OutlinesCours;

import java.util.List;

public interface OutlinesCoursService {
    List<OutlinesCours> allOutlines();


    List<OutlinesCours> findByCoursId(Long coursId);

    OutlinesCours saveOutline(OutlinesCours outline, Long coursId);

    String deleteOutline(Long id);

    OutlinesCours updateOutline(OutlinesCours outline);
}
