package com.sistemaifes.sistemaifes.dto.response;

import com.sistemaifes.sistemaifes.model.Teacher;

public record TeacherResponseDTO( 
    String teacherCode,
    String specialty,
    String educationLevel,
    boolean situation
) {
    public TeacherResponseDTO(Teacher teacher){
        this( 
            teacher.getTeacherCode(),
            teacher.getSpecialty(),
            teacher.getEducationLevel(),
            teacher.isSituation()
        );
    }
 
}
