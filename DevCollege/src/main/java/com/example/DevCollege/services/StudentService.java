package com.example.DevCollege.services;

import com.example.DevCollege.dto.StudentAddUpdateDTO;
import com.example.DevCollege.dto.StudentDto;
import com.example.DevCollege.dto.StudentWalletAmountDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

public interface StudentService {


    ResponseEntity<?> addStudentDetail(@Valid StudentAddUpdateDTO studentAddUpdateDTO);


    ResponseEntity<?> updateStudentDetail(String stdId, @Valid StudentAddUpdateDTO studentAddUpdateDTO);

    ResponseEntity<?> deleteStudentDetail(String stdId);

    ResponseEntity<?> getStudentDetail(String stdId);

    ResponseEntity<?> getAllStudentDetail();

    ResponseEntity<?> addWalletAmount(String stdId, StudentWalletAmountDto studentWalletAmountDto);

    ResponseEntity<?> getWalletDetail(String stdId);
}
