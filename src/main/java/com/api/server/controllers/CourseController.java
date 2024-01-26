package com.api.server.controllers;

import com.api.server.dtos.course.CourseRequestDTO;
import com.api.server.exceptions.CourseNotFoundException;
import com.api.server.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/")
    public ResponseEntity<?> getAllCourses(){
        return ResponseEntity.ok().body(courseService.getAllCourses());
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCourse(@RequestBody CourseRequestDTO courseRequestDTO){
        return ResponseEntity.ok().body(courseService.create(courseRequestDTO));
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<?> getCourse(@PathVariable Long courseId) throws CourseNotFoundException {
        return ResponseEntity.ok().body(courseService.findCourseById(courseId));
    }
}
