package com.example.DevCollege.mapper;

import com.example.DevCollege.dto.CourseAddUpdateDTO;
import com.example.DevCollege.entity.Course;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-05T23:05:59+0530",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class CourseAddUpdateMapperImpl implements CourseAddUpdateMapper {

    @Override
    public Course courseAddUpdateDtoToCourse(CourseAddUpdateDTO courseAddUpdateDTO) {
        if ( courseAddUpdateDTO == null ) {
            return null;
        }

        Course course = new Course();

        course.setName( courseAddUpdateDTO.getName() );
        course.setDescription( courseAddUpdateDTO.getDescription() );
        course.setNoOfSlot( courseAddUpdateDTO.getNoOfSlot() );
        course.setFee( courseAddUpdateDTO.getFee() );
        course.setDuration( courseAddUpdateDTO.getDuration() );
        course.setTag( courseAddUpdateDTO.getTag() );

        return course;
    }

    @Override
    public CourseAddUpdateDTO courseToCourseAddUpdateDto(Course course) {
        if ( course == null ) {
            return null;
        }

        CourseAddUpdateDTO courseAddUpdateDTO = new CourseAddUpdateDTO();

        courseAddUpdateDTO.setName( course.getName() );
        courseAddUpdateDTO.setDescription( course.getDescription() );
        courseAddUpdateDTO.setNoOfSlot( course.getNoOfSlot() );
        courseAddUpdateDTO.setFee( course.getFee() );
        courseAddUpdateDTO.setDuration( course.getDuration() );
        courseAddUpdateDTO.setTag( course.getTag() );

        return courseAddUpdateDTO;
    }
}
