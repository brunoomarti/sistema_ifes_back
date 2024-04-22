package com.sistemaifes.sistemaifes.dto.request;

import java.util.List;
 
import com.sistemaifes.sistemaifes.model.Discipline;
import com.sistemaifes.sistemaifes.model.Semester;
import com.sistemaifes.sistemaifes.model.Student;
import com.sistemaifes.sistemaifes.model.Teacher;

public record LessonRequestDTO(
    Long _id,
    Semester semester,
    Discipline discipline,
    Teacher teacher,
    List<Student> students,
    Boolean allocated 
) {
    
}
