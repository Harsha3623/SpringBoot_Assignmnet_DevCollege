package com.example.DevCollege.Mapper;

import com.example.DevCollege.DTO.CourseDto;
import com.example.DevCollege.Entity.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseDto courseToCourseDto(Course course);
    Course courseDtoToCourse(CourseDto courseDto);
}
