package com.example.DevCollege.mapper;

import com.example.DevCollege.dto.EnrollmentAddDto;
import com.example.DevCollege.entity.Enrollment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnrollmentAddMapper {

    EnrollmentAddDto enrollmentToEnrollmentAddDto(Enrollment enrollment);

    Enrollment enrollmentAddDtoToEnrollment(EnrollmentAddDto enrollmentAddDto);
}
