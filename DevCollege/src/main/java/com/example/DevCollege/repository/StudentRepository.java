package com.example.DevCollege.repository;

import com.example.DevCollege.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,String> {

    Student findTopByOrderByStudentIdDesc();

}
