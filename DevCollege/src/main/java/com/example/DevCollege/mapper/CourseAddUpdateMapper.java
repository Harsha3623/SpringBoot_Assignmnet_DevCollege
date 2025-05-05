package com.example.DevCollege.mapper;

import com.example.DevCollege.dto.CourseAddUpdateDTO;
import com.example.DevCollege.entity.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseAddUpdateMapper {

    Course courseAddUpdateDtoToCourse(CourseAddUpdateDTO courseAddUpdateDTO);

    CourseAddUpdateDTO courseToCourseAddUpdateDto(Course course);
}
