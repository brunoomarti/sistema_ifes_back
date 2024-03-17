package com.sistemaifes.sistemaifes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemaifes.sistemaifes.model.Coordinator;

public interface CoordinatorRepository extends JpaRepository<Coordinator, Long> {

    boolean existsByNameIgnoreCase(String name);

    List<Coordinator> findByNameContainingIgnoreCase(String name);
    
}
