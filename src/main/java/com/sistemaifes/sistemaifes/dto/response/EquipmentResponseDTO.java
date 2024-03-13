package com.sistemaifes.sistemaifes.dto.response;

import com.sistemaifes.sistemaifes.model.Equipment;

public record EquipmentResponseDTO(Long _id, String name) {
    public EquipmentResponseDTO(Equipment equipment){
        this(equipment.get_id(), equipment.getName());
    }
}