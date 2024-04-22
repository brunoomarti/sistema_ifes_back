package com.sistemaifes.sistemaifes.dto.request;

import com.sistemaifes.sistemaifes.model.Equipment;
import com.sistemaifes.sistemaifes.model.Local;

public record EquipmentLocalRequestDTO(
    Long _id,
    Equipment equipment,
    Local location,
    Integer quantity
) {
}