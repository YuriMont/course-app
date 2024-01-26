package com.api.server.services;

import com.api.server.dtos.course.CourseRequestDTO;
import com.api.server.dtos.course.CourseResponseDTO;
import com.api.server.exceptions.CourseNotFoundException;
import com.api.server.models.CourseModel;
import com.api.server.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<CourseModel> getAllCourses(){
        return courseRepository.findAll();
    }

    public CourseResponseDTO findCourseById(Long courseId) throws CourseNotFoundException {
        CourseModel course = courseRepository.findById(courseId).orElseThrow(
                () -> new CourseNotFoundException("Course not found")
        );

        return new CourseResponseDTO(course);
    }

    public CourseResponseDTO create(CourseRequestDTO courseRequestDTO){
        CourseModel course = new CourseModel(courseRequestDTO);

        return new CourseResponseDTO(courseRepository.save(course));
    }
}
