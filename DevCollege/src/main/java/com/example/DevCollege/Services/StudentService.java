package com.example.DevCollege.Services;

import com.example.DevCollege.DTO.StudentDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

public interface StudentService {


    ResponseEntity<?> addStudentDetail(@Valid StudentDto studentDto);


}
