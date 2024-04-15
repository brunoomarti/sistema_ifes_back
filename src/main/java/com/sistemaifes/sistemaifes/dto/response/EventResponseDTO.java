package com.sistemaifes.sistemaifes.dto.response;
 
import com.sistemaifes.sistemaifes.model.Event;

public record EventResponseDTO(
    Long _id,
    String name,
    String description,
    boolean allocated
) {
    public EventResponseDTO(Event event){
        this(
            event.get_id(),
            event.getName(),
            event.getDescription(),
            event.isAllocated()
        );
    }
    
}
