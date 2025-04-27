package com.example.DevCollege.Mapper;

import com.example.DevCollege.DTO.EnrollmentAddDto;
import com.example.DevCollege.Entity.Enrollment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnrollmentAddMapper {

    EnrollmentAddDto enrollmentToEnrollmentAddDto(Enrollment enrollment);

    Enrollment enrollmentAddDtoToEnrollment(EnrollmentAddDto enrollmentAddDto);
}
