package com.sistemaifes.sistemaifes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
 
import com.sistemaifes.sistemaifes.model.Discipline;

public interface DisciplineRepository extends JpaRepository<Discipline, Long>{
    boolean existsByNameIgnoreCase(String name);
    
}
