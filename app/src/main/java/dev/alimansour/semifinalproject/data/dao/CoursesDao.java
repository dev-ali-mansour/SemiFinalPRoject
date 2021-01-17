package dev.alimansour.semifinalproject.data.dao;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import dev.alimansour.semifinalproject.domain.model.Course;

/**
 * SemiFinalPRoject Android Application developed by: Ali Mansour
 * Copyright © 2020 Ali Mansour. All Rights Reserved.
 * This file may not be redistributed in whole or significant part.
 * ----------------- SemiFinalPRoject IS FREE SOFTWARE ------------------
 * https://www.alimansour.dev   |   dev.ali.mansour@gmail.com
 */
@Dao
public interface CoursesDao {

    @Insert
    public void insert(Course course);

    @Update
    public void update(Course course);

    @Delete
    public void delete(Course course);

    @Query("SELECT * FROM courses WHERE id = :id")
    public Course findById(int id);

    @Query("SELECT * FROM COURSES WHERE 1")
    public List<Course> getAllCourses();
}
