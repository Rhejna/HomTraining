package com.example.demo.controller;

import com.example.demo.model.Note;
import com.example.demo.service.NoteService;
import com.example.demo.utils.Constantes;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constantes.PATH + "notes")
@CrossOrigin("*")
public class NoteController {
    private final NoteService service;

    public NoteController(NoteService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<Note> alls() {
        return service.allNotes();
    }

    @GetMapping("/cours/{idCours}")
    public List<Note> getNotesCours(@PathVariable Long idCours) {
        return service.getNotesCours(idCours);
    }

    @GetMapping("/etudiant/{idEtudiant}")
    public List<Note> getNotesEtudiant(@PathVariable Long idEtudiant) {
        return service.getNotesEtudiant(idEtudiant);
    }

//    @GetMapping("/note/{idEtudiant, coursId}")
//    public Note getNote(@PathVariable Long idEtudiant, Long coursId) {
//        return service.getNote(idEtudiant, coursId);
//    }

    @PostMapping("/{coursId}/{etudiantId}")
    public Note create(@RequestBody Note note, @PathVariable Long etudiantId, @PathVariable Long coursId) {
        return this.service.saveNote(note, etudiantId, coursId);
    }

    @PutMapping("/")
    public Note update(@RequestBody Note note) {
        return this.service.updateNote(note);
    }

    @DeleteMapping("/delete/{idNote}")
    public String delete(@PathVariable Long idNote) {
        return this.service.deleteNote(idNote);
    }
}
