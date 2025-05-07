package com.example.DevCollege.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;


public class EnrollmentAddDto {



    @NotBlank(message = "Student is is required.")
    private String studentId;



    @NotBlank(message = "CourseId is required.")
    private String courseId;



    //not null for both integer and datetime
    @NotNull(message = "Course start date time is required.")
    //format the user input to this format
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime courseStartDatetime;



    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public LocalDateTime getCourseStartDatetime() {
        return courseStartDatetime;
    }

    public void setCourseStartDatetime(LocalDateTime courseStartDatetime) {
        this.courseStartDatetime = courseStartDatetime;
    }

}