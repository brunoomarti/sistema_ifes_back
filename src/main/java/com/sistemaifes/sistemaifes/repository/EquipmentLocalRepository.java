package com.sistemaifes.sistemaifes.repository;

import com.sistemaifes.sistemaifes.model.Local;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemaifes.sistemaifes.model.EquipmentLocal;

public interface EquipmentLocalRepository extends JpaRepository<EquipmentLocal, Long>{
    void deleteByLocation(Local location);
}
