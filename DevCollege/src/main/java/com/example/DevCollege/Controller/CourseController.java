package com.example.DevCollege.Controller;

import com.example.DevCollege.DTO.CourseDto;
import com.example.DevCollege.Services.CourseService;
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
    public ResponseEntity<?> addCourseDetails(@Valid @RequestBody CourseDto courseDto){
        return service.addCourseDetails(courseDto);
    }

    @PutMapping("/updatecourse/{id}")
    public ResponseEntity<?> updateCourseDetails(@PathVariable String id,@Valid @RequestBody CourseDto courseDto){
        return service.updateCourseDetails(id,courseDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCourseDetails(@PathVariable String id){
        return service.deleteCourseDetails(id);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getCourseDetail(@PathVariable String id){
        return service.getCourseDetail(id);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllCourseDetail(){
        return service.getAllCourseDetail();
    }
}
