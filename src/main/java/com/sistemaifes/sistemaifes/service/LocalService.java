package com.sistemaifes.sistemaifes.service;

import java.util.LinkedList;
import java.util.List;

import com.sistemaifes.sistemaifes.exception.InvalidLengthException;
import com.sistemaifes.sistemaifes.exception.ItemAlreadyRegisteredException;
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

        if (localData.getName().length() > 100 || localData.getName().length() < 3) {
            throw new InvalidLengthException("O nome do local deve ter no máximo 100 caracteres e no minimo 3 caracteres");
        }

        if (verifyIfEquipmentExist(data.name())){
            throw new ItemAlreadyRegisteredException(data.name());
        }

        if (equipmentLocals != null) {
            for (EquipmentLocal equipmentLocal : equipmentLocals) { 
                savedEquipmentLocal = new EquipmentLocal();
 
                savedEquipmentLocal.setQuantity(equipmentLocal.getQuantity());
                savedEquipmentLocal.setEquipment(equipmentLocal.getEquipment());
                savedEquipmentLocal.setLocation(localData);

                equipmentLocalRepository.save(savedEquipmentLocal); 

                newEquipList.add(savedEquipmentLocal);
            }
        }
        
        localData.setEquipments(newEquipList);

        return repository.save(localData);
    }

    public boolean verifyIfEquipmentExist(String equipmentName) {
        return repository.existsByNameIgnoreCase(equipmentName);
    }
    
    public Local update(@NotNull @Positive Long id, @Valid Local local){
        List<EquipmentLocal> equipmentLocals = local.getEquipments();
        List<EquipmentLocal> newEquipList = new LinkedList<>();
        EquipmentLocal savedEquipmentLocal = null;

        if (equipmentLocals != null){
            for (EquipmentLocal equipmentLocal : equipmentLocals) { 
                savedEquipmentLocal = new EquipmentLocal();

                savedEquipmentLocal.setQuantity(equipmentLocal.getQuantity());
                savedEquipmentLocal.setEquipment(equipmentLocal.getEquipment());
                savedEquipmentLocal.setLocation(local);

                equipmentLocalRepository.save(savedEquipmentLocal); 

                newEquipList.add(savedEquipmentLocal);
            }
        }

        return repository.findById(id).map(recordFound -> {
                    recordFound.setName(local.getName());
                    recordFound.setCapacity(local.getCapacity());
                    recordFound.setEquipments(newEquipList);
                    return repository.save(recordFound);
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@PathVariable @NotNull Long id){
        repository.delete(repository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException(id)));
    }

    public List<Local> getAllUnallocatedLocationst() {
        return repository.findAllUnallocatedLocations().stream().toList();

    }


} 
