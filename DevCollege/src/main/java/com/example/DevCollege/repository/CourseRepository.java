package com.example.DevCollege.repository;

import com.example.DevCollege.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course,String> {

    Course findTopByOrderByCourseIdDesc();

}
