package com.sistemaifes.sistemaifes.dto.request;

import com.sistemaifes.sistemaifes.model.Coordination;
import com.sistemaifes.sistemaifes.util.UserRole;

public record TeacherRequestDTO( 
    String name,
    String teacherCode,
    String educationLevel,
    boolean situation,
    boolean coordinator,
    UserRole role,
    Coordination coordination
) {
    
}
