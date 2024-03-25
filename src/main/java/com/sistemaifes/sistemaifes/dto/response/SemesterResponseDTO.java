package com.sistemaifes.sistemaifes.dto.response;

import java.util.Date;

import com.sistemaifes.sistemaifes.model.Semester; 

public record SemesterResponseDTO(
    Long _id,
    String semester,
    Date startDate,
    Date endDate
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
