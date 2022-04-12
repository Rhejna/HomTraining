package com.example.demo.controller;

import com.example.demo.model.Cours;
import com.example.demo.model.OutlinesCours;
import com.example.demo.service.OutlinesCoursService;
import com.example.demo.utils.Constantes;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constantes.PATH + "outline")
@CrossOrigin("*")
public class OutlinesCoursController {
    private final OutlinesCoursService service;

    public OutlinesCoursController(OutlinesCoursService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<OutlinesCours> allOutlines(){
        return service.allOutlines();
    }


    @GetMapping("/cours/{coursId}")
    public List<OutlinesCours> findByCoursId(@PathVariable Long coursId){
        return this.service.findByCoursId(coursId);
    }



    @PostMapping("/cours/{coursId}")
    public OutlinesCours create(@RequestBody OutlinesCours outline, @PathVariable Long coursId) {
        return this.service.saveOutline(outline, coursId);
    }

    @PutMapping("/")
    public OutlinesCours update(@RequestBody OutlinesCours outline) {
        return this.service.updateOutline(outline);
    }

    @DeleteMapping("/delete/{idOutline}")
    public String delete(@PathVariable Long idOutline) {
        return this.service.deleteOutline(idOutline);
    }
}
