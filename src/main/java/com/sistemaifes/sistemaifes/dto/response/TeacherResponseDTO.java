package com.sistemaifes.sistemaifes.dto.response;

import com.sistemaifes.sistemaifes.model.Coordination;
import com.sistemaifes.sistemaifes.model.Teacher;
import com.sistemaifes.sistemaifes.util.UserRole;

public record TeacherResponseDTO( 
    Long _id,
    String name,
    UserRole role,
    String teacherCode,
    String educationLevel,
    boolean situation,
    boolean coordinator,
    Coordination coordination
) {
    public TeacherResponseDTO(Teacher teacher){
        this( 
            teacher.get_id(),
            teacher.getName(),
            teacher.getRole(),
            teacher.getTeacherCode(),
            teacher.getEducationLevel(),
            teacher.isSituation(),
            teacher.isCoordinator(),
            teacher.getCoordination()
        );
    }
 
}
