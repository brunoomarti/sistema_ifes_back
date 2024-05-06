package com.sistemaifes.sistemaifes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
 
import com.sistemaifes.sistemaifes.model.Student;
import com.sistemaifes.sistemaifes.model.StudentSchedule;
 
public interface StudentRepository extends JpaRepository<Student, Long>  {
    boolean existsByNameIgnoreCase(String name);
    
    List<Student> findByNameContainingIgnoreCase(String name);

    Student findByStudentCode(String studentCode);
}