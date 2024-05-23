package com.sistemaifes.sistemaifes.dto.request;

import java.time.ZonedDateTime;

public record StudentLessonScheduleRequestDTO(
        String studentName,
        String disciplineName,
        String localName,
        ZonedDateTime startTimeTs
) {
}
