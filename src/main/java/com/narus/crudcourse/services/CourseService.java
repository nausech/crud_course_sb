package com.narus.crudcourse.services;

import com.narus.crudcourse.dtos.CourseRequest;
import com.narus.crudcourse.entities.Course;

import java.util.List;
import java.util.Optional;


public interface CourseService {

    List<Course> findAll();
    Optional<Course> findById(Long id);
    Course save(Course course);
    Optional<Course> update(Long id, CourseRequest course);
    Optional<Course> delete(Long id);
}
