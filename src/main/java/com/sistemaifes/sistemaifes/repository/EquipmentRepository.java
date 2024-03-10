package com.sistemaifes.sistemaifes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sistemaifes.sistemaifes.model.Equipment;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    boolean existsByNameIgnoreCase(String name);
    
    List<Equipment> findByNameContainingIgnoreCase(String name);
}
