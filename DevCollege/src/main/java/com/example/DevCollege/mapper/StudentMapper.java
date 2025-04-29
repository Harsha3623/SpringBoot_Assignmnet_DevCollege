package com.example.DevCollege.mapper;

import com.example.DevCollege.dto.StudentDto;
import com.example.DevCollege.entity.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentDto studentToStudentDto(Student student);

    Student studentDtoToStudent(StudentDto studentDto);
}
