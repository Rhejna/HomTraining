package com.example.demo.controller;

import com.example.demo.model.Planning;
import com.example.demo.service.PlanningService;
import com.example.demo.utils.Constantes;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constantes.PATH + "planning")
@CrossOrigin("*")
public class PlanningController {
    private PlanningService service;

    public PlanningController(PlanningService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<Planning> alls() {
        return service.allPlannings();
    }

    @GetMapping("/reference/{value}")
    public Planning getByReference(@PathVariable String value) {
        return this.service.getPlanning(value);
    }

    @PostMapping("/")
    public Planning create(@RequestBody Planning planning) {
        return this.service.createPlanning(planning);
    }

    @PutMapping("/")
    public Planning update(@RequestBody Planning planning) {
        return this.service.updatePlanning(planning);
    }

    @DeleteMapping("/delete/{idPlanning}")
    public String delete(@PathVariable Long idPlanning) {
        return this.service.deletePlanning(idPlanning);
    }
}
