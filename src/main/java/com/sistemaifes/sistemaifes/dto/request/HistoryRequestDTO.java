package com.sistemaifes.sistemaifes.dto.request;

import java.util.Date;
import java.util.List;

import com.sistemaifes.sistemaifes.model.*;

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
    List<Schedule> selectedTimes
    
) {
    
}
