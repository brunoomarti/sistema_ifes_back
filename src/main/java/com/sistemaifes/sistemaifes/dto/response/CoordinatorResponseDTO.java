package com.sistemaifes.sistemaifes.dto.response;

import com.sistemaifes.sistemaifes.model.Coordination;
import com.sistemaifes.sistemaifes.model.Coordinator;
import com.sistemaifes.sistemaifes.util.UserRole;

public record CoordinatorResponseDTO(
    Long _id,
    String name,
    UserRole role,
    Coordination coordination
){
    public CoordinatorResponseDTO(Coordinator coordinator){
        this(
           coordinator.get_id(),
           coordinator.getName(),
           coordinator.getRole(),
           coordinator.getCoordination() 
        );
    }
    
}
