package dev.alimansour.semifinalproject.data.repository;

import android.os.AsyncTask;

import java.util.List;

import dev.alimansour.semifinalproject.data.AppDatabase;
import dev.alimansour.semifinalproject.domain.model.Note;
import dev.alimansour.semifinalproject.domain.repository.NotesRepository;
import dev.alimansour.semifinalproject.presentation.ApplicationProvider;

/**
 * SemiFinalPRoject Android Application developed by: Ali Mansour
 * Copyright © 2021 Ali Mansour. All Rights Reserved.
 * This file may not be redistributed in whole or significant part.
 * ----------------- SemiFinalPRoject IS FREE SOFTWARE ------------------
 * https://www.alimansour.dev   |   dev.ali.mansour@gmail.com
 */
public class NotesRepositoryImpl implements NotesRepository {
    private static AppDatabase database;

    public NotesRepositoryImpl() {
        ApplicationProvider provider = ApplicationProvider.getInstance();
        database = AppDatabase.getInstance(provider.getApp());
    }

    @Override
    public Boolean addNote(Note note) {
        try {
            return new AddNote().execute(note).get();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean updateNote(Note note) {
        try {
            return new UpdateNote().execute(note).get();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean deleteNote(Note note) {
        try {
            return new DeleteNote().execute(note).get();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Note findById(int id) {
        try {
            return new FindNote().execute(id).get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Note> getAllNotes() {
        try {
            return new GetAllNotes().execute().get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Note> getCourseNotes(int id) {
        try {
            return new GetCourseNotes().execute(id).get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private final static class AddNote extends AsyncTask<Note, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Note... notes) {
            database.notesDao().insert(notes[0]);
            return true;
        }
    }

    private final static class UpdateNote extends AsyncTask<Note, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Note... notes) {
            database.notesDao().update(notes[0]);
            return true;
        }
    }

    private final static class DeleteNote extends AsyncTask<Note, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Note... notes) {
            database.notesDao().delete(notes[0]);
            return true;
        }
    }

    private final static class FindNote extends AsyncTask<Integer, Void, Note> {
        @Override
        protected Note doInBackground(Integer... integers) {
            return database.notesDao().findById(integers[0]);
        }
    }

    private final static class GetAllNotes extends AsyncTask<Void, Void, List<Note>> {
        @Override
        protected List<Note> doInBackground(Void... voids) {
            return database.notesDao().getAllNotes();
        }
    }

    private final static class GetCourseNotes extends AsyncTask<Integer, Void, List<Note>> {
        @Override
        protected List<Note> doInBackground(Integer... integers) {
            return database.notesDao().getCourseNotes(integers[0]);
        }
    }
}
