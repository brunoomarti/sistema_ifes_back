package com.sistemaifes.sistemaifes.dto.request;

import com.sistemaifes.sistemaifes.model.Course;

public record DisciplineRequestDTO(
    String name,
    Course course
) {
    
}
