package com.sistemaifes.sistemaifes.dto.request;

public record StudentRequestDTO(
    String name,
    String login,
    String password,
    String studentCode
    ) {
    
}