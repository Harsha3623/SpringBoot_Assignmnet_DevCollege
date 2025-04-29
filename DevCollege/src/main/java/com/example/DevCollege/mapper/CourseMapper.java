package com.example.DevCollege.mapper;

import com.example.DevCollege.dto.CourseDto;
import com.example.DevCollege.entity.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseDto courseToCourseDto(Course course);
    Course courseDtoToCourse(CourseDto courseDto);
}
