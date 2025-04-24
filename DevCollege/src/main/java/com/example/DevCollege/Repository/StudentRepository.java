package com.example.DevCollege.Repository;

import com.example.DevCollege.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,String> {

    Student findTopByOrderByStudentIdDesc();

}
