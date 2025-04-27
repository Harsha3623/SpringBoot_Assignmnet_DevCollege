package com.example.DevCollege.Services.Impl;

import com.example.DevCollege.DTO.StudentDto;
import com.example.DevCollege.DTO.StudentWalletAmountDto;
import com.example.DevCollege.Entity.Enrollment;
import com.example.DevCollege.Entity.Student;
import com.example.DevCollege.Mapper.StudentMapper;
import com.example.DevCollege.Mapper.StudentWalletAmountMapper;
import com.example.DevCollege.Repository.EnrollmentRepository;
import com.example.DevCollege.Repository.StudentRepository;
import com.example.DevCollege.Services.StudentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {



    //qualifications
    private List<String> allowedQualification = List.of(
            "BE", "BTECH", "DIPLOMA", "ME", "MTECH", "MPHIL", "MS",
            "BBA", "BCOM", "BSC", "MSC", "BCA", "MCA", "LLB", "MBBS", "MBA"
    );

    @Autowired
    StudentRepository studentRepository;



    @Autowired
    StudentMapper studentMapper;



    @Autowired
    EnrollmentRepository enrollmentRepository;


    //wallet amount mapper
    @Autowired
    StudentWalletAmountMapper studentWalletAmountMapper;



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
    @Transactional
    public ResponseEntity<?> addStudentDetail(StudentDto studentDto) {

        Student student = studentMapper.studentDtoToStudent(studentDto);

        String studentID = generateStudentId();

        String[] qualifications  = studentDto.getHighestQualification().split(",");

        for(String s: qualifications){
            if(!allowedQualification.contains(s.toUpperCase())){
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                        .body("Student Qualification is incorrect.");
            }
        }

        student.setStudentId(studentID);

        studentRepository.save(student);

        return ResponseEntity.status(HttpStatus.OK)
                .body("Successfully Added Student details for "+studentID);


    }

    @Override
    @Transactional
    public ResponseEntity<?> updateStudentDetail(String stdId, StudentDto studentDto) {

        //find student by id
        Student student = studentRepository.findById(stdId).orElse(null);

        if(student==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Student id: "+stdId+" is not present.");
        }

        //updating the student details
        student.setName(studentDto.getName());
        student.setHighestQualification(studentDto.getHighestQualification());
        student.setContactNo(studentDto.getContactNo());
        /*

        for updating the name qualification and contact number
        //wallet amount should be added
        student.setWalletAmount(studentDto.getWalletAmount());

         */
        //save the updated student detail back to db
        studentRepository.save(student);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body("Successfully updated student details for id: "+stdId);

    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteStudentDetail(String stdId) {

        Student student = studentRepository.findById(stdId).orElse(null);

        //if it is not present
        if(student==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Student id: "+stdId+" doesn't exist.");
        }

        //get all the student enrollment details
        List<Enrollment> studentEnrollment = enrollmentRepository.findByStudent_StudentId(stdId);

        //if it is empty then student is not assigned to any course
        if(studentEnrollment.isEmpty()){
            studentRepository.deleteById(stdId);
            return ResponseEntity.status((HttpStatus.ACCEPTED))
                    .body("Successfully deleted Student details for id:"+stdId);
        }
        studentEnrollment.stream()
                .map(e->{
                    e.setStudent(null);
                    e.setStatus("Cancelled");
                    return e;
                })
                .collect(Collectors.toList());

        enrollmentRepository.saveAll(studentEnrollment);


        studentRepository.deleteById(stdId);

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body("Successfully deleted Student details with "+stdId+
                        " And amount <50% of all the enrolled course fee>" +
                        " will be refunded in original payment method within 24 hours.");


    }


    @Override
    public ResponseEntity<?> getStudentDetail(String stdId) {


        Student student = studentRepository.findById(stdId).orElse(null);
        if(student==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Student id: "+stdId+" is not present");
        }
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(studentMapper.studentToStudentDto(student));

    }

    @Override
    public ResponseEntity<?> getAllStudentDetail() {

        List<Student> students = studentRepository.findAll();

        if(students.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("No data found.");
        }

        return ResponseEntity.status(HttpStatus.FOUND)
                .body(students.stream()
                        .map(s->studentMapper.studentToStudentDto(s))
                        .collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<?> addWalletAmount(String stdId, StudentWalletAmountDto studentWalletAmountDto) {

        Student student = studentRepository.findById(stdId).orElse(null);

        if(student==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Student id: "+stdId+" doesn't found");
        }

        //saving the wallet amount and by adding the wallet amount
        student.setWalletAmount(student.getWalletAmount()+studentWalletAmountDto.getWalletAmount());

        studentRepository.save(student);


        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body("Successfully added Amount for student id: "+stdId+
                        " and available balance is "+student.getWalletAmount());

    }

    @Override
    public ResponseEntity<?> getWalletDetail(String stdId) {

        Student student = studentRepository.findById(stdId).orElse(null);

        if(student==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Student id: "+stdId+" is not found.");
        }

        return ResponseEntity.status(HttpStatus.FOUND)
                .body(studentWalletAmountMapper.studentToStudentWalletAmountDto(student));

    }


}
