package com.example.DevCollege.mapper;

import com.example.DevCollege.dto.StudentDto;
import com.example.DevCollege.entity.Student;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-30T12:38:47+0530",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class StudentMapperImpl implements StudentMapper {

    @Override
    public StudentDto studentToStudentDto(Student student) {
        if ( student == null ) {
            return null;
        }

        StudentDto studentDto = new StudentDto();

        studentDto.setStudentId( student.getStudentId() );
        studentDto.setName( student.getName() );
        studentDto.setHighestQualification( student.getHighestQualification() );
        studentDto.setContactNo( student.getContactNo() );
        studentDto.setWalletAmount( student.getWalletAmount() );

        return studentDto;
    }

    @Override
    public Student studentDtoToStudent(StudentDto studentDto) {
        if ( studentDto == null ) {
            return null;
        }

        Student student = new Student();

        student.setStudentId( studentDto.getStudentId() );
        student.setName( studentDto.getName() );
        student.setHighestQualification( studentDto.getHighestQualification() );
        student.setContactNo( studentDto.getContactNo() );
        student.setWalletAmount( studentDto.getWalletAmount() );

        return student;
    }
}
