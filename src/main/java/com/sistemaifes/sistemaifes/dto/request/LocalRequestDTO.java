package com.sistemaifes.sistemaifes.dto.request;

import java.util.List;

import com.sistemaifes.sistemaifes.model.EquipmentLocal;

public record LocalRequestDTO(
    Long _id,
    String name,
    Integer capacity,
    List<EquipmentLocal> equipments,
    boolean allocated
) {
    
}
