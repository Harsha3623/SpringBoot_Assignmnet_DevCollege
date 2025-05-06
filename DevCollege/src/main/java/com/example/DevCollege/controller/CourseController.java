package com.example.DevCollege.controller;

import com.example.DevCollege.dto.CourseAddUpdateDTO;
import com.example.DevCollege.exception.custom.IDNotFound;
import com.example.DevCollege.services.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/course")
public class CourseController {


    @Autowired
    private CourseService service;


    @PostMapping("/addcourse")
    public ResponseEntity<?> addCourseDetails(@Valid @RequestBody CourseAddUpdateDTO courseAddUpdateDTO) {

        return service.addCourseDetails(courseAddUpdateDTO);
    }


    @PutMapping("/updatecourse/{id}")
    public ResponseEntity<?> updateCourseDetails(@PathVariable String id, @Valid @RequestBody CourseAddUpdateDTO courseAddUpdateDTO) {
        return service.updateCourseDetails(id, courseAddUpdateDTO);
    }


    @DeleteMapping("/deletecourse/{id}")
    public ResponseEntity<?> deleteCourseDetails(@PathVariable String id) {
        return service.deleteCourseDetails(id);
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<?> getCourseDetail(@PathVariable String id) {
        return service.getCourseDetail(id);
    }


    @GetMapping("/getAll")
    public ResponseEntity<?> getAllCourseDetail() {
        return service.getAllCourseDetail();
    }

}
