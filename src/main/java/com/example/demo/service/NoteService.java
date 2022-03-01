package com.example.demo.service;

import com.example.demo.model.Note;

import java.util.List;

public interface NoteService {
    List<Note> allNotes();

    /***liste des notes d'un cours spécifique***/
    List<Note> getNotesCours(Long coursId);

    /***liste des notes d'un étudiant spécifique***/
    List<Note> getNotesEtudiant(Long etudiantId);

    /***Trouver une note d'un étudiant à un cours spécifique***/
//    Note getNote(Long etudiantId, Long coursId);

    Note saveNote(Note note);
//    Note saveNote(Note note, Long etudiantId, Long coursId);

    String deleteNote(Long id);

    Note updateNote(Note note);
}
