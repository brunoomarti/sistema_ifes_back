package com.sistemaifes.sistemaifes.dto.response;
 
import com.sistemaifes.sistemaifes.model.Event;

public record EventResponseDTO(
    Long _id,
    String name,
    String applicant,
    String description,
    boolean allocated
) {
    public EventResponseDTO(Event event){
        this(
            event.get_id(),
            event.getName(),
            event.getApplicant(),
            event.getDescription(),
            event.isAllocated()
        );
    }
    
}
