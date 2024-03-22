package com.sistemaifes.sistemaifes.dto.request;

import com.sistemaifes.sistemaifes.model.Coordination;

public record CoordinatorRequestDTO(
    String name,
    String shift,
    Coordination coordinators
) {
    
}
