package com.sistemaifes.sistemaifes.dto.response;
 
import java.util.Date;
import java.util.List;

import com.sistemaifes.sistemaifes.model.*;

public record HistoryResponseDTO(
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
    public HistoryResponseDTO(History history){
        this(
            history.get_id(),
            history.getDate(),
            history.getAuthorName(),
            history.getChangeType(),
            history.getStartDate(),
            history.getEndDate(),
            history.getStartTime(),
            history.getEndTime(),
            history.getWeekDay(),
            history.getType(),
            history.getApplicant(),
            history.getLesson(),
            history.getEvent(),
            history.getLocation(),
            history.getClasse(),
            history.getAllocation(),
            history.getSelectedTimes()
        );
    }
}
