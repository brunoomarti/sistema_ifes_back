package com.sistemaifes.sistemaifes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
 
import com.sistemaifes.sistemaifes.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long>  {
    boolean existsByNameIgnoreCase(String name);
    
    List<Student> findByNameContainingIgnoreCase(String name);
}