package dev.alimansour.semifinalproject.presentation;

import dev.alimansour.semifinalproject.domain.model.Note;

/**
 * SemiFinalPRoject Android Application developed by: Ali Mansour
 * Copyright © 2020 Ali Mansour. All Rights Reserved.
 * This file may not be redistributed in whole or significant part.
 * ----------------- SemiFinalPRoject IS FREE SOFTWARE ------------------
 * https://www.alimansour.dev   |   dev.ali.mansour@gmail.com
 */
public interface NoteClickListener {
    public void onUpdate(int Id);

    public void onDelete(Note note);
}
