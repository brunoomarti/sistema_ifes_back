package com.sistemaifes.sistemaifes.dto.request;

import java.time.ZonedDateTime;

public record NextLessonTeacherRequestDTO(
        String teacherName,
        String disciplineName,
        String localName,
        ZonedDateTime startTimeTs

) {
}
