package com.example.DevCollege.mapper;

import com.example.DevCollege.dto.CourseSuggestionDto;
import com.example.DevCollege.entity.Course;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-05T23:05:59+0530",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class CourseSuggestionMapperImpl implements CourseSuggestionMapper {

    @Override
    public CourseSuggestionDto courseToCourseSuggestionDto(Course course) {
        if ( course == null ) {
            return null;
        }

        CourseSuggestionDto courseSuggestionDto = new CourseSuggestionDto();

        courseSuggestionDto.setCourseId( course.getCourseId() );
        courseSuggestionDto.setName( course.getName() );
        courseSuggestionDto.setDescription( course.getDescription() );
        courseSuggestionDto.setFee( course.getFee() );
        courseSuggestionDto.setDuration( course.getDuration() );

        return courseSuggestionDto;
    }

    @Override
    public Course courseSuggestionDtoToCourse(CourseSuggestionDto courseSuggestionDto) {
        if ( courseSuggestionDto == null ) {
            return null;
        }

        Course course = new Course();

        course.setCourseId( courseSuggestionDto.getCourseId() );
        course.setName( courseSuggestionDto.getName() );
        course.setDescription( courseSuggestionDto.getDescription() );
        course.setFee( courseSuggestionDto.getFee() );
        course.setDuration( courseSuggestionDto.getDuration() );

        return course;
    }
}
