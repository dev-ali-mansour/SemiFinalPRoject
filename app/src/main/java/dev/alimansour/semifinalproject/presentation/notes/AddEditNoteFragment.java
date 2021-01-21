package dev.alimansour.semifinalproject.presentation.notes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import dev.alimansour.semifinalproject.R;
import dev.alimansour.semifinalproject.adapter.CoursesSpinnerAdapter;
import dev.alimansour.semifinalproject.databinding.FragmentAddEditNoteBinding;
import dev.alimansour.semifinalproject.domain.model.Course;
import dev.alimansour.semifinalproject.domain.model.Note;
import dev.alimansour.semifinalproject.presentation.MainActivity;
import dev.alimansour.semifinalproject.presentation.courses.CoursesViewModel;

public class AddEditNoteFragment extends Fragment {
    private FragmentAddEditNoteBinding binding;
    private CoursesViewModel coursesViewModel;
    private NotesViewModel notesViewModel;
    private List<Course> courses;
    private Course selectedCourse;
    private Note currentNote;
    private int id;
    private boolean isEdit;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ActionBar actionBar = ((MainActivity) requireActivity()).getSupportActionBar();
        binding = FragmentAddEditNoteBinding.inflate(inflater, container, false);
        coursesViewModel = new ViewModelProvider(this).get(CoursesViewModel.class);
        notesViewModel =
                new ViewModelProvider(this).get(NotesViewModel.class);

        loadCourses();

        if (getArguments() != null) {
            id = requireArguments().getInt(getString(R.string.note_id), 0);
            isEdit = requireArguments().getBoolean(getString(R.string.is_edit), false);
        }
        actionBar.setTitle(R.string.new_note);

        if (isEdit && id > 0) {
            actionBar.setTitle(getString(R.string.update_note));
            loadCurrentNote(id);
        }

        binding.saveNoteCardView.setOnClickListener(v -> {
            if (isEdit) updateNote();
            else addNote();
        });
        return binding.getRoot();
    }

    private void loadCourses() {
        coursesViewModel.getAllCourses().observe(getViewLifecycleOwner(), courses -> {
            this.courses = courses;
            CoursesSpinnerAdapter adapter =
                    new CoursesSpinnerAdapter(requireContext(),
                            android.R.layout.simple_spinner_item,
                            courses);
            binding.coursesSpinner.setAdapter(adapter);
            binding.coursesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    selectedCourse = adapter.getItem(position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        });
    }

    private void addNote() {
        try {
            Note note = new Note(selectedCourse.getId(),
                    binding.titleEditText.getText().toString(),
                    binding.contentEditText.getText().toString());
            notesViewModel.addNote(note).observe(getViewLifecycleOwner(), isAdded -> {
                if (isAdded) {
                    Navigation.findNavController(binding.getRoot())
                            .navigate(R.id.action_addEditNoteFragment_to_nav_notes);
                } else {
                    Toast.makeText(requireContext(),
                            getString(R.string.failed_to_add_note),
                            Toast.LENGTH_LONG)
                            .show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateNote() {
        try {
            currentNote.setCourseId(selectedCourse.getId());
            currentNote.setTitle(binding.titleEditText.getText().toString());
            currentNote.setContent(binding.contentEditText.getText().toString());
            notesViewModel.updateNote(currentNote).observe(getViewLifecycleOwner(), isUpdated -> {
                if (isUpdated) {
                    Navigation.findNavController(binding.getRoot())
                            .navigate(R.id.action_addEditNoteFragment_to_nav_notes);
                } else {
                    Toast.makeText(requireContext(),
                            getString(R.string.failed_to_update_note),
                            Toast.LENGTH_LONG)
                            .show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadCurrentNote(int id) {
        try {
            notesViewModel.findById(id).observe(getViewLifecycleOwner(), note -> {
                currentNote = note;

                for (int i = 0; i < courses.size(); i++) {
                    if (courses.get(i).getId() == note.getCourseId()) {
                        binding.coursesSpinner.setSelection(i);
                    }
                }
                binding.titleEditText.setText(note.getTitle());
                binding.contentEditText.setText(note.getContent());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}