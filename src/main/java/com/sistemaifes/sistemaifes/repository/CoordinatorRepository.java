package com.sistemaifes.sistemaifes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemaifes.sistemaifes.model.Coordinator;

public interface CoordinatorRepository extends JpaRepository<Coordinator, Long> {
    
}