package com.example.DevCollege.controller;

import com.example.DevCollege.dto.EnrollmentAddDto;
import com.example.DevCollege.dto.EnrollmentStatus;
import com.example.DevCollege.services.EnrollmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enrolment")
public class EnrollmentController {


    @Autowired
    private EnrollmentService service;



    @PostMapping("/add")
    public ResponseEntity<?> addEnrollmentForCourse(@RequestBody EnrollmentAddDto enrollmentAddDto){
        return service.addEnrollmentForCourse(enrollmentAddDto);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getEnrollmentDetailsUsingID(@PathVariable String id){
        return service.getEnrollmentDetailsUsingID(id);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllEnrollmentDetails(){
        return service.getAllEnrollmentDetails();
    }

    //get enrollment details for student
    @GetMapping("/get/student/{stdId}")
    public ResponseEntity<?> getEnrollmentDetailsForStudent(@PathVariable String stdId){
        return service.getEnrollmentDetailsForStudent(stdId);
    }

    //change status
    @PostMapping("/status/{enrollId}")
    public ResponseEntity<?> changeStatus(@PathVariable String enrollId,
                                          @Valid @RequestBody EnrollmentStatus enrollmentStatus){
        return service.changeStatus(enrollId,enrollmentStatus);
    }

    //checking the availability of the course
    @GetMapping("/availability/{courseId}")
    public ResponseEntity<?> checkAvailability(@PathVariable String courseId){
        return service.checkAvailability(courseId);
    }

    //course suggestion
    @GetMapping("/suggestion/{studentId}")
    public ResponseEntity<?> courseSuggestion(@PathVariable String studentId){
        return service.courseSuggestion(studentId);
    }


}