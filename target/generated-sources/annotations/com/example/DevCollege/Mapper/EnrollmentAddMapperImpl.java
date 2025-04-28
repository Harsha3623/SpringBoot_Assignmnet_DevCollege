package com.example.DevCollege.Mapper;

import com.example.DevCollege.DTO.EnrollmentAddDto;
import com.example.DevCollege.Entity.Enrollment;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-28T15:56:01+0530",
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
