package com.sistemaifes.sistemaifes.dto.request;

import java.sql.Date;

public record SemesterRequestDTO(
    String semester,
    Date startDate,
    Date endDate
) {
    
}
