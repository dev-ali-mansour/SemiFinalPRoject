package dev.alimansour.semifinalproject.presentation.notes;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import dev.alimansour.semifinalproject.data.repository.NotesRepositoryImpl;
import dev.alimansour.semifinalproject.domain.model.Note;
import dev.alimansour.semifinalproject.domain.repository.NotesRepository;
import dev.alimansour.semifinalproject.domain.usecase.note.AddNoteUseCase;
import dev.alimansour.semifinalproject.domain.usecase.note.DeleteNoteUseCase;
import dev.alimansour.semifinalproject.domain.usecase.note.FindNoteUseCase;
import dev.alimansour.semifinalproject.domain.usecase.note.GetAllNotesUseCase;
import dev.alimansour.semifinalproject.domain.usecase.note.GetCourseNotesUseCase;
import dev.alimansour.semifinalproject.domain.usecase.note.UpdateNoteUseCase;

public class NotesViewModel extends ViewModel {
    private final NotesRepository repository = new NotesRepositoryImpl();
    private final static MutableLiveData<Boolean> isAdded = new MutableLiveData<>();
    private final static MutableLiveData<Boolean> isUpdated = new MutableLiveData<>();
    private final static MutableLiveData<Boolean> isDeleted = new MutableLiveData<>();
    private final static MutableLiveData<Note> noteLiveData = new MutableLiveData<>();
    private final static MutableLiveData<List<Note>> notesLiveData = new MutableLiveData<>();

    public LiveData<Boolean> addNote(Note note) {
       isAdded.setValue(new AddNoteUseCase(repository).execute(note));
        return isAdded;
    }

    public LiveData<Boolean> updateNote(Note note) {
        isUpdated.setValue(new UpdateNoteUseCase(repository).execute(note));
        return isUpdated;
    }

    public LiveData<Boolean> deleteNote(Note note) {
        isDeleted.setValue(new DeleteNoteUseCase(repository).execute(note));
        return isDeleted;
    }

    public LiveData<Note> findById(int id) {
        noteLiveData.setValue(new FindNoteUseCase(repository).execute(id));
        return noteLiveData;
    }

    public LiveData<List<Note>> getAllNotes() {
        notesLiveData.setValue(new GetAllNotesUseCase(repository).execute());
        return notesLiveData;
    }

    public LiveData<List<Note>> getCourseNotes(int courseId) {
        notesLiveData.setValue(new GetCourseNotesUseCase(repository).execute2(courseId));
        return notesLiveData;
    }
}