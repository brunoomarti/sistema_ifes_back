package com.sistemaifes.sistemaifes.dto.request;

import com.sistemaifes.sistemaifes.util.UserRole;

public record UserRequestDTO(
    String name,
    String login,
    String password,
    UserRole role
) {
    
}
