package com.example.DevCollege.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;


public class EnrollmentAddDto {

    @NotBlank(message = "Student is is required.")
    private String studentId;

    @NotBlank(message = "CourseId is required.")
    private String courseId;

    //not null for both integer and datetime
    @NotNull(message = "Course start date time is required.")
    private LocalDateTime courseStartDateTime;


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

    public LocalDateTime getCourseStartDateTime() {
        return courseStartDateTime;
    }

    public void setCourseStartDateTime(LocalDateTime courseStartDateTime) {
        this.courseStartDateTime = courseStartDateTime;
    }
}