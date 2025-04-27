package com.example.DevCollege.Services;

import com.example.DevCollege.DTO.EnrollmentAddDto;
import org.springframework.http.ResponseEntity;

public interface EnrollmentService {


    ResponseEntity<?> addEnrollmentForCourse(EnrollmentAddDto enrollmentAddDto);
}
