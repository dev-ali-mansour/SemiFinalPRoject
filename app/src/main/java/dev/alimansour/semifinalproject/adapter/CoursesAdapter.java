package dev.alimansour.semifinalproject.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import dev.alimansour.semifinalproject.databinding.CoursesListItemBinding;
import dev.alimansour.semifinalproject.model.Course;
import dev.alimansour.semifinalproject.ui.CourseClickListener;

/**
 * SemiFinalPRoject Android Application developed by: Ali Mansour
 * Copyright © 2020 Ali Mansour. All Rights Reserved.
 * This file may not be redistributed in whole or significant part.
 * ----------------- SemiFinalPRoject IS FREE SOFTWARE ------------------
 * https://www.alimansour.dev   |   dev.ali.mansour@gmail.com
 */
public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.CoursesViewHolder> {
    private List<Course> courses;
    private CourseClickListener listener;

    @NonNull
    @Override
    public CoursesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CoursesListItemBinding binding = CoursesListItemBinding.inflate(inflater, parent, false);
        CoursesViewHolder viewHolder = new CoursesViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CoursesViewHolder holder, int position) {
        Course course = courses.get(position);
        holder.bind(course);
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public void setDataSource(List<Course> courses) {
        this.courses = courses;
    }

    public void setClickListener(CourseClickListener listener) {
        this.listener = listener;
    }

    public class CoursesViewHolder extends RecyclerView.ViewHolder {
        private final CoursesListItemBinding binding;

        public CoursesViewHolder(@NonNull CoursesListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Course course) {
            binding.nameTextView.setText(course.getName());
            binding.descriptionTextView.setText(course.getDescription());
            binding.getRoot().setOnClickListener(v -> listener.onClick(course.getId()));
            binding.updateButton.setOnClickListener(v -> listener.onUpdate(course));
            binding.deleteButton.setOnClickListener(v -> listener.onDelete(course));
        }
    }
}
