package com.sistemaifes.sistemaifes.dto.response;

import java.util.List;

import com.sistemaifes.sistemaifes.model.Allocation;
import com.sistemaifes.sistemaifes.model.Classe;
import com.sistemaifes.sistemaifes.model.Event;
import com.sistemaifes.sistemaifes.model.Lesson;
import com.sistemaifes.sistemaifes.model.Local;
import com.sistemaifes.sistemaifes.model.Schedule;

public record AllocationResponseDTO(
    Long _id,
    String startDate,
    String endDate,
    String startTime,
    String endTime,
    String weekDay,
    String type,
    String applicant,
    Lesson lesson,
    Event event,
    Local location,
    Classe classe,
    List<Schedule> selectedTimes,
    Boolean active
) {
    public AllocationResponseDTO(Allocation allocation){
        this(
            allocation.get_id(), 
            allocation.getStartDate(),
            allocation.getEndDate(),
            allocation.getStartTime(),
            allocation.getEndTime(),
            allocation.getWeekDay(),
            allocation.getType(),
            allocation.getApplicant(),
            allocation.getLesson(),
            allocation.getEvent(),
            allocation.getLocation(),
            allocation.getClasse(),
            allocation.getSelectedTimes(),
            allocation.getActive()
        );
    }
}
