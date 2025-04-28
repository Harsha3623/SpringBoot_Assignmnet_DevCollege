package com.example.DevCollege.Mapper;

import com.example.DevCollege.DTO.CourseDto;
import com.example.DevCollege.Entity.Course;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-28T15:56:01+0530",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class CourseMapperImpl implements CourseMapper {

    @Override
    public CourseDto courseToCourseDto(Course course) {
        if ( course == null ) {
            return null;
        }

        CourseDto courseDto = new CourseDto();

        courseDto.setName( course.getName() );
        courseDto.setDescription( course.getDescription() );
        courseDto.setNoOfSlot( course.getNoOfSlot() );
        courseDto.setFee( course.getFee() );
        courseDto.setDuration( course.getDuration() );
        courseDto.setTag( course.getTag() );
        courseDto.setCourseId( course.getCourseId() );

        return courseDto;
    }

    @Override
    public Course courseDtoToCourse(CourseDto courseDto) {
        if ( courseDto == null ) {
            return null;
        }

        Course course = new Course();

        course.setCourseId( courseDto.getCourseId() );
        course.setName( courseDto.getName() );
        course.setDescription( courseDto.getDescription() );
        course.setNoOfSlot( courseDto.getNoOfSlot() );
        course.setFee( courseDto.getFee() );
        course.setDuration( courseDto.getDuration() );
        course.setTag( courseDto.getTag() );

        return course;
    }
}
