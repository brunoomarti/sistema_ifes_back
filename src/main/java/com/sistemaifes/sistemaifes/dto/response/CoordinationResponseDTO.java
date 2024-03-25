package com.sistemaifes.sistemaifes.dto.response;

import com.sistemaifes.sistemaifes.model.Coordination; 

public record CoordinationResponseDTO(
    Long _id,
    String name,
    String description,
    String acronym
) {
    public CoordinationResponseDTO(Coordination coordinator){
        this(
            coordinator.get_id(), 
            coordinator.getName(),
            coordinator.getDescription(),
            coordinator.getAcronym()
            );
    }
}
