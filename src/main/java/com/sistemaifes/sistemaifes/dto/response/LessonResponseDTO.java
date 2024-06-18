package com.sistemaifes.sistemaifes.dto.response;

import java.util.List;

import com.sistemaifes.sistemaifes.model.Allocation;
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
        int weeklyQuantity,
        List<Student> students,
        Boolean allocated,
        List<AllocationResponseDTO> allocations
) {
    public LessonResponseDTO(Lesson lesson){
        this(
                lesson.get_id(),
                lesson.getSemester(),
                lesson.getDiscipline(),
                lesson.getTeacher(),
                lesson.getWeeklyQuantity(),
                lesson.getStudents(),
                lesson.getAllocated(),
                getAllocationResponseDTOs(lesson.getAllocations())
        );
    }

    private static List<AllocationResponseDTO> getAllocationResponseDTOs(List<Allocation> allocations) {
        return allocations.stream()
                .map(AllocationResponseDTO::new)
                .toList();
    }
}
