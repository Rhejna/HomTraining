package com.example.demo.repository;

import com.example.demo.model.Cours;
import com.example.demo.model.Etudiant;
import com.example.demo.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepo extends JpaRepository<Note, Long> {
    List<Note> findByCours (Cours cours);
    List<Note> findByEtudiant (Etudiant etudiant);

    //@Query(value = "SELECT u FROM Article u WHERE u.reference = :ref")
//    @Query(value = "SELECT u FROM Note u LEFT JOIN Etudiant e, Cours c WHERE u.etudiant.id = :etudiantId AND u.etudiant.id = c.etudiant.id")
//    Note getByEtudiant(Long etudiantId);

    Note findByNoteCode(String noteCode);
}
