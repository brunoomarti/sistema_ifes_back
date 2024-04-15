package com.sistemaifes.sistemaifes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemaifes.sistemaifes.model.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Long>{
    
}
