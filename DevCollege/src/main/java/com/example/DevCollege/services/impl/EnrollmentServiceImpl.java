package com.example.DevCollege.services.impl;

import com.example.DevCollege.dto.EnrollmentAddDto;
import com.example.DevCollege.dto.EnrollmentDetailsDto;
import com.example.DevCollege.dto.EnrollmentStatus;
import com.example.DevCollege.entity.Course;
import com.example.DevCollege.entity.Enrollment;
import com.example.DevCollege.entity.Student;
import com.example.DevCollege.mapper.EnrollmentAddMapper;
import com.example.DevCollege.mapper.EnrollmentDetailsMapper;
import com.example.DevCollege.mapper.EnrollmentStatusMapper;
import com.example.DevCollege.repository.CourseRepository;
import com.example.DevCollege.repository.EnrollmentRepository;
import com.example.DevCollege.repository.StudentRepository;
import com.example.DevCollege.services.EnrollmentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
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


    @Autowired
    private EnrollmentStatusMapper enrollmentStatusMapper;


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
    public ResponseEntity<?> changeStatus(String enrollId, EnrollmentStatus enrollmentStatus) {

        Enrollment enrollment = enrollmentRepository.findById(enrollId).orElse(null);

        if(enrollment == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Enrollment id: "+enrollId+" is not present.");
        }

        if(enrollment.getStudent()==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Student not found");
        }
        //course link ref
        Course course = enrollment.getCourse();

        //check whether course end date is completed compared to current date time
        if((LocalDateTime.now()).isAfter(enrollment.getCourseEndDatetime())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Course end date is completed on: "+enrollment.getCourseEndDatetime()
                    +" and status cannot be changed");
        }

        String oldStatus = enrollment.getStatus();
        //if status is allocated --> in progress or cancelled
        if(enrollment.getStatus().equals("Allocated")){

            if(enrollmentStatus.getStatus().equals("In Progress")){

                enrollment.setStatus(enrollmentStatus.getStatus());

            }else if(enrollmentStatus.getStatus().equals("Cancelled")){

                //current local date time
                LocalDateTime current = LocalDateTime.now();

                LocalDateTime refundEndDate = enrollment.getCourseEndDatetime();

                //get the course and student details
                Student student = enrollment.getStudent();

                float amount = student.getWalletAmount();

                float refund = course.getFee();

                if(current.isBefore(refundEndDate.minusDays(2))){

                    student.setWalletAmount(amount+refund);

                } else if(current.isBefore(refundEndDate.minusDays(1))){

                    student.setWalletAmount(amount+(refund*((float) 70 /100)));

                }else if(current.isBefore(refundEndDate.minusHours(12))){

                    student.setWalletAmount(amount+(refund*((float) 50 / 100)));

                }
                //after refunding saving the student details
                studentRepository.save(student);

                //setting the status to cancelled
                enrollment.setStatus(enrollmentStatus.getStatus());

                //update the course slots availability as it is cancelled
                course.setNoOfSlot(course.getNoOfSlot()+1);
                courseRepository.save(course);


            }else{
                //if want to change from allocated--> completed throw error
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Enrollment status: "+enrollment.getStatus()+" and cannot be changed to "+enrollmentStatus.getStatus());
            }

        }else if((enrollment.getStatus().equals("In Progress"))&&(enrollmentStatus.getStatus().equals("Completed"))){

            //inprogress --> completed
                enrollment.setStatus(enrollmentStatus.getStatus());

                //update the course number of slots as it is completed

                course.setNoOfSlot(course.getNoOfSlot()+1);
                courseRepository.save(course);

        }else{
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Enrollment status: "+enrollment.getStatus()+" and cannot be changed to "+enrollmentStatus.getStatus());
        }
        enrollmentRepository.save(enrollment);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Successfully change the status from "+oldStatus+" to "+ enrollmentStatus.getStatus()+" for enroll id: "+enrollment.getEnrollId());
    }

    @Override
    public ResponseEntity<?> checkAvailability(String courseId) {

        Course course = courseRepository.findById(courseId).orElse(null);
        if(course==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Course ID :"+courseId+" doesn't exists");
        }


        //check whether the slots are not present
        if(course.getNoOfSlot()==0){
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Course id:"+courseId+", course name: "+course.getName()
                            +" is not available for enrolment");
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body("Course id:"+courseId+", course name: "+course.getName()
                +" available for enrolment with number of slots: "+course.getNoOfSlot());
    }

    @Override
    public ResponseEntity<?> courseSuggestion(String studentId) {
        Student student = studentRepository.findById(studentId).orElse(null);

        if (student == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Student Id: " + studentId + " doesn't exist.");
        }

        String[] qualifications = student.getHighestQualification().toUpperCase().split(",");
        List<Course> suggestions = new ArrayList<>();
        List<Course> courses = courseRepository.findAll();

        for(Course c: courses){
            if(isContains(c.getTag(),qualifications)){
                suggestions.add(c);
            }
        }
        System.out.println(suggestions);

        return ResponseEntity.status(HttpStatus.OK).body(suggestions);
    }

    private boolean isContains(String tag, String[] qualifications) {
        String[] tags = tag.split(",");
        for(String str: tags){
            for(String contain: qualifications){
                if(str.equalsIgnoreCase(contain)){
                    return true;
                }
            }
        }
        return false;
    }


}