package dev.alimansour.semifinalproject.data.dao;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import dev.alimansour.semifinalproject.domain.model.Note;

/**
 * SemiFinalPRoject Android Application developed by: Ali Mansour
 * Copyright © 2020 Ali Mansour. All Rights Reserved.
 * This file may not be redistributed in whole or significant part.
 * ----------------- SemiFinalPRoject IS FREE SOFTWARE ------------------
 * https://www.alimansour.dev   |   dev.ali.mansour@gmail.com
 */
@Dao
public interface NotesDao {
    @Insert
    public void insert(Note note);

    @Update
    public void update(Note note);

    @Delete
    public void delete(Note note);

    @Query("SELECT * FROM notes WHERE id = :id")
    public Note findById(int id);

    @Query("SELECT * FROM notes WHERE 1")
    public List<Note> getAllNotes();

    @Query("SELECT * FROM notes WHERE courseId = :courseId")
    public List<Note> getCourseNotes(int courseId);
}
