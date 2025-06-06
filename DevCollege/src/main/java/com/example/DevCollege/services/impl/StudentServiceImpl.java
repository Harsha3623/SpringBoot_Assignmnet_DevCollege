package com.example.DevCollege.services.impl;

import com.example.DevCollege.dto.StudentAddUpdateDTO;
import com.example.DevCollege.dto.StudentDto;
import com.example.DevCollege.dto.StudentWalletAmountDto;
import com.example.DevCollege.entity.Enrollment;
import com.example.DevCollege.entity.Student;
import com.example.DevCollege.exception.custom.IDNotFound;
import com.example.DevCollege.mapper.StudentAddUpdateMapper;
import com.example.DevCollege.mapper.StudentMapper;
import com.example.DevCollege.mapper.StudentWalletAmountMapper;
import com.example.DevCollege.repository.EnrollmentRepository;
import com.example.DevCollege.repository.StudentRepository;
import com.example.DevCollege.response.ApiResponse;
import com.example.DevCollege.services.StudentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {


    @Autowired
    StudentRepository studentRepository;


    @Autowired
    StudentMapper studentMapper;


    @Autowired
    StudentAddUpdateMapper studentAddUpdateMapper;

    @Autowired
    EnrollmentRepository enrollmentRepository;


    //wallet amount mapper
    @Autowired
    StudentWalletAmountMapper studentWalletAmountMapper;


    private String generateStudentId() {
        Student student = studentRepository.findTopByOrderByStudentIdDesc();

        if (student == null) {

            return "STD0001";
        }

        String studentId = student.getStudentId();

        int numericId = Integer.parseInt(studentId.substring(3));

        return String.format("STD%04d", numericId + 1);

    }





    @Override
    @Transactional
    public ResponseEntity<?> addStudentDetail(StudentAddUpdateDTO studentAddUpdateDTO) {

        Student student = studentAddUpdateMapper.studentAddUpdateDTOToStudent(studentAddUpdateDTO);

        String studentID = generateStudentId();

        String[] qualifications = studentAddUpdateDTO.getHighestQualification().split(",");


        student.setStudentId(studentID);

        studentRepository.save(student);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("Successfully Added Student details for " + studentID));


    }







    @Override
    @Transactional
    public ResponseEntity<?> updateStudentDetail(String stdId, StudentAddUpdateDTO studentAddUpdateDTO) {

        //find student by id
        Student student = studentRepository.findById(stdId).orElse(null);

        if (student == null) {

            throw new IDNotFound("Student Id","Student Id: "+stdId+" is not present");
        }

        //updating the student details
        student.setName(studentAddUpdateDTO.getName());
        student.setHighestQualification(studentAddUpdateDTO.getHighestQualification());
        student.setContactNo(studentAddUpdateDTO.getContactNo());

        //save the updated student detail back to db
        studentRepository.save(student);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("Successfully updated student details for id: " + stdId));

    }






    @Override
    @Transactional
    public ResponseEntity<?> deleteStudentDetail(String stdId) {

        Student student = studentRepository.findById(stdId).orElse(null);

        //if it is not present
        if (student == null) {


            throw new IDNotFound("Student Id","Student Id: "+stdId+" not present.");

        }

        //get all the student enrollment details
        List<Enrollment> studentEnrollment = enrollmentRepository.findByStudent_StudentId(stdId);

        //if it is empty then student is not assigned to any course
        if (studentEnrollment.isEmpty()) {

            studentRepository.deleteById(stdId);

            return ResponseEntity.status((HttpStatus.OK))
                    .body(ApiResponse.success("Successfully deleted Student details for id:" + stdId));
        }

        float refundAmount = 0;

        //calculate the refund amount
        for (Enrollment e : studentEnrollment) {

            //calculate refund only if it is allocated
            if ("Allocated".equalsIgnoreCase(e.getStatus())) {

                refundAmount += e.getCourse().getFee() * ((float) 50 / 100);
            }
        }

        studentEnrollment.stream()
                .map(e -> {
                    e.setStudent(null);
                    e.setStatus("Cancelled");
                    return e;
                })
                .collect(Collectors.toList());

        enrollmentRepository.saveAll(studentEnrollment);


        studentRepository.deleteById(stdId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("Successfully deleted Student details with " + stdId +
                        " And amount " + refundAmount +
                        " will be refunded in original payment method within 24 hours."));


    }









    @Override
    public ResponseEntity<ApiResponse<StudentDto>> getStudentDetail(String stdId) {


        Student student = studentRepository.findById(stdId).orElse(null);

        if (student == null) {

            throw new IDNotFound("Student Id","Student Id: "+stdId+" not present.");
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(studentMapper.studentToStudentDto(student)));

    }







    @Override
    public ResponseEntity<ApiResponse<StudentDto>> getAllStudentDetail() {

        List<Student> students = studentRepository.findAll();

        if (students.isEmpty()) {

            throw new IDNotFound("Student Data","No Data Found in Student table");
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(students.stream()
                        .map(s -> studentMapper.studentToStudentDto(s))
                        .collect(Collectors.toList())));
    }







    @Override
    public ResponseEntity<?> addWalletAmount(String stdId, StudentWalletAmountDto studentWalletAmountDto) {

        Student student = studentRepository.findById(stdId).orElse(null);

        if (student == null) {

            throw new IDNotFound("Student Id","Student Id: "+stdId+" not present.");

        }

        //saving the wallet amount and by adding the wallet amount
        student.setWalletAmount(student.getWalletAmount() + studentWalletAmountDto.getWalletAmount());

        studentRepository.save(student);


        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("Successfully added Amount for student id: " + stdId +
                        " and available balance is " + student.getWalletAmount()));

    }







    @Override
    public ResponseEntity<ApiResponse<StudentWalletAmountDto>> getWalletDetail(String stdId) {

        Student student = studentRepository.findById(stdId).orElse(null);

        if (student == null) {

            throw new IDNotFound("Student Id","Student Id: "+stdId+" not present.");

        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(studentWalletAmountMapper.studentToStudentWalletAmountDto(student)));

    }


}
