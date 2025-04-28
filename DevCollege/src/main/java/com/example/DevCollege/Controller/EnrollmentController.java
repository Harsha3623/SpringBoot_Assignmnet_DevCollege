package com.example.DevCollege.Controller;

import com.example.DevCollege.DTO.EnrollmentAddDto;
import com.example.DevCollege.Services.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enrollment")
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
    public ResponseEntity<?> changeStatus(@PathVariable String enrollId){
        return service.changeStatus(enrollId);
    }
}