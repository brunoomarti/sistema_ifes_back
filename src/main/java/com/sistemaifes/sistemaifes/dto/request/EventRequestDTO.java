package com.sistemaifes.sistemaifes.dto.request;

import java.sql.Date;
import java.time.LocalDate;

public record EventRequestDTO(
    Long _id,
    String description,
    LocalDate eventDate,
    Date startTime,
    Date endTime
) {
    
}
