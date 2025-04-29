package com.example.DevCollege.repository;

import com.example.DevCollege.entity.Course;
import com.example.DevCollege.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment,String> {


    List<Enrollment> findByCourse_CourseId(String courseId);

    List<Enrollment> findByStudent_StudentId(String studentId);

    Enrollment findTopByOrderByEnrollIdDesc();

    List<Enrollment> findByCourse_Tag(String tag);


}
