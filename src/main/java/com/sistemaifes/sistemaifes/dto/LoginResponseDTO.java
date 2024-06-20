package com.sistemaifes.sistemaifes.dto;

import com.sistemaifes.sistemaifes.util.UserRole;

public record LoginResponseDTO(String name, String token, String role, String user_code) {
    
}
