package com.example.DevCollege.mapper;

import com.example.DevCollege.dto.StudentAddUpdateDTO;
import com.example.DevCollege.entity.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentAddUpdateMapper {

    Student studentAddUpdateDTOToStudent(StudentAddUpdateDTO studentAddUpdateDTO);

    StudentAddUpdateDTO studentToStudentAddUpdateDTO(Student student);
}
