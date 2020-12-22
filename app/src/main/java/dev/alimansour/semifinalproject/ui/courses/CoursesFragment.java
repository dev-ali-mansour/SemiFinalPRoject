package dev.alimansour.semifinalproject.ui.courses;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import dev.alimansour.semifinalproject.databinding.FragmentCoursesBinding;

public class CoursesFragment extends Fragment {
    private FragmentCoursesBinding binding;

    private CoursesViewModel coursesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCoursesBinding.inflate(inflater,container,false);
        coursesViewModel = new ViewModelProvider(this).get(CoursesViewModel.class);

        /*coursesViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {


            }
        });*/
        return binding.getRoot();
    }
}