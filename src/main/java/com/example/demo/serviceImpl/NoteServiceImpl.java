package com.example.demo.serviceImpl;

import com.example.demo.model.Cours;
import com.example.demo.model.Etudiant;
import com.example.demo.model.Note;
import com.example.demo.repository.CoursRepo;
import com.example.demo.repository.EtudiantRepo;
import com.example.demo.repository.NoteRepo;
import com.example.demo.service.NoteService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class NoteServiceImpl implements NoteService {
    private final NoteRepo noteRepo;
    private final CoursRepo coursRepo;
    private final EtudiantRepo etudiantRepo;

    public NoteServiceImpl(NoteRepo noteRepo, CoursRepo coursRepo, EtudiantRepo etudiantRepo) {
        this.noteRepo = noteRepo;
        this.coursRepo = coursRepo;
        this.etudiantRepo = etudiantRepo;
    }


    @Override
    public List<Note> allNotes() {
        return noteRepo.findAll();
    }

    /***liste des notes d'un cours spécifique***/
    @Override
    public List<Note> getNotesCours(Long coursId){
        Cours cours = coursRepo.findById(coursId).get();
        return noteRepo.findByCours(cours);

    }

    /***liste des notes d'un étudiant spécifique***/
    @Override
    public List<Note> getNotesEtudiant(Long etudiantId){
        Etudiant etudiant = etudiantRepo.findById(etudiantId).get();
        return noteRepo.findByEtudiant(etudiant);

    }

    /***Trouver une note d'un étudiant à un cours spécifique***/
    @Override
    public Note getNote(Long etudiantId, Long coursId) {
        Etudiant etudiant = etudiantRepo.findById(etudiantId).get();
        Cours cours = coursRepo.findById(coursId).get();
        return noteRepo.findByCoursAndEtudiant(cours, etudiant);
    }

    @Override
    @Transactional
    public Note saveNote(Note note, Long etudiantId, Long coursId) {
        note.setNoteCode(UUID.randomUUID().toString());
        try {
            Cours cours = coursRepo.findById(coursId).get();
            Etudiant etudiant = etudiantRepo.findById(etudiantId).get();
            Note bean = noteRepo.findByCoursAndEtudiant(cours, etudiant);
            if (bean != null && bean.getId() > 0) {
                return new Note();
            }
            note.setCours(cours);
            note.setEtudiant(etudiant);
            return noteRepo.save(note);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new Note();
        }
    }

    @Override
    @Transactional
    public Note updateNote(Note note) {
        try {
            Note bean = noteRepo.findByNoteCode(note.getNoteCode());
            if (bean != null && bean.getId() != note.getId()) {
                return new Note();
            }
            return noteRepo.save(note);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new Note();
        }
    }

    @Override
    public String deleteNote(Long id) {
        Note bean = noteRepo.findById(id).orElse(new Note());
        if (bean.getId() > 0) {
            noteRepo.delete(bean);
            return "Supprimer !";
        }
        return "Erreur";
    }


}
