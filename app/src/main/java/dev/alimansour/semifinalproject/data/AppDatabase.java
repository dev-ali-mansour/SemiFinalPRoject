package dev.alimansour.semifinalproject.data;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import dev.alimansour.semifinalproject.data.dao.CoursesDao;
import dev.alimansour.semifinalproject.data.dao.NotesDao;
import dev.alimansour.semifinalproject.model.Course;
import dev.alimansour.semifinalproject.model.Note;

/**
 * SemiFinalPRoject Android Application developed by: Ali Mansour
 * Copyright © 2020 Ali Mansour. All Rights Reserved.
 * This file may not be redistributed in whole or significant part.
 * ----------------- SemiFinalPRoject IS FREE SOFTWARE ------------------
 * https://www.alimansour.dev   |   dev.ali.mansour@gmail.com
 */
@Database(entities = {Course.class, Note.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;

    public abstract CoursesDao coursesDao();

    public abstract NotesDao notesDao();

    public static AppDatabase getInstance(Application application) {
        if (instance == null) {
            instance = Room.databaseBuilder(application.getApplicationContext(),
                    AppDatabase.class,
                    "database.db")
                    .build();
        }
        return instance;
    }
}
