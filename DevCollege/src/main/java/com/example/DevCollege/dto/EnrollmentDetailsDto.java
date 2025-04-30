package com.example.DevCollege.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

//getting employee details DTO
public class EnrollmentDetailsDto {

    private String enrollId;


    private String studentId;


    private String studentName;



    private String courseId;



    private String courseName;


    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime courseStartDatetime;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime courseEndDatetime;


    private String status;

    private String studentLink;

    private String courseLink;


    public String getEnrollId() {
        return enrollId;
    }

    public void setEnrollId(String enrollId) {
        this.enrollId = enrollId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public LocalDateTime getCourseStartDatetime() {
        return courseStartDatetime;
    }

    public void setCourseStartDatetime(LocalDateTime courseStartDatetime) {
        this.courseStartDatetime = courseStartDatetime;
    }

    public LocalDateTime getCourseEndDatetime() {
        return courseEndDatetime;
    }

    public void setCourseEndDatetime(LocalDateTime courseEndDatetime) {
        this.courseEndDatetime = courseEndDatetime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStudentLink() {
        return studentLink;
    }

    public void setStudentLink(String studentLink) {
        this.studentLink = studentLink;
    }

    public String getCourseLink() {
        return courseLink;
    }

    public void setCourseLink(String courseLink) {
        this.courseLink = courseLink;
    }
}
