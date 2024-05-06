package com.sistemaifes.sistemaifes.dto.request;

public record StudentScheduleRequestDTO(
    Long id_studentSchedule,
    String name,
    String _id,
    String start_time,
    String end_time
) {
}
