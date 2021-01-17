package dev.alimansour.semifinalproject.ui.courses;

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
import dev.alimansour.semifinalproject.adapter.CoursesAdapter;
import dev.alimansour.semifinalproject.databinding.FragmentCoursesBinding;
import dev.alimansour.semifinalproject.domain.model.Course;
import dev.alimansour.semifinalproject.ui.CourseClickListener;

public class CoursesFragment extends Fragment implements CourseClickListener {
    private FragmentCoursesBinding binding;
    private CoursesViewModel coursesViewModel;
    private Bundle bundle;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCoursesBinding.inflate(inflater, container, false);
        coursesViewModel = new ViewModelProvider(this,
                new CoursesViewModelFactory(requireActivity().getApplication()))
                .get(CoursesViewModel.class);
        requireActivity().setTitle(getString(R.string.all_courses));
        loadCourses();

        binding.newCourseCardView.setOnClickListener(v ->
                Navigation.findNavController(binding.getRoot())
                        .navigate(R.id.action_nav_courses_to_addEditCourseFragment)
        );

        return binding.getRoot();
    }

    private void loadCourses() {
        try {
            coursesViewModel.getAllCourses().observe(getViewLifecycleOwner(), courses -> {
                if (courses.size() > 0) {
                    initRecyclerView(courses);
                } else {
                    binding.coursesRecyclerView.setVisibility(View.GONE);
                    binding.noCoursesTextView.setVisibility(View.VISIBLE);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initRecyclerView(List<Course> courses) {
        try {
            RecyclerView recyclerView = binding.coursesRecyclerView;
            recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
            recyclerView.setHasFixedSize(true);
            CoursesAdapter adapter = new CoursesAdapter();
            adapter.setDataSource(courses);
            adapter.setListener(this);
            recyclerView.setAdapter(adapter);
            recyclerView.setVisibility(View.VISIBLE);
            binding.noCoursesTextView.setVisibility(View.GONE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(int id) {
        bundle = new Bundle();
        bundle.putInt(getString(R.string.course_id), id);
        Navigation.findNavController(binding.getRoot())
                .navigate(R.id.action_nav_courses_to_nav_notes, bundle);
    }

    @Override
    public void onUpdate(int id) {
        bundle = new Bundle();
        bundle.putInt(getString(R.string.course_id), id);
        bundle.putBoolean(getString(R.string.is_edit), true);
        Navigation.findNavController(binding.getRoot())
                .navigate(R.id.action_nav_courses_to_addEditCourseFragment, bundle);
    }

    @Override
    public void onDelete(Course course) {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
            builder.setIcon(R.drawable.ic_baseline_delete_forever_24);
            builder.setTitle(R.string.delete_confirmation);
            builder.setMessage(R.string.delete_course_confirmation);
            builder.setCancelable(false);
            builder.setNegativeButton(R.string.yes, (dialog, which) ->
                    deleteCourse(course));
            builder.setPositiveButton(R.string.no, null);
            builder.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteCourse(Course course) {
        try {
            coursesViewModel.deleteCourse(course)
                    .observe(getViewLifecycleOwner(), isDeleted -> {
                        if (isDeleted) {
                            loadCourses();
                        } else {
                            Toast.makeText(requireContext(),
                                    getString(R.string.failed_to_delete_course) + course.getName(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}