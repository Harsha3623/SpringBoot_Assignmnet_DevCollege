package com.example.DevCollege.Repository;

import com.example.DevCollege.Entity.Enrollment;
import com.example.DevCollege.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment,String> {


    List<Enrollment> findByCourse_CourseId(String courseId);

    List<Enrollment> findByStudent_StudentId(String studentId);
}
