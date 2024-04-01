package com.sistemaifes.sistemaifes.dto.response;

import com.sistemaifes.sistemaifes.dto.response.StudentResponseDTO;
import com.sistemaifes.sistemaifes.model.Student;

public record StudentResponseDTO(
    Long _id,
    String name,
    String studentCode   
) {
    public StudentResponseDTO(Student student){
        this(student.get_id(),
            student.getName(),
            student.getStudentCode()
        );
    }
} 