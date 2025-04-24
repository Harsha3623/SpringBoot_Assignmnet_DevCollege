package com.example.DevCollege.Services.Impl;

import com.example.DevCollege.DTO.StudentDto;
import com.example.DevCollege.Entity.Student;
import com.example.DevCollege.Mapper.StudentMapper;
import com.example.DevCollege.Repository.StudentRepository;
import com.example.DevCollege.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;


    @Autowired
    StudentMapper studentMapper;

    private String generateStudentId(){
        Student student = studentRepository.findTopByOrderByStudentIdDesc();

        if(student==null){
            return "STD0001";
        }
        String studentId= student.getStudentId();
        int numericId = Integer.parseInt(studentId.substring(3));
        return String.format("STD%04d",numericId+1);

    }
    @Override
    public ResponseEntity<?> addStudentDetail(StudentDto studentDto) {

        Student student = studentMapper.studentDtoToStudent(studentDto);
        String studentID = generateStudentId();

        student.setStudentId(studentID);

        studentRepository.save(student);

        return ResponseEntity.status(HttpStatus.OK)
                .body("Successfully Added Student details for "+studentID);


    }
}
