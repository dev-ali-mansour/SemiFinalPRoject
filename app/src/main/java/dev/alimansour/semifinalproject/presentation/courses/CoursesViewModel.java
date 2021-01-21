package dev.alimansour.semifinalproject.presentation.courses;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import dev.alimansour.semifinalproject.data.repository.CourseRepositoryImpl;
import dev.alimansour.semifinalproject.domain.model.Course;
import dev.alimansour.semifinalproject.domain.usecase.course.AddCourseUseCase;
import dev.alimansour.semifinalproject.domain.usecase.course.DeleteCourseUseCase;
import dev.alimansour.semifinalproject.domain.usecase.course.FindCourseUseCase;
import dev.alimansour.semifinalproject.domain.usecase.course.GetAllCoursesUseCase;
import dev.alimansour.semifinalproject.domain.usecase.course.UpdateCourseUseCase;

public class CoursesViewModel extends ViewModel {
    private CourseRepositoryImpl repository = new CourseRepositoryImpl();
    private static final MutableLiveData<Boolean> isAdded = new MutableLiveData<>();
    private static final MutableLiveData<Boolean> isUpdated = new MutableLiveData<>();
    private static final MutableLiveData<Boolean> isDeleted = new MutableLiveData<>();
    private static final MutableLiveData<Course> courseLiveData = new MutableLiveData<>();
    private static final MutableLiveData<List<Course>> coursesLiveData = new MutableLiveData<>();

    public LiveData<Boolean> addCourse(Course course) {
        isAdded.setValue(new AddCourseUseCase(repository).execute(course));
        return isAdded;
    }

    public LiveData<Boolean> updateCourse(Course course) {
        isUpdated.setValue(new UpdateCourseUseCase(repository).execute(course));
        return isUpdated;
    }

    public LiveData<Boolean> deleteCourse(Course course) {
        isDeleted.setValue(new DeleteCourseUseCase(repository).execute(course));
        return isDeleted;
    }

    public LiveData<Course> findById(int id) {
        courseLiveData.setValue(new FindCourseUseCase(repository).execute(id));
        return courseLiveData;
    }

    public LiveData<List<Course>> getAllCourses() {
        coursesLiveData.setValue(new GetAllCoursesUseCase(repository).execute());
        return coursesLiveData;
    }


}