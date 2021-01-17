package dev.alimansour.semifinalproject.ui.notes;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import dev.alimansour.semifinalproject.data.AppDatabase;
import dev.alimansour.semifinalproject.domain.model.Note;

public class NotesViewModel extends AndroidViewModel {
    private static AppDatabase database;
    private final static MutableLiveData<Boolean> isAdded = new MutableLiveData<>();
    private final static MutableLiveData<Boolean> isUpdated = new MutableLiveData<>();
    private final static MutableLiveData<Boolean> isDeleted = new MutableLiveData<>();
    private final static MutableLiveData<Note> noteLiveData = new MutableLiveData<>();
    private final static MutableLiveData<List<Note>> notesLiveData = new MutableLiveData<>();

    public NotesViewModel(@NonNull Application application) {
        super(application);
        database = AppDatabase.getInstance(application);
    }

    public LiveData<Boolean> addNote(Note note) {
        new AddNote().execute(note);
        return isAdded;
    }

    public LiveData<Boolean> updateNote(Note note) {
        new UpdateNote().execute(note);
        return isUpdated;
    }

    public LiveData<Boolean> deleteNote(Note note) {
        new DeleteNote().execute(note);
        return isDeleted;
    }

    public LiveData<Note> findById(int id) {
        new FindNote().execute(id);
        return noteLiveData;
    }

    public LiveData<List<Note>> getAllNotes() {
        new GetAllNotes().execute();
        return notesLiveData;
    }

    public LiveData<List<Note>> getCourseNotes(int courseId) {
        new GetCourseNotes().execute(courseId);
        return notesLiveData;
    }

    private final static class AddNote extends AsyncTask<Note, Void, Void> {

        @Override
        protected Void doInBackground(Note... notes) {
            database.notesDao().insert(notes[0]);
            isAdded.postValue(true);
            return null;
        }
    }

    private final static class UpdateNote extends AsyncTask<Note, Void, Void> {

        @Override
        protected Void doInBackground(Note... notes) {
            database.notesDao().update(notes[0]);
            isUpdated.postValue(true);
            return null;
        }
    }

    private final static class DeleteNote extends AsyncTask<Note, Void, Void> {

        @Override
        protected Void doInBackground(Note... notes) {
            database.notesDao().delete(notes[0]);
            isDeleted.postValue(true);
            return null;
        }
    }

    private final static class FindNote extends AsyncTask<Integer, Void, Void> {
        @Override
        protected Void doInBackground(Integer... integers) {
            noteLiveData.postValue(database.notesDao().findById(integers[0]));
            return null;
        }
    }

    private final static class GetAllNotes extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            notesLiveData.postValue(database.notesDao().getAllNotes());
            return null;
        }
    }

    private final static class GetCourseNotes extends AsyncTask<Integer, Void, Void> {
        @Override
        protected Void doInBackground(Integer... integers) {
            notesLiveData.postValue(database.notesDao().getCourseNotes(integers[0]));
            return null;
        }
    }
}