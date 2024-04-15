package com.sistemaifes.sistemaifes.dto.response;

import java.util.List;

import com.sistemaifes.sistemaifes.model.EquipmentLocal;
import com.sistemaifes.sistemaifes.model.Local;

public record LocalResponseDTO(
    Long _id,
    String name,
    Integer capacity,
    List<EquipmentLocal> equipments,
    boolean allocated
) {
    public LocalResponseDTO(Local local){
        this(
            local.get_id(), 
            local.getName(),
            local.getCapacity(),
            local.getEquipments(),
            local.isAllocated()
        );
    }
}
