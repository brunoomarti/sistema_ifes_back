package com.sistemaifes.sistemaifes.dto.request;

public record StudentRequestDTO(
    Long _id,
    String name,
    String studentCode,
    double performanceCoefficient,
    boolean situation,
    Integer period
    ) {
    
}