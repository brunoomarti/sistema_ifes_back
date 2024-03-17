package com.sistemaifes.sistemaifes.dto.response;

import com.sistemaifes.sistemaifes.model.Classe;

public record ClasseResponseDTO(Long _id, String name) {
    public ClasseResponseDTO(Classe c){
        this(c.get_id(), c.getName());
    }
}
