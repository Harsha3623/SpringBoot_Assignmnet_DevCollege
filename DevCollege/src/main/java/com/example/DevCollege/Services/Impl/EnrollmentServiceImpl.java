package com.example.DevCollege.Services.Impl;

import com.example.DevCollege.DTO.EnrollmentAddDto;
import com.example.DevCollege.DTO.EnrollmentDetailsDto;
import com.example.DevCollege.Entity.Course;
import com.example.DevCollege.Entity.Enrollment;
import com.example.DevCollege.Entity.Student;
import com.example.DevCollege.Mapper.EnrollmentAddMapper;
import com.example.DevCollege.Mapper.EnrollmentDetailsMapper;
import com.example.DevCollege.Repository.CourseRepository;
import com.example.DevCollege.Repository.EnrollmentRepository;
import com.example.DevCollege.Repository.StudentRepository;
import com.example.DevCollege.Services.EnrollmentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.xml.stream.events.EndElement;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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


    @Autowired
    private EnrollmentDetailsMapper enrollmentDetailsMapper;


    private String getEnrollmentID(){
        Enrollment enrollment = enrollmentRepository.findTopByOrderByEnrollIdDesc();
        if(enrollment==null){
            return "EN0001";
        }
        int lastId = Integer.parseInt((enrollment.getEnrollId()).substring(2));

        return String.format("EN%04d",lastId+1);

    }




    @Override
    @Transactional
    public ResponseEntity<?> addEnrollmentForCourse(EnrollmentAddDto enrollmentAddDto) {
        Student student = studentRepository.findById(enrollmentAddDto.getStudentId()).orElse(null);

        Course course = courseRepository.findById(enrollmentAddDto.getCourseId()).orElse(null);

        if(student==null || course==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Either student id or course id is not presnet.");
        }



        Enrollment enrollment = enrollmentAddMapper.enrollmentAddDtoToEnrollment(enrollmentAddDto);

//        //adding the course duration to the course end date time
//        enrollment.setCourseEndDatetime(enrollment.getCourseStartDatetime()
//                        .plusMinutes(course.getDuration()));

        //if student have less amount then course fee --> don't allow
        if(course.getFee()>student.getWalletAmount()){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .body("Student wallet amount is less then course fee.");
        }

        List<Enrollment> studentEnrollments = enrollmentRepository.findByStudent_StudentId(enrollmentAddDto.getStudentId());

       //validate the course lies between any course range
        //enrollment.setCourseEndDatetime(enrollment.getCourseStartDatetime().plusMinutes(course.getDuration()));


        boolean isValid = studentEnrollments.stream()
                .noneMatch(e->
                {
                     return !enrollment.getCourseStartDatetime().isBefore(e.getCourseStartDatetime())
                            && !enrollment.getCourseStartDatetime().isAfter(e.getCourseEndDatetime());
                });

        if(!isValid){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Student have already enrolled for a course");
        }

        //validate whether the student is already enrolled for that course.
        for(Enrollment e: studentEnrollments){
            if((e.getStudent().getStudentId()).equals(enrollmentAddDto.getStudentId())){
                if((e.getCourse().getCourseId()).equals(enrollmentAddDto.getCourseId())){
                    return ResponseEntity.status(HttpStatus.CONFLICT)
                            .body("Student "+e.getStudent().getName()+" already enrolled for " +
                                    "course "+e.getCourse().getName());
                }
            }
        }

        //check the slots
        if(course.getNoOfSlot()==0){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .body("The Course Slots are already filled");
        }

        //after verifying all the validations then update course student and enrollment details
        course.setNoOfSlot(course.getNoOfSlot()-1);

        //update course end time
        enrollment.setCourseEndDatetime(enrollment.getCourseStartDatetime().plusMinutes(course.getDuration()));

        courseRepository.save(course);


        //deduct the course fee from student wallet amount
        student.setWalletAmount(student.getWalletAmount()-course.getFee());

        //set enrollid
        enrollment.setEnrollId(getEnrollmentID());


        //course status will be allocated
        enrollment.setStatus("Allocated");
        enrollment.setCourse(course);
        enrollment.setStudent(student);

        //save the student and enrollment entity

        studentRepository.save(student);

        enrollmentRepository.save(enrollment);

        return ResponseEntity.status(HttpStatus.OK)
                .body("Successfully Enrolled for "+student.getName()+
                        " in course "+course.getName()+" for enrollment id:"+enrollment.getEnrollId());


    }

    @Override
    public ResponseEntity<?> getEnrollmentDetailsUsingID(String id) {
        EnrollmentDetailsDto enrollmentDetailsDto  = enrollmentDetailsMapper
                .enrollmentToEnrollmentDetailsDto(enrollmentRepository.findById(id).orElse(null));


        if(enrollmentDetailsDto==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Enrollment id: "+id+" Not exists.");
        }

        //setting the student link
        enrollmentDetailsDto.setStudentLink("/student/get/"+enrollmentDetailsDto.getStudentId());

        //setting the course Link
        enrollmentDetailsDto.setCourseLink("/course/get/"+ enrollmentDetailsDto.getCourseId());


        return ResponseEntity.status(HttpStatus.OK)
                .body(enrollmentDetailsDto);
    }

    @Override
    public ResponseEntity<?> getAllEnrollmentDetails() {

        List<Enrollment> enrollments = enrollmentRepository.findAll();

        if(enrollments.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No data found.");
        }

        //converting enrollment to enrollmentDetailsDto
       List<EnrollmentDetailsDto> enrollmentDetailsDtoList = enrollments.stream()
               .map(e->enrollmentDetailsMapper.enrollmentToEnrollmentDetailsDto(e))
               .collect(Collectors.toList());

        //set course and student link
       enrollmentDetailsDtoList.stream()
               .map(e->
               {
                   e.setStudentLink("/student/get/"+e.getStudentId());
                   e.setCourseLink("/course/get/"+e.getCourseId());
                   return e;
               }).collect(Collectors.toList());

       return ResponseEntity.status(HttpStatus.FOUND)
               .body(enrollmentDetailsDtoList);
    }

    @Override
    public ResponseEntity<?> getEnrollmentDetailsForStudent(String stdId) {


        Student student = studentRepository.findById(stdId).orElse(null);

        if(student==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Student id: "+stdId+" is not present in the table.");
        }

        List<Enrollment> enrollments = enrollmentRepository.findByStudent_StudentId(stdId);

        if(enrollments.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(student.getName()+" doesn't enroll for any course.");
        }

        List<EnrollmentDetailsDto> enrollmentDetailsDtoList = enrollments.stream()
                .map(e->
                  enrollmentDetailsMapper.enrollmentToEnrollmentDetailsDto(e)
                  ).collect(Collectors.toList());

        enrollmentDetailsDtoList = enrollmentDetailsDtoList.stream()
                .map(e->{
                    e.setStudentLink("/student/get/"+e.getStudentId());
                    e.setCourseLink("/course/get/"+e.getCourseId());
                    return e;
                }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.FOUND)
                .body(enrollmentDetailsDtoList);

    }

    @Override
    @Transactional
    //todo
    public ResponseEntity<?> changeStatus(String enrollId) {

        Enrollment enrollment = enrollmentRepository.findById(enrollId).orElse(null);

        if(enrollment == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Enrollment id: "+enrollId+" is not present.");
        }


        LocalDateTime end = enrollment.getCourseEndDatetime().minusDays(2);
        return null;
    }


}
