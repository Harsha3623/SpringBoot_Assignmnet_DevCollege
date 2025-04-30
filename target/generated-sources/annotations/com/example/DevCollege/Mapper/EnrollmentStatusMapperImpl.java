package com.example.DevCollege.mapper;

import com.example.DevCollege.dto.EnrollmentStatus;
import com.example.DevCollege.entity.Enrollment;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-30T12:38:47+0530",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class EnrollmentStatusMapperImpl implements EnrollmentStatusMapper {

    @Override
    public EnrollmentStatus enrollmentToEnrolmentStatus(Enrollment enrollment) {
        if ( enrollment == null ) {
            return null;
        }

        EnrollmentStatus enrollmentStatus = new EnrollmentStatus();

        enrollmentStatus.setStatus( enrollment.getStatus() );

        return enrollmentStatus;
    }

    @Override
    public Enrollment enrollmentStatusToEnrollment(EnrollmentStatus enrollmentStatus) {
        if ( enrollmentStatus == null ) {
            return null;
        }

        Enrollment enrollment = new Enrollment();

        enrollment.setStatus( enrollmentStatus.getStatus() );

        return enrollment;
    }
}
