package com.sistemaifes.sistemaifes.dto.request;

import com.sistemaifes.sistemaifes.model.Coordinator;

public record CoordinationRequestDTO(
    String name,
    String description,
    String acronym,
    Coordinator coordinator

) {
    
}
