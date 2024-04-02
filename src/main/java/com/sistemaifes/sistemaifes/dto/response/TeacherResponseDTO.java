package com.sistemaifes.sistemaifes.dto.response;

import com.sistemaifes.sistemaifes.model.Coordination;
import com.sistemaifes.sistemaifes.model.Teacher;

public record TeacherResponseDTO( 
    Long _id,
    String name,
    String teacherCode,
    String specialty,
    String educationLevel,
    boolean situation,
    boolean coordinator,
    Coordination coordination
) {
    public TeacherResponseDTO(Teacher teacher){
        this( 
            teacher.get_id(),
            teacher.getName(),
            teacher.getTeacherCode(),
            teacher.getSpecialty(),
            teacher.getEducationLevel(),
            teacher.isSituation(),
            teacher.isCoordinator(),
            teacher.getCoordination()
        );
    }
 
}
