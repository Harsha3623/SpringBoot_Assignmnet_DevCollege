package com.example.DevCollege.Repository;

import com.example.DevCollege.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course,String> {

    Course findTopByOrderByCourseIdDesc();

}
