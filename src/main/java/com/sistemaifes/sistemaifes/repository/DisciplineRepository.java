package com.sistemaifes.sistemaifes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
 
import com.sistemaifes.sistemaifes.model.Discipline;
import org.springframework.data.jpa.repository.Query;

public interface DisciplineRepository extends JpaRepository<Discipline, Long>{
    boolean existsByNameIgnoreCase(String name);

    @Query("SELECT d FROM Discipline d WHERE d.course._id = :cursoId")
    List<Discipline> findByCourseId(Long cursoId);
    
}
