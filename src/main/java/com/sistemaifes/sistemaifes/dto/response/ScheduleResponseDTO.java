package com.sistemaifes.sistemaifes.dto.response;
 
import com.sistemaifes.sistemaifes.model.Schedule;

public record ScheduleResponseDTO(
    Long _id,
    String startTime,
    String endTime
) {

    public ScheduleResponseDTO(Schedule schedule){
        this(
            schedule.get_id(),
            schedule.getStartTime(),
            schedule.getEndTime()
        );
    }
    
}
