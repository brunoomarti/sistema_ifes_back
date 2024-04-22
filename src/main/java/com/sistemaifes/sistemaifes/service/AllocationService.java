package com.sistemaifes.sistemaifes.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import com.sistemaifes.sistemaifes.dto.request.AllocationRequestDTO;
import com.sistemaifes.sistemaifes.dto.response.AllocationResponseDTO;
import com.sistemaifes.sistemaifes.exception.RecordNotFoundException;
import com.sistemaifes.sistemaifes.model.AllocSchedule;
import com.sistemaifes.sistemaifes.model.Allocation;
import com.sistemaifes.sistemaifes.repository.AllocScheduleRepository;
import com.sistemaifes.sistemaifes.repository.AllocationRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
public class AllocationService {
    private AllocationRepository repository;
    private final AllocScheduleRepository allocScheduleRepository;


    public AllocationService(AllocationRepository repository, AllocScheduleRepository allocScheduleRepository){
        this.repository = repository;
        this.allocScheduleRepository = allocScheduleRepository;
    }

        public List<AllocationResponseDTO> getAll() {
        return repository.findAll().stream().map((AllocationResponseDTO::new)).toList();
    }

    public Allocation findById(@PathVariable @NotNull @Positive Long id){
        return repository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public Allocation saveAllocation(AllocationRequestDTO data){
        Allocation allocationData = new Allocation(data);

        List<AllocSchedule> allocSchedules = allocationData.getSelectedTimes();
        AllocSchedule savedAllocSchedule = null;
        List<AllocSchedule> newAllocList = new LinkedList<>();

        if (allocSchedules != null) {
            for (AllocSchedule allocSchedule : allocSchedules) { 
                savedAllocSchedule = new AllocSchedule();
 
                savedAllocSchedule.setSchedule(allocSchedule.getSchedule());
                savedAllocSchedule.setAllocation(allocationData);

                allocScheduleRepository.save(savedAllocSchedule); 

                newAllocList.add(savedAllocSchedule);
            }
        }
        
        allocationData.setSelectedTimes(newAllocList);

        return repository.save(allocationData);
    }
  
    public Allocation update(@NotNull @Positive Long id, @Valid Allocation allocation){
        return repository.findById(id)
                .map(recordFound -> {
                    recordFound.setStartDate(allocation.getStartDate());
                    recordFound.setEndDate(allocation.getEndDate());
                    recordFound.setType(allocation.getType());
                    return repository.save(recordFound);
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@PathVariable @NotNull Long id){
        repository.delete(repository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
