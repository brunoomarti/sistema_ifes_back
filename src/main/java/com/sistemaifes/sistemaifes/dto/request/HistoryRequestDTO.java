package com.sistemaifes.sistemaifes.dto.request;

import java.time.LocalTime;
import java.util.Date;

import com.sistemaifes.sistemaifes.model.Allocation;

public record HistoryRequestDTO(
    Long _id,
    Date date,
    String action,
    String authorName,
    LocalTime time,
    String oldContent,
    String newContent,
    Allocation allocation
    
) {
    
}
