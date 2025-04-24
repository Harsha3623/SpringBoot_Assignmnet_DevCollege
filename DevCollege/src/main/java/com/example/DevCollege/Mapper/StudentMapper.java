package com.example.DevCollege.Mapper;

import com.example.DevCollege.DTO.StudentDto;
import com.example.DevCollege.Entity.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentDto studentToStudentDto(Student student);

    Student studentDtoToStudent(StudentDto studentDto);
}
