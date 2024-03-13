package com.sistemaifes.sistemaifes.dto.response;

import com.sistemaifes.sistemaifes.dto.response.StudentResponseDTO;
import com.sistemaifes.sistemaifes.model.Student;

public record StudentResponseDTO(
    Long _id,
    String name,
    String studentCode,
    double performanceCoefficient,
    boolean situation,
    Integer period
) {
    public StudentResponseDTO(Student student){
        this(student.get_id(),
            student.getName(),
            student.getStudentCode(),
            student.getPerformanceCoefficient(),
            student.isSituation(),
            student.getPeriod()
        );
    }
} 