package com.sistemaifes.sistemaifes.dto.response;

import com.sistemaifes.sistemaifes.model.Classroom;

public record ClassroomResponseDTO(
    Long _id,
    String name
) {
    public ClassroomResponseDTO(Classroom classroom){
        this(classroom.get_id(), classroom.getName());
    }
}
