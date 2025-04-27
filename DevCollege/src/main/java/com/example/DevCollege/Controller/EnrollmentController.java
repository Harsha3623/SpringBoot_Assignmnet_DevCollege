package com.example.DevCollege.Controller;

import com.example.DevCollege.DTO.EnrollmentAddDto;
import com.example.DevCollege.Services.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enrollment")
public class EnrollmentController {


    @Autowired
    private EnrollmentService service;



    @PostMapping("/add")
    public ResponseEntity<?> addEnrollmentForCourse(@RequestBody EnrollmentAddDto enrollmentAddDto){
        return service.addEnrollmentForCourse(enrollmentAddDto);
    }
}