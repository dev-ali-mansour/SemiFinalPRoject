package dev.alimansour.semifinalproject.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import dev.alimansour.semifinalproject.databinding.NotesListItemBinding;
import dev.alimansour.semifinalproject.domain.model.Note;
import dev.alimansour.semifinalproject.presentation.NoteClickListener;

/**
 * SemiFinalPRoject Android Application developed by: Ali Mansour
 * Copyright © 2020 Ali Mansour. All Rights Reserved.
 * This file may not be redistributed in whole or significant part.
 * ----------------- SemiFinalPRoject IS FREE SOFTWARE ------------------
 * https://www.alimansour.dev   |   dev.ali.mansour@gmail.com
 */
public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {
    private List<Note> notes;
    private NoteClickListener listener;

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        NotesListItemBinding binding = NotesListItemBinding.inflate(inflater, parent, false);
        return new NotesViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.bind(note);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setDataSource(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    public void setListener(NoteClickListener listener) {
        this.listener = listener;
    }

    public class NotesViewHolder extends RecyclerView.ViewHolder {
        private NotesListItemBinding binding;

        public NotesViewHolder(@NonNull NotesListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Note note) {
            binding.titleTextView.setText(note.getTitle());
            binding.contentTextView.setText(note.getContent());

            binding.updateButton.setOnClickListener(v -> listener.onUpdate(note.getId()));
            binding.deleteButton.setOnClickListener(v -> listener.onDelete(note));
        }
    }
}
