package com.narus.crudcourse.controllers;

import com.narus.crudcourse.dtos.CourseRequest;
import com.narus.crudcourse.entities.Course;
import com.narus.crudcourse.services.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping()
    public List<Course> getAll() {
        return courseService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        Optional<Course> course = courseService.findById(id);
        if(course.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(course.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Course> save(@Valid @RequestBody CourseRequest courseRequest) {
        Course course = new Course(courseRequest.getName(), courseRequest.getPrice());
        return ResponseEntity.ok(courseService.save(course));
    }

    @PutMapping("/{id}")
    public  ResponseEntity<Course> update(@PathVariable Long id, @RequestBody CourseRequest course) {
        Optional<Course> courseOptional = courseService.update(id, course);
        if(courseOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(courseOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Course> courseOptional = courseService.delete(id);
        if(courseOptional.isPresent()){
            return ResponseEntity.ok(courseOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
}
