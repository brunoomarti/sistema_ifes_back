package com.sistemaifes.sistemaifes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemaifes.sistemaifes.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long>  {
}