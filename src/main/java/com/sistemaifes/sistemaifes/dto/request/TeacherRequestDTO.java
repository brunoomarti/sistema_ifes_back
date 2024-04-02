package com.sistemaifes.sistemaifes.dto.request;

import com.sistemaifes.sistemaifes.model.Coordination;

public record TeacherRequestDTO( 
    String name,
    String teacherCode,
    String specialty,
    String educationLevel,
    boolean situation,
    boolean coordinator,
    Coordination coordination
) {
    
}
