package com.example.DevCollege.Services;

import com.example.DevCollege.DTO.StudentDto;
import com.example.DevCollege.DTO.StudentWalletAmountDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

public interface StudentService {


    ResponseEntity<?> addStudentDetail(@Valid StudentDto studentDto);


    ResponseEntity<?> updateStudentDetail(String stdId, @Valid StudentDto studentDto);

    ResponseEntity<?> deleteStudentDetail(String stdId);

    ResponseEntity<?> getStudentDetail(String stdId);

    ResponseEntity<?> getAllStudentDetail();

    ResponseEntity<?> addWalletAmount(String stdId, StudentWalletAmountDto studentWalletAmountDto);

    ResponseEntity<?> getWalletDetail(String stdId);
}
