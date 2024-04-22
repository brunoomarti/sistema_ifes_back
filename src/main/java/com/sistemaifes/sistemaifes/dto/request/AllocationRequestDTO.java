package com.sistemaifes.sistemaifes.dto.request;


import com.sistemaifes.sistemaifes.model.Classe;
import com.sistemaifes.sistemaifes.model.Event;
import com.sistemaifes.sistemaifes.model.Lesson;
import com.sistemaifes.sistemaifes.model.Local;

public record AllocationRequestDTO(
    Long _id,
    String startDate,
    String endDate,
    String type,
    Lesson lesson,
    Event event,
    Local location,
    Classe classe
) {
    
}
