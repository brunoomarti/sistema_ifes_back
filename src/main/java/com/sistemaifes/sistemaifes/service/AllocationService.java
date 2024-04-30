package com.sistemaifes.sistemaifes.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import com.sistemaifes.sistemaifes.dto.request.AllocationRequestDTO;
import com.sistemaifes.sistemaifes.dto.response.AllocationResponseDTO;
import com.sistemaifes.sistemaifes.exception.RecordNotFoundException;
import com.sistemaifes.sistemaifes.model.Allocation;
import com.sistemaifes.sistemaifes.model.Local;
import com.sistemaifes.sistemaifes.repository.AllocationRepository;
import com.sistemaifes.sistemaifes.repository.LocalRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
public class AllocationService {
    private AllocationRepository repository;
    private LocalRepository localRepository;

    public AllocationService(AllocationRepository repository, LocalRepository localRepository){
        this.repository = repository;
        this.localRepository = localRepository;
    }

        public List<AllocationResponseDTO> getAll() {
        return repository.findAll().stream().map((AllocationResponseDTO::new)).toList();
    }

    public Allocation findById(@PathVariable @NotNull @Positive Long id){
        return repository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public Allocation saveAllocation(AllocationRequestDTO data){
        Allocation allocationData = new Allocation(data);
        // Local localData = allocationData.getLocation();
        // localData.setAllocated(true);

        // localRepository.save(localData);

        return repository.save(allocationData);
    }
  
    public Allocation update(@NotNull @Positive Long id, @Valid Allocation allocation){
        return repository.findById(id)
                .map(recordFound -> {
                    recordFound.setStartDate(allocation.getStartDate());
                    recordFound.setEndDate(allocation.getEndDate());
                    recordFound.setStartTime(allocation.getStartTime());
                    recordFound.setEndTime(allocation.getEndTime());
                    recordFound.setWeekDay(allocation.getWeekDay());
                    recordFound.setType(allocation.getType());
                    recordFound.setApplicant(allocation.getApplicant());
                    recordFound.setClasse(allocation.getClasse());
                    recordFound.setLocation(allocation.getLocation());
                    recordFound.setSelectedTimes(allocation.getSelectedTimes());
                    return repository.save(recordFound);
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@PathVariable @NotNull Long id){
        repository.delete(repository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
