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
public class BaseUseCase {
    protected final NotesRepository repository;

    public BaseUseCase(NotesRepository repository) {
        this.repository = repository;
    }

    public Boolean execute(Note note) {
        return false;
    }

    public Note execute(int id) {
        return null;
    }

    public List<Note> execute() {
        return null;
    }

    public List<Note> execute2(int id) {
        return null;
    }
}
