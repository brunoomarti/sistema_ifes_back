package com.sistemaifes.sistemaifes.dto.request;

public record EventRequestDTO(
    Long _id,
    String name,
    String description,
    boolean allocated
) {
    
}
