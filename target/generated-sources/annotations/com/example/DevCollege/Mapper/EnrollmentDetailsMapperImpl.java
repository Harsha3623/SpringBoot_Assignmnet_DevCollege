package com.example.DevCollege.mapper;

import com.example.DevCollege.dto.EnrollmentDetailsDto;
import com.example.DevCollege.entity.Course;
import com.example.DevCollege.entity.Enrollment;
import com.example.DevCollege.entity.Student;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-30T12:38:48+0530",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class EnrollmentDetailsMapperImpl implements EnrollmentDetailsMapper {

    @Override
    public EnrollmentDetailsDto enrollmentToEnrollmentDetailsDto(Enrollment enrollment) {
        if ( enrollment == null ) {
            return null;
        }

        EnrollmentDetailsDto enrollmentDetailsDto = new EnrollmentDetailsDto();

        enrollmentDetailsDto.setStudentId( enrollmentStudentStudentId( enrollment ) );
        enrollmentDetailsDto.setStudentName( enrollmentStudentName( enrollment ) );
        enrollmentDetailsDto.setCourseId( enrollmentCourseCourseId( enrollment ) );
        enrollmentDetailsDto.setCourseName( enrollmentCourseName( enrollment ) );
        enrollmentDetailsDto.setEnrollId( enrollment.getEnrollId() );
        enrollmentDetailsDto.setCourseStartDatetime( enrollment.getCourseStartDatetime() );
        enrollmentDetailsDto.setCourseEndDatetime( enrollment.getCourseEndDatetime() );
        enrollmentDetailsDto.setStatus( enrollment.getStatus() );

        return enrollmentDetailsDto;
    }

    @Override
    public Enrollment enrollmentDeatilsDtoToEnrollment(EnrollmentDetailsDto enrollmentDetailsDto) {
        if ( enrollmentDetailsDto == null ) {
            return null;
        }

        Enrollment enrollment = new Enrollment();

        enrollment.setEnrollId( enrollmentDetailsDto.getEnrollId() );
        enrollment.setCourseStartDatetime( enrollmentDetailsDto.getCourseStartDatetime() );
        enrollment.setCourseEndDatetime( enrollmentDetailsDto.getCourseEndDatetime() );
        enrollment.setStatus( enrollmentDetailsDto.getStatus() );

        return enrollment;
    }

    private String enrollmentStudentStudentId(Enrollment enrollment) {
        if ( enrollment == null ) {
            return null;
        }
        Student student = enrollment.getStudent();
        if ( student == null ) {
            return null;
        }
        String studentId = student.getStudentId();
        if ( studentId == null ) {
            return null;
        }
        return studentId;
    }

    private String enrollmentStudentName(Enrollment enrollment) {
        if ( enrollment == null ) {
            return null;
        }
        Student student = enrollment.getStudent();
        if ( student == null ) {
            return null;
        }
        String name = student.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String enrollmentCourseCourseId(Enrollment enrollment) {
        if ( enrollment == null ) {
            return null;
        }
        Course course = enrollment.getCourse();
        if ( course == null ) {
            return null;
        }
        String courseId = course.getCourseId();
        if ( courseId == null ) {
            return null;
        }
        return courseId;
    }

    private String enrollmentCourseName(Enrollment enrollment) {
        if ( enrollment == null ) {
            return null;
        }
        Course course = enrollment.getCourse();
        if ( course == null ) {
            return null;
        }
        String name = course.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
