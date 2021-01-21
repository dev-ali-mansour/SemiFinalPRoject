package dev.alimansour.semifinalproject.data.repository;

import android.os.AsyncTask;

import java.util.List;

import dev.alimansour.semifinalproject.data.AppDatabase;
import dev.alimansour.semifinalproject.domain.model.Course;
import dev.alimansour.semifinalproject.domain.repository.CourseRepository;
import dev.alimansour.semifinalproject.presentation.ApplicationProvider;

/**
 * SemiFinalPRoject Android Application developed by: Ali Mansour
 * Copyright © 2021 Ali Mansour. All Rights Reserved.
 * This file may not be redistributed in whole or significant part.
 * ----------------- SemiFinalPRoject IS FREE SOFTWARE ------------------
 * https://www.alimansour.dev   |   dev.ali.mansour@gmail.com
 */
public class CourseRepositoryImpl implements CourseRepository {
    private static AppDatabase database;

    public CourseRepositoryImpl() {
        ApplicationProvider provider = ApplicationProvider.getInstance();
        database = AppDatabase.getInstance(provider.getApp());
    }

    @Override
    public Boolean addCourse(Course course) {
        try {
            return new AddCourse().execute(course).get();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean updateCourse(Course course) {
        try {
            return new UpdateCourse().execute(course).get();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean deleteCourse(Course course) {
        try {
            return new DeleteCourse().execute(course).get();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Course findById(int id) {
        try {
            return new FindCourse().execute(id).get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Course> getAllCourses() {
        try {
            return new GetAllCourses().execute().get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private final static class AddCourse extends AsyncTask<Course, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Course... courses) {
            database.coursesDao().insert(courses[0]);
            return true;
        }
    }

    private final static class UpdateCourse extends AsyncTask<Course, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Course... courses) {
            database.coursesDao().update(courses[0]);
            return true;
        }
    }

    private final static class DeleteCourse extends AsyncTask<Course, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Course... courses) {
            database.coursesDao().delete(courses[0]);
            return true;
        }
    }

    private final static class FindCourse extends AsyncTask<Integer, Void, Course> {

        @Override
        protected Course doInBackground(Integer... integers) {
            return database.coursesDao().findById(integers[0]);
        }
    }

    private final static class GetAllCourses extends AsyncTask<Void, Void, List<Course>> {

        @Override
        protected List<Course> doInBackground(Void... voids) {
            return database.coursesDao().getAllCourses();
        }
    }
}
