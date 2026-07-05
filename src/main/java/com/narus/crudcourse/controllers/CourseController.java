package com.narus.crudcourse.controllers;

import com.narus.crudcourse.entities.Course;
import com.narus.crudcourse.services.CourseService;
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

    @PostMapping
    public ResponseEntity<Course> save(@RequestBody Course course) {
        return ResponseEntity.ok(courseService.save(course));
    }

    @PutMapping("/{id}")
    public  ResponseEntity<Course> update(@PathVariable Long id, @RequestBody Course course) {
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
