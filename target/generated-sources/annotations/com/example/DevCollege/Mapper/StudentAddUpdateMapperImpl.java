package com.example.DevCollege.mapper;

import com.example.DevCollege.dto.StudentAddUpdateDTO;
import com.example.DevCollege.entity.Student;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-05T23:05:59+0530",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class StudentAddUpdateMapperImpl implements StudentAddUpdateMapper {

    @Override
    public Student studentAddUpdateDTOToStudent(StudentAddUpdateDTO studentAddUpdateDTO) {
        if ( studentAddUpdateDTO == null ) {
            return null;
        }

        Student student = new Student();

        student.setName( studentAddUpdateDTO.getName() );
        student.setHighestQualification( studentAddUpdateDTO.getHighestQualification() );
        student.setContactNo( studentAddUpdateDTO.getContactNo() );
        student.setWalletAmount( studentAddUpdateDTO.getWalletAmount() );

        return student;
    }

    @Override
    public StudentAddUpdateDTO studentToStudentAddUpdateDTO(Student student) {
        if ( student == null ) {
            return null;
        }

        StudentAddUpdateDTO studentAddUpdateDTO = new StudentAddUpdateDTO();

        studentAddUpdateDTO.setName( student.getName() );
        studentAddUpdateDTO.setHighestQualification( student.getHighestQualification() );
        studentAddUpdateDTO.setContactNo( student.getContactNo() );
        studentAddUpdateDTO.setWalletAmount( student.getWalletAmount() );

        return studentAddUpdateDTO;
    }
}
