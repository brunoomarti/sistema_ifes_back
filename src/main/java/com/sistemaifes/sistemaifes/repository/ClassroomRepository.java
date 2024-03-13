package com.sistemaifes.sistemaifes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemaifes.sistemaifes.model.Classroom;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
    
}
