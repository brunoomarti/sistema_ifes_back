package com.sistemaifes.sistemaifes.dto.request;

import com.sistemaifes.sistemaifes.model.Coordinator;

public record TeacherRequestDTO(
    String name,
    String teacherCode,
    String specialty,
    String academic_degree,
    boolean situation,
    Coordinator coordinator
) {
    
}
