package com.narus.crudcourse.services;

import com.narus.crudcourse.dtos.CourseRequest;
import com.narus.crudcourse.entities.Course;
import com.narus.crudcourse.repositories.CourseRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService{


    private final CourseRepository repository;

    public CourseServiceImpl(CourseRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public List<Course> findAll(){
        return (List<Course>) repository.findAll();
    }

    @Override
    @Transactional( readOnly = true )
    public Optional<Course> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Course save(Course course) {
        return repository.save(course);
    }

    @Override
    @Transactional
    public Optional<Course> update(Long id, CourseRequest course){
        Optional<Course> courseOptional = repository.findById(id);
        if(courseOptional.isPresent()){
            Course courseDb = courseOptional.orElseThrow();
            courseDb.setName(course.getName());
            courseDb.setPrice(course.getPrice());
        }
        return courseOptional;
    }

    @Override
    @Transactional
    public Optional<Course> delete(Long id) {
        Optional<Course> courseOptional = repository.findById(id);
        courseOptional.ifPresent(repository::delete);
        return courseOptional;
    }

}
