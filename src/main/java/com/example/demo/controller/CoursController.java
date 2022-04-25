package com.example.demo.controller;

import com.example.demo.model.Cours;
import com.example.demo.model.OutlinesCours;
import com.example.demo.service.CoursService;
import com.example.demo.utils.Constantes;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constantes.PATH + "cours")
@CrossOrigin("*")
public class CoursController {
    private final CoursService service;

    public CoursController(CoursService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<Cours> alls() {
        return service.allCours();
    }

    @GetMapping("/outlines/{idCours}")
    public List<OutlinesCours> getOutlines(@PathVariable Long idCours) {
        return this.service.getOutlines(idCours);
    }

    @GetMapping("/reference/{value}")
    public Cours getByReference(@PathVariable String value) {
        return this.service.getCours(value);
    }

    @GetMapping("/id/{value}")
    public Cours getById(@PathVariable Long value) {
        return this.service.getCours(value);
    }

    @GetMapping("/etudiant/id/{value}")
    public List<Cours> getByEtudiant(@PathVariable Long value) {
        return this.service.getCoursByEtudiant(value);
    }

    @PostMapping("/")
    public Cours create(@RequestBody Cours cours) {
        return this.service.saveCours(cours);
    }

    @PutMapping("/")
    public Cours update(@RequestBody Cours cours) {
        return this.service.updateCours(cours);
    }

    @DeleteMapping("/delete/{idCours}")
    public String delete(@PathVariable Long idCours) {
        return this.service.deleteCours(idCours);
    }
}
