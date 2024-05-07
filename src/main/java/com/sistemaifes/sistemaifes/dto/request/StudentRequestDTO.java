package com.sistemaifes.sistemaifes.dto.request;

import com.sistemaifes.sistemaifes.model.Course;

public record StudentRequestDTO(
    String name,
    String studentCode,
    Course course
    ) {
    
}