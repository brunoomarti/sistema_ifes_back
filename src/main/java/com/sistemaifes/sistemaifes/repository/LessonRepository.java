package com.sistemaifes.sistemaifes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.sistemaifes.sistemaifes.model.Lesson;

import jakarta.transaction.Transactional;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long>{

    @Query("SELECT l FROM Lesson l " +
            "JOIN l.students s " +
            "JOIN l.semester sem " +
            "WHERE s.studentCode = :studentCode " +
            "AND sem._id = :semesterId")
    List<Lesson> findLessonsByStudentCodeAndSemesterId(
            @Param("studentCode") String studentCode,
            @Param("semesterId") Long semesterId
    );

    @Query(
        "SELECT l " +
        "FROM " +
            "Lesson AS l " +
        "JOIN l.teacher AS t " +
        "JOIN l.semester AS s " +
        "JOIN l.discipline AS d " +
        "WHERE " +
            "t.teacherCode = :teacherCode AND " +
            "s.id = :semesterId " +
        "GROUP BY " +
            "l"
        )
    List<Lesson> findLessonsByTeacherCodeAndSemesterId(
            @Param("teacherCode") String teacherCode,
            @Param("semesterId") Long semesterId
    );

    
    @Transactional
    @Modifying
    @Query(value = """ 
        DELETE FROM lesson_student ls
        WHERE ls.id_lesson = :lessonId 
        AND ls.id_student = :studentId 
    """, nativeQuery = true)
    void removeStudentFromLesson(Long studentId, Long lessonId);
}
