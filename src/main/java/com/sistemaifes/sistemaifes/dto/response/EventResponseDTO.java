package com.sistemaifes.sistemaifes.dto.response;

import java.time.LocalDate;
import java.util.Date;

import com.sistemaifes.sistemaifes.model.Event;

public record EventResponseDTO(
    Long _id,
    String description,
    LocalDate eventDate,
    Date startTime,
    Date endTime
) {
    public EventResponseDTO(Event event){
        this(
            event.get_id(),
            event.getDescription(),
            event.getEventDate(),
            event.getStartTime(),
            event.getEndTime()
        );
    }
    
}
