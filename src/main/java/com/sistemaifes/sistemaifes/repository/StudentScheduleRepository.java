package com.sistemaifes.sistemaifes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sistemaifes.sistemaifes.model.StudentSchedule;

public interface StudentScheduleRepository extends JpaRepository<StudentSchedule, Long> {

    @Query(value = """ 
        SELECT user_ifes.name, Lesson._id as id_lesson, schedule.start_time, schedule.end_time, 0 AS id_student_schedule
        FROM Student
        JOIN lesson_student ON Student._id = lesson_student.id_student
        JOIN Lesson ON lesson_student.id_lesson = Lesson._id
        JOIN Allocation ON Lesson._id = Allocation.id_lesson
        JOIN allocation_schedule ON Allocation._id = allocation_schedule.allocation_id
        JOIN Schedule ON allocation_schedule.schedule_id = Schedule._id
        JOIN Discipline ON Lesson.id_discipline = Discipline._id
        JOIN User_ifes ON Student._id = User_ifes._id
        WHERE Student._id = :studentId 
    """, nativeQuery = true) 
    List<StudentSchedule> getStudentSchedule(Long studentId);
    
}
