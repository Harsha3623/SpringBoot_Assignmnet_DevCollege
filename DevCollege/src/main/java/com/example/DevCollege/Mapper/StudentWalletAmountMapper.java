package com.example.DevCollege.Mapper;

import com.example.DevCollege.DTO.StudentWalletAmountDto;
import com.example.DevCollege.Entity.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentWalletAmountMapper {

    StudentWalletAmountDto studentToStudentWalletAmountDto(Student student);

    Student studentWalletAmountDtoToStudent(StudentWalletAmountDto studentWalletAmountDto);


}
