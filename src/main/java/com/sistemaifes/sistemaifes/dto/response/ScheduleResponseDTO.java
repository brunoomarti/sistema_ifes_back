package com.sistemaifes.sistemaifes.dto.response;

import java.time.LocalDate; 
import com.sistemaifes.sistemaifes.model.Schedule;

public record ScheduleResponseDTO(
    Long _id,
    LocalDate startTime,
    LocalDate endTime
) {

    public ScheduleResponseDTO(Schedule schedule){
        this(
            schedule.get_id(),
            schedule.getStartTime(),
            schedule.getEndTime()
        );
    }
    
}
