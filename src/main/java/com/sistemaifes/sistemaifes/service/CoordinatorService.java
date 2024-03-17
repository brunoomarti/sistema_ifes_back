package com.sistemaifes.sistemaifes.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.sistemaifes.sistemaifes.dto.request.CoordinatorRequestDTO;
import com.sistemaifes.sistemaifes.dto.request.EquipmentRequestDTO;
import com.sistemaifes.sistemaifes.dto.response.CoordinatorResponseDTO;
import com.sistemaifes.sistemaifes.exception.ItemAlreadyRegisteredException;
import com.sistemaifes.sistemaifes.exception.RecordNotFoundException;
import com.sistemaifes.sistemaifes.model.Coordinator;
import com.sistemaifes.sistemaifes.model.Equipment;
import com.sistemaifes.sistemaifes.repository.CoordinatorRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
public class CoordinatorService {
    private final CoordinatorRepository repository;

    public CoordinatorService(CoordinatorRepository repository){
        this.repository = repository;
    }

    public List<CoordinatorResponseDTO> getAll() {
        return repository.findAll().stream().map((CoordinatorResponseDTO::new)).toList();
    }

    public Coordinator findById(@PathVariable @NotNull @Positive Long id){
        return repository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public Coordinator saveEquipment(CoordinatorRequestDTO data){
        Coordinator cordData = new Coordinator(data);
        
        if (verifyIfCoordinatorExist(data.name())){
            throw new ItemAlreadyRegisteredException(data.name());
        }

        return repository.save(cordData);
    }

    public boolean verifyIfCoordinatorExist(String equipmentName) {
        return repository.existsByNameIgnoreCase(equipmentName);
    }

    public Coordinator update(@NotNull @Positive Long id, @Valid Coordinator coordinator){
        return repository.findById(id)
                .map(recordFound -> {
                    recordFound.setName(coordinator.getName());
                    recordFound.setShift(coordinator.getShift());
                    return repository.save(recordFound);
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@PathVariable @NotNull Long id){
        repository.delete(repository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException(id)));
    }

    public List<Coordinator> findCoordinatorByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }
}
