package com.example.DevCollege.mapper;

import com.example.DevCollege.dto.StudentWalletAmountDto;
import com.example.DevCollege.entity.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentWalletAmountMapper {

    StudentWalletAmountDto studentToStudentWalletAmountDto(Student student);

    Student studentWalletAmountDtoToStudent(StudentWalletAmountDto studentWalletAmountDto);


}
