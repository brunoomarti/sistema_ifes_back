package com.sistemaifes.sistemaifes.dto.request;

public record StudentRequestDTO(
    String name,
    String studentCode,
    double performanceCoefficient,
    boolean situation,
    Integer period
    ) {
    
}