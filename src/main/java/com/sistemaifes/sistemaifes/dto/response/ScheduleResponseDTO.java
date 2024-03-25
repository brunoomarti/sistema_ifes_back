package com.sistemaifes.sistemaifes.dto.response;
 
import java.util.Date;

import com.sistemaifes.sistemaifes.model.Schedule;

public record ScheduleResponseDTO(
    Long _id,
    Date startTime,
    Date endTime
) {

    public ScheduleResponseDTO(Schedule schedule){
        this(
            schedule.get_id(),
            schedule.getStartTime(),
            schedule.getEndTime()
        );
    }
    
}
