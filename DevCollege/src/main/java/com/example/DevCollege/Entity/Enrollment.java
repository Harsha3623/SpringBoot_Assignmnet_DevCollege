package com.example.DevCollege.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


import java.time.LocalDate;

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

    private LocalDate start;

    private LocalDate end;

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

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Enrollment(String enrollId, Student student, Course course, LocalDate start, LocalDate end, String status) {
        this.enrollId = enrollId;
        this.student = student;
        this.course = course;
        this.start = start;
        this.end = end;
        this.status = status;
    }

    public Enrollment() {
    }
}
