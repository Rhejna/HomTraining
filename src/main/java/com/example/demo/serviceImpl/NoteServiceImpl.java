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

@Service
public class NoteServiceImpl implements NoteService {
    private NoteRepo noteRepo;
    private CoursRepo coursRepo;
    private EtudiantRepo etudiantRepo;

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

    // trouver une note d'un étudiant à un cours spécifique
//    @Override
//    public Note getNote(Long etudiantId, Long coursId) {
//        Cours cours = coursRepo.findById(coursId).get();
//        List<Etudiant> etudiantList = etudiantRepo.findByCours(cours);
//        for (Etudiant etudiant : etudiantList){
//            if (etudiant.getId() == etudiantId){
//                return noteRepo.
//            }
//        }
//        return noteRepo.findByReference(reference);
//    }

//    @Override
//    @Transactional
//    public Note saveNote(Note note, Long etudiantId, Long coursId) {
//        try {
//            Note bean = noteRepo.findByReference(note.getReference());
//            if (bean != null && bean.getId() > 0) {
//                return new Note();
//            }
//            return noteRepo.save(note);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            return new Note();
//        }
//    }

//    @Override
//    @Transactional
//    public Note updateNote(Note note) {
//        try {
//            Note bean = noteRepo.findByReference(note.getReference());
//            if (bean != null && bean.getId() != note.getId()) {
//                return new Note();
//            }
//            return noteRepo.save(note);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            return new Note();
//        }
//    }

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
