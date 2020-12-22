package dev.alimansour.semifinalproject.ui;

import dev.alimansour.semifinalproject.model.Course;

/**
 * SemiFinalPRoject Android Application developed by: Ali Mansour
 * Copyright © 2020 Ali Mansour. All Rights Reserved.
 * This file may not be redistributed in whole or significant part.
 * ----------------- SemiFinalPRoject IS FREE SOFTWARE ------------------
 * https://www.alimansour.dev   |   dev.ali.mansour@gmail.com
 */
public interface CourseClickListener {

    public void onClick(int id);

    public void onUpdate(Course course);

    public void onDelete(Course course);
}
