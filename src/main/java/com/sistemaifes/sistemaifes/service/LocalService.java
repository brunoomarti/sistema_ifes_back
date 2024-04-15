package com.sistemaifes.sistemaifes.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.sistemaifes.sistemaifes.dto.request.LocalRequestDTO;
import com.sistemaifes.sistemaifes.dto.response.LocalResponseDTO;
import com.sistemaifes.sistemaifes.exception.RecordNotFoundException;
import com.sistemaifes.sistemaifes.model.EquipmentLocal;
import com.sistemaifes.sistemaifes.model.Local;
import com.sistemaifes.sistemaifes.repository.EquipmentLocalRepository;
import com.sistemaifes.sistemaifes.repository.LocalRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
public class LocalService {
    private final LocalRepository repository;
    private final EquipmentLocalRepository equipmentLocalRepository;

    public LocalService(LocalRepository repository, EquipmentLocalRepository equipmentLocalRepository){
        this.repository = repository;
        this.equipmentLocalRepository = equipmentLocalRepository;
    }

    public List<LocalResponseDTO> getAll() {
        return repository.findAll().stream().map((LocalResponseDTO::new)).toList();
    }

    public Local findById(@PathVariable @NotNull @Positive Long id){
        return repository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public Local saveLocal(LocalRequestDTO data){
        Local localData = new Local(data);

        List<EquipmentLocal> equipmentLocals = localData.getEquipments();
        EquipmentLocal savedEquipmentLocal = null;
        List<EquipmentLocal> newEquipList = new LinkedList<>();

        if (equipmentLocals != null) {
            for (EquipmentLocal equipmentLocal : equipmentLocals) { 
                savedEquipmentLocal = new EquipmentLocal();
 
                savedEquipmentLocal.setQuantity(equipmentLocal.getQuantity());
                savedEquipmentLocal.setEquipment(equipmentLocal.getEquipment());
                savedEquipmentLocal.setLocal(localData);

                equipmentLocalRepository.save(savedEquipmentLocal); 

                newEquipList.add(savedEquipmentLocal);
            }
        }
        
        localData.setEquipments(newEquipList);

        return repository.save(localData);
    }
    
    public Local update(@NotNull @Positive Long id, @Valid Local local){
        return repository.findById(id)
                .map(recordFound -> {
                    recordFound.setName(local.getName());
                    recordFound.setCapacity(local.getCapacity());
                    return repository.save(recordFound);
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@PathVariable @NotNull Long id){
        repository.delete(repository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException(id)));
    }


} 
