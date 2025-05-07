package com.example.DevCollege.services;

import com.example.DevCollege.dto.CourseAddUpdateDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

public interface CourseService {


    ResponseEntity<?> addCourseDetails(CourseAddUpdateDTO courseAddUpdateDTO);

    ResponseEntity<?> updateCourseDetails(String id, @Valid CourseAddUpdateDTO courseAddUpdateDTO);

    ResponseEntity<?> deleteCourseDetails(String id);

    ResponseEntity<?> getCourseDetail(String id);

    ResponseEntity<?> getAllCourseDetail();
}
