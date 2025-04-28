package com.example.DevCollege.Mapper;

import com.example.DevCollege.DTO.EnrollmentDetailsDto;
import com.example.DevCollege.Entity.Enrollment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EnrollmentDetailsMapper {

    @Mapping(source = "student.studentId", target = "studentId")
    @Mapping(source = "student.name", target = "studentName")
    @Mapping(source = "course.courseId",target = "courseId")
    @Mapping(source = "course.name",target = "courseName")
    EnrollmentDetailsDto enrollmentToEnrollmentDetailsDto(Enrollment enrollment);


    Enrollment enrollmentDeatilsDtoToEnrollment(EnrollmentDetailsDto enrollmentDetailsDto);
}
