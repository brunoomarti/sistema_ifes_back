package com.sistemaifes.sistemaifes.dto.response;

import com.sistemaifes.sistemaifes.model.Coordination;
import com.sistemaifes.sistemaifes.model.Coordinator;

public record CoordinatorResponseDTO(
    Coordination coordination
){
    public CoordinatorResponseDTO(Coordinator coordinator){
        this(
           coordinator.getCoordination() 
        );
    }
    
}
