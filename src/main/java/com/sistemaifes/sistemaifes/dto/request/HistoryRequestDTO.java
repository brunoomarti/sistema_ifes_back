package com.sistemaifes.sistemaifes.dto.request;

import java.util.Date;
import java.util.List;

import com.sistemaifes.sistemaifes.model.Allocation;
import com.sistemaifes.sistemaifes.model.Classe;
import com.sistemaifes.sistemaifes.model.Event;
import com.sistemaifes.sistemaifes.model.Lesson;
import com.sistemaifes.sistemaifes.model.Local;
import com.sistemaifes.sistemaifes.model.Schedule;

public record HistoryRequestDTO(
    Long _id,
    Date date,
    String authorName,
    String changeType,
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
    Allocation allocation,
    String selectedTimes
    
) {
    
}
