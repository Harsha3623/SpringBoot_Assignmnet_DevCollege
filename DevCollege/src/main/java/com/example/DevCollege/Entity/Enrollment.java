package com.example.DevCollege.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity

public class Enrollment {

    @Id
    private String enrollId;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "studentId", nullable = false)
    private Student student;
    //private String studentId;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "courseId", nullable = false)
    private Course course;
    //private String courseId;

    private LocalDateTime courseStartDatetime;

    private LocalDateTime courseEndDatetime;


    private String status;


    public String getEnrollId() {
        return enrollId;
    }

    public void setEnrollId(String enrollId) {
        this.enrollId = enrollId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
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

    public Enrollment(String enrollId, Student student, Course course, LocalDateTime courseStartDatetime, LocalDateTime courseEndDatetime, String status) {
        this.enrollId = enrollId;
        this.student = student;
        this.course = course;
        this.courseStartDatetime = courseStartDatetime;
        this.courseEndDatetime = courseEndDatetime;
        this.status = status;
    }

    public Enrollment() {
    }
}
