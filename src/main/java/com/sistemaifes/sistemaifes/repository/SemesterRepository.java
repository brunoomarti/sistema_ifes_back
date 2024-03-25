package com.sistemaifes.sistemaifes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
 
import com.sistemaifes.sistemaifes.model.Semester;

public interface SemesterRepository extends JpaRepository<Semester, Long>{ 
}
