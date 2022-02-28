package com.example.demo.repository;

import com.example.demo.model.Cours;
import com.example.demo.model.Etudiant;
import com.example.demo.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepo extends JpaRepository<Note, Long> {
    List<Note> findByCours (Cours cours);
    List<Note> findByEtudiant (Etudiant etudiant);
}
