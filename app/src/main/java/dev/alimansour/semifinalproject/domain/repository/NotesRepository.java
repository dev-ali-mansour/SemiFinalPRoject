package dev.alimansour.semifinalproject.domain.repository;

import java.util.List;

import dev.alimansour.semifinalproject.domain.model.Note;

/**
 * SemiFinalPRoject Android Application developed by: Ali Mansour
 * Copyright © 2021 Ali Mansour. All Rights Reserved.
 * This file may not be redistributed in whole or significant part.
 * ----------------- SemiFinalPRoject IS FREE SOFTWARE ------------------
 * https://www.alimansour.dev   |   dev.ali.mansour@gmail.com
 */
public interface NotesRepository {

    Boolean addNote(Note note);

    Boolean updateNote(Note note);

    Boolean deleteNote(Note note);

    Note findById(int id);

    List<Note> getAllNotes();

    List<Note> getCourseNotes(int id);
}
