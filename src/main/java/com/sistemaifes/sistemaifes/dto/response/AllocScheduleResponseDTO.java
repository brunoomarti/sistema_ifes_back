package com.sistemaifes.sistemaifes.dto.response;

import com.sistemaifes.sistemaifes.model.AllocSchedule;
import com.sistemaifes.sistemaifes.model.Allocation;
import com.sistemaifes.sistemaifes.model.Schedule;

public record AllocScheduleResponseDTO(
    Long _id,
    Schedule schedule,
    Allocation allocation
) {
    public AllocScheduleResponseDTO(AllocSchedule allocSchedule){
        this(
            allocSchedule.get_id(), 
            allocSchedule.getSchedule(),
            allocSchedule.getAllocation()
        );
    }
}
