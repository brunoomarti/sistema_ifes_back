package com.sistemaifes.sistemaifes.dto.request;

import com.sistemaifes.sistemaifes.model.Allocation;

public record EventRequestDTO(
    Long _id,
    String name,
    String applicant,
    String description,
    boolean allocated,
    Allocation allocation
) {
    
}
