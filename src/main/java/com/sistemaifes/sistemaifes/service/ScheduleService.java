package com.sistemaifes.sistemaifes.service;
 
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.sistemaifes.sistemaifes.dto.request.ScheduleRequestDTO;
import com.sistemaifes.sistemaifes.dto.response.ScheduleResponseDTO;
import com.sistemaifes.sistemaifes.exception.RecordNotFoundException;
import com.sistemaifes.sistemaifes.model.Schedule; 
import com.sistemaifes.sistemaifes.repository.ScheduleRepository; 

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
public class ScheduleService {
    private final ScheduleRepository repository;

    public ScheduleService(ScheduleRepository repository){
        this.repository = repository;
    }

    public List<ScheduleResponseDTO> getAll() {
        return repository.findAll().stream().map((ScheduleResponseDTO::new)).toList();
    }

    public Schedule findById(@PathVariable @NotNull @Positive Long id){
        return repository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public Schedule saveSchedule(ScheduleRequestDTO data){
        System.out.println(data.startTime());
        System.out.println(data.endTime());
        
        Schedule eqData = new Schedule(data);
        
        
        return repository.save(eqData);
    }
    
    public Schedule update(@NotNull @Positive Long id, @Valid Schedule schedule){
        return repository.findById(id)
                .map(recordFound -> {
                    recordFound.setStartTime(schedule.getStartTime());
                    recordFound.setEndTime(schedule.getEndTime());
                    return repository.save(recordFound);
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@PathVariable @NotNull Long id){
        repository.delete(repository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
