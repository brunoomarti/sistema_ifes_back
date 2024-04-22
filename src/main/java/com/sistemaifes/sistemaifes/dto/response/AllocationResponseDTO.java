package com.sistemaifes.sistemaifes.dto.response;

import java.util.Date;
import java.util.List;

import com.sistemaifes.sistemaifes.model.AllocSchedule;
import com.sistemaifes.sistemaifes.model.Allocation;
import com.sistemaifes.sistemaifes.model.Classe;
import com.sistemaifes.sistemaifes.model.Event;
import com.sistemaifes.sistemaifes.model.Lesson;
import com.sistemaifes.sistemaifes.model.Local;

public record AllocationResponseDTO(
    Long _id,
    Date startDate,
    Date endDate,
    String type,
    List<Lesson> lessons,
    List<Event> events,
    Local location,
    Classe classe,
    List<AllocSchedule> selectedTimes
) {
    public AllocationResponseDTO(Allocation allocation){
        this(
            allocation.get_id(), 
            allocation.getStartDate(),
            allocation.getEndDate(),
            allocation.getType(),
            allocation.getLessons(),
            allocation.getEvents(),
            allocation.getLocation(),
            allocation.getClasse(),
            allocation.getSelectedTimes()
        );
    }
}
