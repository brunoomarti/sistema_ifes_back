package com.sistemaifes.sistemaifes.Equipment;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sistemaifes.sistemaifes.model.Equipment;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    
}
