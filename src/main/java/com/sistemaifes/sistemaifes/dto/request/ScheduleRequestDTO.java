package com.sistemaifes.sistemaifes.dto.request;

import java.util.Date; 

public record ScheduleRequestDTO(
    Date startTime,
    Date endTime
) {
    
}
