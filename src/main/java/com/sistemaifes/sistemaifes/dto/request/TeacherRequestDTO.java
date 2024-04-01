package com.sistemaifes.sistemaifes.dto.request;

import com.sistemaifes.sistemaifes.model.Coordination;

public record TeacherRequestDTO( 
    String name,
    String teacherCode,
    String specialty,
    String educationLevel,
    boolean situation,
    boolean isCoordinator,
    String login,
    String password,
    Coordination coordination
) {
    
}
