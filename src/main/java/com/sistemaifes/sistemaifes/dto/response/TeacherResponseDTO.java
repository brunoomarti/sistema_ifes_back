package com.sistemaifes.sistemaifes.dto.response;

import com.sistemaifes.sistemaifes.model.Teacher;

public record TeacherResponseDTO(
    Long _id,
    String name,
    String teacherCode,
    String specialty,
    String academic_degree,
    boolean situation
) {
    public TeacherResponseDTO(Teacher teacher){
        this(
            teacher.get_id(),
            teacher.getName(),
            teacher.getTeacherCode(),
            teacher.getSpecialty(),
            teacher.getAcademic_degree(),
            teacher.isSituation()
        );
    }
 
}
