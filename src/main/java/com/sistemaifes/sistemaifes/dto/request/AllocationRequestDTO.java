package com.sistemaifes.sistemaifes.dto.request;

import java.sql.Date;
import java.util.List;

import com.sistemaifes.sistemaifes.model.AllocSchedule;
import com.sistemaifes.sistemaifes.model.Classe;
import com.sistemaifes.sistemaifes.model.Event;
import com.sistemaifes.sistemaifes.model.Lesson;
import com.sistemaifes.sistemaifes.model.Local;

public record AllocationRequestDTO(
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
    
}
