package dev.alimansour.semifinalproject.ui.courses;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import dev.alimansour.semifinalproject.databinding.FragmentAddEditCourseBinding;

public class AddEditCourseFragment extends Fragment {
    private FragmentAddEditCourseBinding binding;
    private CoursesViewModel coursesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAddEditCourseBinding.inflate(inflater, container, false);
        coursesViewModel =
                new ViewModelProvider(this).get(CoursesViewModel.class);

        return binding.getRoot();
    }
}