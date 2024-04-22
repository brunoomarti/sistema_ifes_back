package com.sistemaifes.sistemaifes.dto.response;

import com.sistemaifes.sistemaifes.model.Equipment;
import com.sistemaifes.sistemaifes.model.EquipmentLocal;
import com.sistemaifes.sistemaifes.model.Local;

public record EquipmentLocalResponseDTO(
    Long _id,
    Equipment equipment,
    Local local,
    Integer quantity
) {
    public EquipmentLocalResponseDTO(EquipmentLocal equipmentLocal){
        this(
            equipmentLocal.get_id(), 
            equipmentLocal.getEquipment(),
            equipmentLocal.getLocation(),
            equipmentLocal.getQuantity()
        );
    }
}
