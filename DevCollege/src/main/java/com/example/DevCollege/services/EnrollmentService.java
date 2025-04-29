package com.example.DevCollege.services;

import com.example.DevCollege.dto.EnrollmentAddDto;
import com.example.DevCollege.dto.EnrollmentStatus;
import org.springframework.http.ResponseEntity;

public interface EnrollmentService {


    ResponseEntity<?> addEnrollmentForCourse(EnrollmentAddDto enrollmentAddDto);

    ResponseEntity<?> getEnrollmentDetailsUsingID(String id);

    ResponseEntity<?> getAllEnrollmentDetails();

    ResponseEntity<?> getEnrollmentDetailsForStudent(String stdId);

    ResponseEntity<?> changeStatus(String enrollId, EnrollmentStatus enrollmentStatus);

    ResponseEntity<?> checkAvailability(String courseId);

    ResponseEntity<?> courseSuggestion(String studentId);
}