package com.sistemaifes.sistemaifes.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import com.sistemaifes.sistemaifes.Equipment.EquipmentRepository;
import com.sistemaifes.sistemaifes.Equipment.EquipmentRequestDTO;
import com.sistemaifes.sistemaifes.Equipment.EquipmentResponseDTO;
import com.sistemaifes.sistemaifes.exception.RecordNotFoundException;
import com.sistemaifes.sistemaifes.model.Equipment;

@Service
public class EquipmentService {
    private final EquipmentRepository repository;

    public EquipmentService(EquipmentRepository repository){
        this.repository = repository;
    }

    public List<EquipmentResponseDTO> getAll() {
        return repository.findAll().stream().map((EquipmentResponseDTO::new)).toList();
    }

    public Equipment findById(@PathVariable @NotNull @Positive Long id){
        return repository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public Equipment saveEquipment(EquipmentRequestDTO data){
        Equipment eqData = new Equipment(data);
        return repository.save(eqData);
    }

    public Equipment update(@NotNull @Positive Long id, @Valid Equipment equipment){
        return repository.findById(id)
                .map(recordFound -> {
                    recordFound.setName(equipment.getName());
                    return repository.save(recordFound);
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@PathVariable @NotNull Long id){
        repository.delete(repository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
