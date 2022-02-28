package com.example.demo.controller;

import com.example.demo.model.UE;
import com.example.demo.service.UEService;
import com.example.demo.utils.Constantes;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constantes.PATH + "uniteEnseignement")
@CrossOrigin("*")
public class UEController {

    private UEService service;

    public UEController(UEService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<UE> alls() {
        return service.allUEs();
    }

    @GetMapping("/reference/{value}")
    public UE getByReference(@PathVariable String value) {
        return this.service.getUE(value);
    }

    @PostMapping("/")
    public UE create(@RequestBody UE uniteE) {
        return this.service.saveUE(uniteE);
    }

    @PutMapping("/")
    public UE update(@RequestBody UE uniteE) {
        return this.service.updateUE(uniteE);
    }

    @DeleteMapping("/delete/{idUE}")
    public String delete(@PathVariable Long idUE) {
        return this.service.deleteUE(idUE);
    }
}
