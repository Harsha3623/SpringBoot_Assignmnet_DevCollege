package com.example.DevCollege.controller;

import com.example.DevCollege.dto.StudentAddUpdateDTO;
import com.example.DevCollege.dto.StudentDto;
import com.example.DevCollege.dto.StudentWalletAmountDto;
import com.example.DevCollege.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {



    @Autowired
    StudentService service;



    @PostMapping("/addstudent")
    public ResponseEntity<?> addStudentDetail(@Valid @RequestBody StudentAddUpdateDTO studentAddUpdateDTO){
        return service.addStudentDetail(studentAddUpdateDTO);
    }




    //update Student
    @PutMapping("/updatestudent/{stdId}")
    public ResponseEntity<?> updateStudentDetail(@PathVariable String stdId, @Valid @RequestBody StudentAddUpdateDTO studentAddUpdateDTO){
        return service.updateStudentDetail(stdId,studentAddUpdateDTO);
    }



    //deleting the student details
    @DeleteMapping("/deletestudent/{stdId}")
    public ResponseEntity<?> deleteStudentDetail(@PathVariable String stdId){
        return service.deleteStudentDetail(stdId);
    }

    //getting student details
    @GetMapping("/get/{stdId}")
    public ResponseEntity<?> getStudentDetail(@PathVariable String stdId){
        return service.getStudentDetail(stdId);
    }




    //getting all student details
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllStudentDetail(){
        return service.getAllStudentDetail();
    }



    //Add Wallet amount
    @PostMapping("/studentwallet/{stdId}")
    public ResponseEntity<?> addWalletAmount(@PathVariable String stdId, @Valid @RequestBody StudentWalletAmountDto studentWalletAmountDto){
        return service.addWalletAmount(stdId,studentWalletAmountDto);
    }



    //get wallet details
    @GetMapping("/studentwallet/{stdId}")
    public ResponseEntity<?> getWalletDetail(@PathVariable String stdId){
        return service.getWalletDetail(stdId);
    }

}
