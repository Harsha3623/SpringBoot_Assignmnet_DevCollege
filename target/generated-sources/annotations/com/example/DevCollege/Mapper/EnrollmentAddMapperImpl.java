package com.example.DevCollege.mapper;

import com.example.DevCollege.dto.EnrollmentAddDto;
import com.example.DevCollege.entity.Enrollment;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-05T23:05:59+0530",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class EnrollmentAddMapperImpl implements EnrollmentAddMapper {

    @Override
    public EnrollmentAddDto enrollmentToEnrollmentAddDto(Enrollment enrollment) {
        if ( enrollment == null ) {
            return null;
        }

        EnrollmentAddDto enrollmentAddDto = new EnrollmentAddDto();

        enrollmentAddDto.setCourseStartDatetime( enrollment.getCourseStartDatetime() );

        return enrollmentAddDto;
    }

    @Override
    public Enrollment enrollmentAddDtoToEnrollment(EnrollmentAddDto enrollmentAddDto) {
        if ( enrollmentAddDto == null ) {
            return null;
        }

        Enrollment enrollment = new Enrollment();

        enrollment.setCourseStartDatetime( enrollmentAddDto.getCourseStartDatetime() );

        return enrollment;
    }
}
