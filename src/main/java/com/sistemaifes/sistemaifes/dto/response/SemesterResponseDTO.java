package com.sistemaifes.sistemaifes.dto.response;

import java.time.LocalDate; 

import com.sistemaifes.sistemaifes.model.Semester; 

public record SemesterResponseDTO(
    Long _id,
    String semester,
    LocalDate startDate,
    LocalDate endDate
) {
    public SemesterResponseDTO(Semester semester){
        this(
            semester.get_id(),
            semester.getSemester(),
            semester.getStartDate(),
            semester.getEndDate()
        );
    }
}
