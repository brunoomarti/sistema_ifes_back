package com.sistemaifes.sistemaifes.Equipment;

import com.sistemaifes.sistemaifes.model.Equipment;

public record EquipmentResponseDTO(Long id, String name) {
    public EquipmentResponseDTO(Equipment equipment){
        this(equipment.getId(), equipment.getName());
    }
}