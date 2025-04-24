package com.example.DevCollege.Services;

import com.example.DevCollege.DTO.CourseDto;
import com.example.DevCollege.Mapper.CourseMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

public interface CourseService {


    ResponseEntity<?> addCourseDetails(CourseDto courseDto);

    ResponseEntity<?> updateCourseDetails(String id, @Valid CourseDto courseDto);

    ResponseEntity<?> deleteCourseDetails(String id);

    ResponseEntity<?> getCourseDetail(String id);

    ResponseEntity<?> getAllCourseDetail();
}
