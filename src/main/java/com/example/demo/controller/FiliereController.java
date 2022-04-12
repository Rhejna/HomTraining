package com.example.demo.controller;

import com.example.demo.model.Filiere;
import com.example.demo.service.FiliereService;
import com.example.demo.utils.Constantes;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constantes.PATH + "filiere")
@CrossOrigin("*")
public class FiliereController {
    private final FiliereService service;

    public FiliereController(FiliereService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<Filiere> alls() {
        return service.allFilieres();
    }

    @GetMapping("/reference/{value}")
    public Filiere getByReference(@PathVariable String value) {
        return this.service.getFiliere(value);
    }

    @PostMapping("/")
    public Filiere create(@RequestBody Filiere filiere) {
        return this.service.saveFiliere(filiere);
    }

    @PutMapping("/")
    public Filiere update(@RequestBody Filiere filiere) {
        return this.service.updateFiliere(filiere);
    }

    @DeleteMapping("/delete/{idFiliere}")
    public String delete(@PathVariable Long idFiliere) {
        return this.service.deleteFiliere(idFiliere);
    }
}
