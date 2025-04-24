package com.example.DevCollege.Controller;

import com.example.DevCollege.DTO.StudentDto;
import com.example.DevCollege.Services.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {


    @Autowired
    StudentService service;

    @PostMapping("/addstudent")
    public ResponseEntity<?> addStudentDetail(@Valid @RequestBody StudentDto studentDto){
        return service.addStudentDetail(studentDto);
    }


}
