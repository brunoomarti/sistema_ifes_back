package com.sistemaifes.sistemaifes.dto.response;

import com.sistemaifes.sistemaifes.model.StudentSchedule;

public record StudentScheduleResponseDTO(
    String name,
    String id_lesson,
    String start_time,
    String end_time  
) {
    public StudentScheduleResponseDTO(StudentSchedule student){
        this( 
            student.getName(),
            student.getId_lesson(),
            student.getStart_time(),
            student.getEnd_time()
        );
    }
    
}
