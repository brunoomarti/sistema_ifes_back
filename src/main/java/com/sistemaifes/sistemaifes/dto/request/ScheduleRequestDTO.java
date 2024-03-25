package com.sistemaifes.sistemaifes.dto.request;

import java.time.LocalDate; 

public record ScheduleRequestDTO(
    LocalDate startTime,
    LocalDate endTime
) {
    
}
