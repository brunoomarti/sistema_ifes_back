package com.sistemaifes.sistemaifes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
 
import com.sistemaifes.sistemaifes.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long>{
    boolean existsByNameIgnoreCase(String name);
    
    List<Teacher> findByNameContainingIgnoreCase(String name);
}
