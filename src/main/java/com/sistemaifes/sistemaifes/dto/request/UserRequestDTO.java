package com.sistemaifes.sistemaifes.dto.request;

public record UserRequestDTO(
    Long _id,
    String name,
    String login,
    String password,
    boolean estahAtivo
) {
    
}
