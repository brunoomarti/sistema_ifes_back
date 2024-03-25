package com.sistemaifes.sistemaifes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
 
import com.sistemaifes.sistemaifes.model.Coordination;

public interface CoordinationRepository extends JpaRepository<Coordination, Long> {
    boolean existsByNameIgnoreCase(String name);
    
    List<Coordination> findByNameContainingIgnoreCase(String name);
}
