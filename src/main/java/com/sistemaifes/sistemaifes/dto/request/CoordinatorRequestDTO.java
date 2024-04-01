package com.sistemaifes.sistemaifes.dto.request;

import com.sistemaifes.sistemaifes.model.Coordination;

public record CoordinatorRequestDTO(
    String name, 
    String login,
    String password,
    Coordination coordination 
) {
    
}
