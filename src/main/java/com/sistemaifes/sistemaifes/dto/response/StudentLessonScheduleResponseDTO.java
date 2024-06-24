package com.sistemaifes.sistemaifes.dto.response;

import com.sistemaifes.sistemaifes.model.StudentLessonSchedule;

import java.time.ZonedDateTime;

public record StudentLessonScheduleResponseDTO(
        String studentName,
        String disciplineName,
        String teacherName,
        String localName,
        String completeDisciplineName,
        ZonedDateTime startTimeTs
) {
    public StudentLessonScheduleResponseDTO(StudentLessonSchedule studentLessonSchedule){
        this(
                studentLessonSchedule.getStudentName(),
                studentLessonSchedule.getDisciplineName(),
                studentLessonSchedule.getTeacherName(),
                studentLessonSchedule.getLocalName(),
                studentLessonSchedule.getCompleteDisciplineName(),
                studentLessonSchedule.getStartTimeTs()
        );
    }
}
