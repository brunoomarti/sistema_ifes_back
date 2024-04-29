package com.sistemaifes.sistemaifes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.sistemaifes.sistemaifes.model.Lesson;

import jakarta.transaction.Transactional;

public interface LessonRepository extends JpaRepository<Lesson, Long>{
    
    @Transactional
    @Modifying
    @Query(value = """ 
        DELETE FROM lesson_student ls
        WHERE ls.id_lesson = :lessonId 
        AND ls.id_student = :studentId 
    """, nativeQuery = true)
    void removeStudentFromLesson(Long studentId, Long lessonId);
}
