package com.example.DevCollege.mapper;

import com.example.DevCollege.dto.StudentWalletAmountDto;
import com.example.DevCollege.entity.Student;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-30T12:38:47+0530",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class StudentWalletAmountMapperImpl implements StudentWalletAmountMapper {

    @Override
    public StudentWalletAmountDto studentToStudentWalletAmountDto(Student student) {
        if ( student == null ) {
            return null;
        }

        StudentWalletAmountDto studentWalletAmountDto = new StudentWalletAmountDto();

        studentWalletAmountDto.setStudentId( student.getStudentId() );
        studentWalletAmountDto.setWalletAmount( student.getWalletAmount() );

        return studentWalletAmountDto;
    }

    @Override
    public Student studentWalletAmountDtoToStudent(StudentWalletAmountDto studentWalletAmountDto) {
        if ( studentWalletAmountDto == null ) {
            return null;
        }

        Student student = new Student();

        student.setStudentId( studentWalletAmountDto.getStudentId() );
        student.setWalletAmount( studentWalletAmountDto.getWalletAmount() );

        return student;
    }
}
