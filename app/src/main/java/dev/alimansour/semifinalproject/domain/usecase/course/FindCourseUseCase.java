package dev.alimansour.semifinalproject.domain.usecase.course;

import dev.alimansour.semifinalproject.domain.model.Course;
import dev.alimansour.semifinalproject.domain.repository.CourseRepository;

/**
 * SemiFinalPRoject Android Application developed by: Ali Mansour
 * Copyright © 2021 Ali Mansour. All Rights Reserved.
 * This file may not be redistributed in whole or significant part.
 * ----------------- SemiFinalPRoject IS FREE SOFTWARE ------------------
 * https://www.alimansour.dev   |   dev.ali.mansour@gmail.com
 */
public class FindCourseUseCase {
    private CourseRepository repository;

    public FindCourseUseCase(CourseRepository repository) {
        this.repository = repository;
    }

    Course execute(int id) {
        return repository.findById(id);
    }
}
