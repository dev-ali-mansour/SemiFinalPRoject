package dev.alimansour.semifinalproject.ui.notes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dev.alimansour.semifinalproject.R;
import dev.alimansour.semifinalproject.adapter.NotesAdapter;
import dev.alimansour.semifinalproject.databinding.FragmentNotesBinding;
import dev.alimansour.semifinalproject.model.Note;
import dev.alimansour.semifinalproject.ui.NoteClickListener;

public class NotesFragment extends Fragment implements NoteClickListener {
    private FragmentNotesBinding binding;
    private NotesViewModel notesViewModel;
    private int courseId = 0;
    Bundle bundle;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNotesBinding.inflate(inflater, container, false);
        notesViewModel =
                new ViewModelProvider(this,
                        new NotesViewModelFactory(requireActivity().getApplication()))
                        .get(NotesViewModel.class);
        if (getArguments() != null) {
            courseId = requireArguments().getInt(getString(R.string.course_id), 0);
            requireActivity().setTitle(getString(R.string.course_notes));
            loadCourseNotes(courseId);
        } else {
            requireActivity().setTitle(getString(R.string.all_notes));
            loadAllNotes();
        }

        binding.newNoteCardView.setOnClickListener(v ->
                Navigation.findNavController(binding.getRoot())
                        .navigate(R.id.action_nav_notes_to_addEditNoteFragment));

        return binding.getRoot();
    }

    private void loadCourseNotes(int courseId) {
        notesViewModel.getCourseNotes(courseId).observe(getViewLifecycleOwner(), notes -> {
            if (notes.size() > 0) {
                initRecyclerView(notes);
            } else {
                binding.notesRecyclerView.setVisibility(View.GONE);
                binding.noNotesTextView.setVisibility(View.VISIBLE);
            }
        });
    }

    private void loadAllNotes() {
        try {
            notesViewModel.getAllNotes().observe(getViewLifecycleOwner(), notes -> {
                if (notes.size() > 0) {
                    initRecyclerView(notes);
                } else {
                    binding.notesRecyclerView.setVisibility(View.GONE);
                    binding.noNotesTextView.setVisibility(View.VISIBLE);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initRecyclerView(List<Note> notes) {
        try {
            RecyclerView recyclerView = binding.notesRecyclerView;
            recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
            recyclerView.setHasFixedSize(true);
            NotesAdapter adapter = new NotesAdapter();
            adapter.setDataSource(notes);
            adapter.setListener(this);
            recyclerView.setAdapter(adapter);
            recyclerView.setVisibility(View.VISIBLE);
            binding.noNotesTextView.setVisibility(View.GONE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdate(int id) {
        bundle = new Bundle();
        bundle.putInt(getString(R.string.note_id), id);
        bundle.putBoolean(getString(R.string.is_edit), true);
        Navigation.findNavController(binding.getRoot())
                .navigate(R.id.action_nav_notes_to_addEditNoteFragment, bundle);
    }

    @Override
    public void onDelete(Note note) {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
            builder.setIcon(R.drawable.ic_baseline_delete_forever_24);
            builder.setTitle(R.string.delete_confirmation);
            builder.setMessage(R.string.delete_note_confirmation);
            builder.setCancelable(false);
            builder.setNegativeButton(R.string.yes, (dialog, which) ->
                    deleteNote(note));
            builder.setPositiveButton(R.string.no, null);
            builder.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteNote(Note note) {
        try {
            notesViewModel.deleteNote(note)
                    .observe(getViewLifecycleOwner(), isDeleted -> {
                        if (isDeleted) {
                            if (courseId == 0) {
                                loadAllNotes();
                            }else {
                                loadCourseNotes(courseId);
                            }
                        } else {
                            Toast.makeText(requireContext(),
                                    getString(R.string.failed_to_delete_note) + note.getTitle(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}