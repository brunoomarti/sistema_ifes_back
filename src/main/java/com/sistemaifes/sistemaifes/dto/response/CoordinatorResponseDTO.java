package com.sistemaifes.sistemaifes.dto.response;

import com.sistemaifes.sistemaifes.model.Coordinator;

public record CoordinatorResponseDTO(
    Long _id,
    String name,
    String shift
){
    public CoordinatorResponseDTO(Coordinator coordinator){
        this(
            coordinator.get_id(), 
            coordinator.getName(),
            coordinator.getShift()
        );
    }
    
}
