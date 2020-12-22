package dev.alimansour.semifinalproject.ui.notes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import dev.alimansour.semifinalproject.databinding.FragmentAddEditNoteBinding;

public class AddEditNoteFragment extends Fragment {
    private FragmentAddEditNoteBinding binding;
    private NotesViewModel notesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAddEditNoteBinding.inflate(inflater, container, false);
        notesViewModel =
                new ViewModelProvider(this).get(NotesViewModel.class);

        return binding.getRoot();
    }
}