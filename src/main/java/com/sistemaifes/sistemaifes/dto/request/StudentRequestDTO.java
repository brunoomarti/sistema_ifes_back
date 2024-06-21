package com.sistemaifes.sistemaifes.dto.request;

import com.sistemaifes.sistemaifes.model.Course;
import com.sistemaifes.sistemaifes.util.UserRole;

public record StudentRequestDTO(
    String name,
    String studentCode,
    String registrationYear,
    UserRole role,
    Course course
    ) {
    
}