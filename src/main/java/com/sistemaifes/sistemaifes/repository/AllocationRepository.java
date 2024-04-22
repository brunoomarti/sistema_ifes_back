package com.sistemaifes.sistemaifes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sistemaifes.sistemaifes.model.Allocation;

public interface AllocationRepository extends JpaRepository<Allocation, Long>{
    
}
