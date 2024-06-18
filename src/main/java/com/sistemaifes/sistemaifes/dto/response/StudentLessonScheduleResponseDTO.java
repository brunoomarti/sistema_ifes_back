package com.sistemaifes.sistemaifes.dto.response;

import com.sistemaifes.sistemaifes.model.StudentLessonSchedule;
import com.sistemaifes.sistemaifes.model.StudentSchedule;

import java.time.ZonedDateTime;

public record StudentLessonScheduleResponseDTO(
        String studentName,
        String disciplineName,
        String localName,
        ZonedDateTime startTimeTs
) {
    public StudentLessonScheduleResponseDTO(StudentLessonSchedule studentLessonSchedule){
        this(
                studentLessonSchedule.getStudentName(),
                studentLessonSchedule.getDisciplineName(),
                studentLessonSchedule.getLocalName(),
                studentLessonSchedule.getStartTimeTs()
        );
    }
}
