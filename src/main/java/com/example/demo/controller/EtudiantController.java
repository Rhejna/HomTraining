package com.example.demo.controller;

import com.example.demo.model.Etudiant;
import com.example.demo.service.EtudiantService;
import com.example.demo.utils.Constantes;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constantes.PATH + "etudiant")
@CrossOrigin("*")
public class EtudiantController {
    private final EtudiantService service;

    public EtudiantController(EtudiantService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<Etudiant> alls() {
        return service.allEtudiants();
    }

    @GetMapping("/matricule/{value}")
    public Etudiant getByMatricule(@PathVariable String value) {
        return this.service.getEtudiant(value);
    }

    @GetMapping("/cours/{idCours}")
    public List<Etudiant> getByCours(@PathVariable Long idCours) {
        return this.service.getEtudiantsByCours(idCours);
    }

    @PostMapping("/")
    public Etudiant create(@RequestBody Etudiant etudiant) {
        return this.service.saveEtudiant(etudiant);
    }

    @PutMapping("/")
    public Etudiant update(@RequestBody Etudiant etudiant) {
        return this.service.updateEtudiant(etudiant);
    }

    @DeleteMapping("/delete/{idEtudiant}")
    public String delete(@PathVariable Long idEtudiant) {
        return this.service.deleteEtudiant(idEtudiant);
    }
}
