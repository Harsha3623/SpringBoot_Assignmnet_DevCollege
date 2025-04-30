package com.example.DevCollege.mapper;

import com.example.DevCollege.dto.CourseSuggestionDto;
import com.example.DevCollege.entity.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseSuggestionMapper {

    CourseSuggestionDto courseToCourseSuggestionDto(Course course);

    Course courseSuggestionDtoToCourse(CourseSuggestionDto courseSuggestionDto);
}
