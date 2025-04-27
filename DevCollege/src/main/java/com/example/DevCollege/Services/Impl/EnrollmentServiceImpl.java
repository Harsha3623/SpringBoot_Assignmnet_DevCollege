package com.example.DevCollege.Services.Impl;

import com.example.DevCollege.DTO.EnrollmentAddDto;
import com.example.DevCollege.Entity.Course;
import com.example.DevCollege.Entity.Enrollment;
import com.example.DevCollege.Entity.Student;
import com.example.DevCollege.Mapper.EnrollmentAddMapper;
import com.example.DevCollege.Repository.CourseRepository;
import com.example.DevCollege.Repository.EnrollmentRepository;
import com.example.DevCollege.Repository.StudentRepository;
import com.example.DevCollege.Services.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.xml.stream.events.EndElement;
import java.util.List;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {


    @Autowired
    private EnrollmentRepository enrollmentRepository;


    @Autowired
    private EnrollmentAddMapper enrollmentAddMapper;


    @Autowired
    private StudentRepository studentRepository;


    @Autowired
    private CourseRepository courseRepository;



    @Override
    public ResponseEntity<?> addEnrollmentForCourse(EnrollmentAddDto enrollmentAddDto) {
        Student student = studentRepository.findById(enrollmentAddDto.getStudentId()).orElse(null);

        Course course = courseRepository.findById(enrollmentAddDto.getCourseId()).orElse(null);

        if(student==null || course==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Either student id or course id is not presnet.");
        }


        Enrollment enrollment = enrollmentAddMapper.enrollmentAddDtoToEnrollment(enrollmentAddDto);

        //adding the course duration to the course end date time
        enrollment.setCourseEndDatetime(enrollment.getCourseStartDatetime()
                        .plusMinutes(course.getDuration()));

        //if student have less amount then course fee --> dont allow
        if(course.getFee()>student.getWalletAmount()){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .body("Student wallet amount is less then course fee.");
        }

        List<Enrollment> studentEnrollments = enrollmentRepository.findByStudent_StudentId(enrollmentAddDto.getStudentId());

       //validate the course lies between any couse range
        //deduct the course fee from student wallet amount
        student.setWalletAmount(student.getWalletAmount()-course.getFee());

        //course status will be allocated
        enrollment.setStatus("Allocated");



    }
}
