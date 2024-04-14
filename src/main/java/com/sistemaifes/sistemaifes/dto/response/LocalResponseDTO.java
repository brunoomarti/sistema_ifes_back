package com.sistemaifes.sistemaifes.dto.response;

import com.sistemaifes.sistemaifes.model.Local;

public record LocalResponseDTO(
    Long _id,
    String name,
    Integer capacity
) {
    public LocalResponseDTO(Local local){
        this(
            local.get_id(), 
            local.getName(),
            local.getCapacity()
        );
    }
}
