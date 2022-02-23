package com.example.demo.service;

import com.example.demo.model.Planning;

import java.util.List;

public interface PlanningService {
    List<Planning> allPlannings();

    Planning getPlanning(String reference);

    Planning createPlanning(Planning planning);

    String deletePlanning(Long id);

    Planning updatePlanning(Planning planning);
}
