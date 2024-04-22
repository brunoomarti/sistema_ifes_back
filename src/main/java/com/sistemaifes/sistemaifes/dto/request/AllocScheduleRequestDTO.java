package com.sistemaifes.sistemaifes.dto.request;

import com.sistemaifes.sistemaifes.model.Allocation;
import com.sistemaifes.sistemaifes.model.Schedule;

public record AllocScheduleRequestDTO(
    Long _id,
    Allocation allocation,
    Schedule schedule
) {
    
}
