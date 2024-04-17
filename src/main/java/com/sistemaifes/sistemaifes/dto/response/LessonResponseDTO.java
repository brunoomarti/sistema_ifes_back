package com.sistemaifes.sistemaifes.dto.response;

import java.util.List;

import com.sistemaifes.sistemaifes.model.Discipline;
import com.sistemaifes.sistemaifes.model.Lesson;
import com.sistemaifes.sistemaifes.model.Semester;
import com.sistemaifes.sistemaifes.model.Student;
import com.sistemaifes.sistemaifes.model.Teacher;

public record LessonResponseDTO(
    Long _id,
    Semester semester,
    Discipline discipline,
    Teacher teacher,
    List<Student> students,
    Boolean allocated
) {
    public LessonResponseDTO(Lesson lesson){
        this(
            lesson.get_id(),
            lesson.getSemester(),
            lesson.getDiscipline(),
            lesson.getTeacher(),
            lesson.getStudents(),
            lesson.getAllocated()
        );
    }
    
}
