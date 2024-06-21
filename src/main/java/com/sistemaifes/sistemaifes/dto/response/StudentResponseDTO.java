package com.sistemaifes.sistemaifes.dto.response;

import com.sistemaifes.sistemaifes.dto.response.StudentResponseDTO;
import com.sistemaifes.sistemaifes.model.Course;
import com.sistemaifes.sistemaifes.model.Student;
import com.sistemaifes.sistemaifes.util.UserRole;

public record StudentResponseDTO(
    Long _id,
    String name,
    UserRole role,
    String studentCode,
    String registrationYear,
    Course course
) {
    public StudentResponseDTO(Student student){
        this(student.get_id(),
            student.getName(),
            student.getRole(),
            student.getStudentCode(),
            student.getRegistrationYear(),
            student.getCourse()
        );
    }
} 