package com.sistemaifes.sistemaifes.dto.request;
 
import java.time.LocalDate;

public record SemesterRequestDTO(
    String semester,
    LocalDate startDate,
    LocalDate endDate
) {
    
}
