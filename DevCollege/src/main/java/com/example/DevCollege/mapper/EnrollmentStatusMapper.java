package com.example.DevCollege.mapper;

import com.example.DevCollege.dto.EnrollmentStatus;
import com.example.DevCollege.entity.Enrollment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnrollmentStatusMapper {

    EnrollmentStatus enrollmentToEnrolmentStatus(Enrollment enrollment);

    Enrollment enrollmentStatusToEnrollment(EnrollmentStatus enrollmentStatus);


}
