package com.example.DevCollege.Mapper;

import com.example.DevCollege.DTO.StudentDto;
import com.example.DevCollege.Entity.Student;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-25T14:53:07+0530",
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
