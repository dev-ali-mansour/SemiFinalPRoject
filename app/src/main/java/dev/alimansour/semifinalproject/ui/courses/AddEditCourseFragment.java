package dev.alimansour.semifinalproject.ui.courses;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import dev.alimansour.semifinalproject.R;
import dev.alimansour.semifinalproject.databinding.FragmentAddEditCourseBinding;
import dev.alimansour.semifinalproject.domain.model.Course;

public class AddEditCourseFragment extends Fragment {
    private FragmentAddEditCourseBinding binding;
    private CoursesViewModel coursesViewModel;
    private int id;
    private boolean isEdit;
    private Course currentCourse;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requireActivity().setTitle(getString(R.string.new_course));
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAddEditCourseBinding.inflate(inflater, container, false);
        try {
            coursesViewModel =
                    new ViewModelProvider(this,
                            new CoursesViewModelFactory(requireActivity().getApplication()))
                            .get(CoursesViewModel.class);

            if (getArguments() != null) {
                id = requireArguments().getInt(getString(R.string.course_id), 0);
                isEdit = requireArguments().getBoolean(getString(R.string.is_edit), false);
            }

            if (isEdit && id > 0) {
                requireActivity().setTitle(getString(R.string.update_course));
                loadCurrentCourse(id);
            }

            binding.saveCourseCardView.setOnClickListener(v -> {
                if (isEdit) updateCourse();
                else addCourse();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return binding.getRoot();
    }

    private void addCourse() {
        try {
            Course course = new Course(binding.nameEditText.getText().toString(),
                    binding.descriptionEditText.getText().toString());
            coursesViewModel.addCourse(course).observe(getViewLifecycleOwner(), isAdded -> {
                if (isAdded) {
                    Navigation.findNavController(binding.getRoot())
                            .navigate(R.id.action_addEditCourseFragment_to_nav_courses);
                } else {
                    Toast.makeText(requireContext(),
                            getString(R.string.failed_to_add_course),
                            Toast.LENGTH_LONG)
                            .show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateCourse() {
        try {
            currentCourse.setName(binding.nameEditText.getText().toString());
            currentCourse.setDescription(binding.descriptionEditText.getText().toString());
            coursesViewModel.updateCourse(currentCourse).observe(getViewLifecycleOwner(), isUpdated -> {
                if (isUpdated) {
                    Navigation.findNavController(binding.getRoot())
                            .navigate(R.id.action_addEditCourseFragment_to_nav_courses);
                } else {
                    Toast.makeText(requireContext(),
                            getString(R.string.failed_to_update_course),
                            Toast.LENGTH_LONG)
                            .show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadCurrentCourse(int id) {
        try {
            coursesViewModel.findById(id).observe(getViewLifecycleOwner(), course -> {
                currentCourse = course;
                binding.nameEditText.setText(course.getName());
                binding.descriptionEditText.setText(course.getDescription());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}