package com.sistemaifes.sistemaifes.dto.response;

import com.sistemaifes.sistemaifes.model.StudentSchedule;

public record StudentScheduleResponseDTO(
    Long id_studentSchedule,
    String name,
    String _id,
    String start_time,
    String end_time  
) {
    public StudentScheduleResponseDTO(StudentSchedule student){
        this(
            student.getId_studentSchedule(),
            student.getName(),
            student.get_id(),
            student.getStart_time(),
            student.getEnd_time()
        );
    }
    
}
