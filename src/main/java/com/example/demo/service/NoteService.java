package com.example.demo.service;

import com.example.demo.model.Note;

import java.util.List;

public interface NoteService {
    List<Note> allNotes();

    /***liste des notes d'un cours spécifique***/
    public List<Note> getNotesCours(Long coursId);

    /***liste des notes d'un étudiant spécifique***/
    public List<Note> getNotesEtudiant(Long etudiantId);

//    Note getNote(Long etudiantId, Long coursId);

//    Note saveNote(Note note, Long etudiantId, Long coursId);

    String deleteNote(Long id);

//    Note updateNote(Note note, Long etudiantId, Long coursId);
}
