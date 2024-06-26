package com.sistemaifes.sistemaifes.service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.sistemaifes.sistemaifes.exception.InvalidLengthException;
import com.sistemaifes.sistemaifes.exception.ItemAlreadyRegisteredException;
import com.sistemaifes.sistemaifes.model.Allocation;
import com.sistemaifes.sistemaifes.model.Lesson;
import com.sistemaifes.sistemaifes.repository.AllocationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private final EquipmentLocalRepository equipmentLocalRepository;

    @Autowired
    private AllocationRepository allocationRepository;

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

    @Transactional
    public Local update(@NotNull @Positive Long id, @Valid Local local) {
        Local managedLocal = repository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));

        managedLocal.setName(local.getName());
        managedLocal.setCapacity(local.getCapacity());

        equipmentLocalRepository.deleteByLocation(managedLocal);

        List<EquipmentLocal> equipmentLocals = local.getEquipments();
        List<EquipmentLocal> newEquipList = new LinkedList<>();

        if (equipmentLocals != null) {
            for (EquipmentLocal equipmentLocal : equipmentLocals) {
                EquipmentLocal savedEquipmentLocal = new EquipmentLocal();
                savedEquipmentLocal.setQuantity(equipmentLocal.getQuantity());
                savedEquipmentLocal.setEquipment(equipmentLocal.getEquipment());
                savedEquipmentLocal.setLocation(managedLocal);

                equipmentLocalRepository.save(savedEquipmentLocal);

                newEquipList.add(savedEquipmentLocal);
            }
        }

        managedLocal.setEquipments(newEquipList);

        return repository.save(managedLocal);
    }

    public void delete(@PathVariable @NotNull Long id){
        repository.delete(repository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException(id)));
    }

    public List<Local> getAllUnallocatedLocationst() {
        return repository.findAllUnallocatedLocations().stream().toList();

    }

    public List<Object> getRecordsAllocation(Long courseId) {
        List<Allocation> lessons = allocationRepository.findByLocalId(courseId);

        return lessons.stream().collect(Collectors.toList());
    }


} 
