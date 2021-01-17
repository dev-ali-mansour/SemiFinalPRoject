package dev.alimansour.semifinalproject.domain.repository;

import java.util.List;

import dev.alimansour.semifinalproject.domain.model.Course;

/**
 * SemiFinalPRoject Android Application developed by: Ali Mansour
 * Copyright © 2021 Ali Mansour. All Rights Reserved.
 * This file may not be redistributed in whole or significant part.
 * ----------------- SemiFinalPRoject IS FREE SOFTWARE ------------------
 * https://www.alimansour.dev   |   dev.ali.mansour@gmail.com
 */
public interface CourseRepository {

    Boolean addCourse(Course course);

    Boolean updateCourse(Course course);

    Boolean deleteCourse(Course course);

    Course findById(int id);

    List<Course> getAllCourses();
}