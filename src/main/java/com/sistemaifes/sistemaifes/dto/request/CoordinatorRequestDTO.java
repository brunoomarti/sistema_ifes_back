package com.sistemaifes.sistemaifes.dto.request;

import com.sistemaifes.sistemaifes.model.Coordination;
import com.sistemaifes.sistemaifes.util.UserRole;

public record CoordinatorRequestDTO(
    String name,
    UserRole role,
    Coordination coordination 
) {
    
}
