package com.sistemaifes.sistemaifes.dto.response;

import com.sistemaifes.sistemaifes.model.NextLessonTeacher;
import com.sistemaifes.sistemaifes.model.StudentLessonSchedule;

import java.time.ZonedDateTime;

public record NextLessonTeacherResponseDTO(
        String teacherName,
        String disciplineName,
        String localName,
        ZonedDateTime startTimeTs
) {
    public NextLessonTeacherResponseDTO(NextLessonTeacher nextLessonTeacher){
        this(
                nextLessonTeacher.getTeacherName(),
                nextLessonTeacher.getDisciplineName(),
                nextLessonTeacher.getLocalName(),
                nextLessonTeacher.getStartTimeTs()
        );
    }
}
