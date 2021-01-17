package dev.alimansour.semifinalproject.domain.usecase.note;

import java.util.List;

import dev.alimansour.semifinalproject.domain.model.Note;
import dev.alimansour.semifinalproject.domain.repository.NotesRepository;

/**
 * SemiFinalPRoject Android Application developed by: Ali Mansour
 * Copyright © 2021 Ali Mansour. All Rights Reserved.
 * This file may not be redistributed in whole or significant part.
 * ----------------- SemiFinalPRoject IS FREE SOFTWARE ------------------
 * https://www.alimansour.dev   |   dev.ali.mansour@gmail.com
 */
public class GetAllNotesUseCase {
    private NotesRepository repository;

    public GetAllNotesUseCase(NotesRepository repository) {
        this.repository = repository;
    }

    List<Note> execute() {
        return repository.getAllNotes();
    }
}
