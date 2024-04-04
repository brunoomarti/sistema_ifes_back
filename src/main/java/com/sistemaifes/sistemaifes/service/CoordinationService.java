package com.sistemaifes.sistemaifes.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
 
import com.sistemaifes.sistemaifes.dto.request.CoordinationRequestDTO;
import com.sistemaifes.sistemaifes.dto.response.CoordinationResponseDTO; 
import com.sistemaifes.sistemaifes.exception.RecordNotFoundException; 
import com.sistemaifes.sistemaifes.model.Coordination;
import com.sistemaifes.sistemaifes.repository.CoordinationRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
public class CoordinationService {
    private CoordinationRepository repository;

    public CoordinationService(CoordinationRepository repository){
        this.repository = repository;
    }

        public List<CoordinationResponseDTO> getAll() {
        return repository.findAll().stream().map((CoordinationResponseDTO::new)).toList();
    }

    public Coordination findById(@PathVariable @NotNull @Positive Long id){
        return repository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public Coordination saveCoordination(CoordinationRequestDTO data){
        Coordination cData = new Coordination(data);
        return repository.save(cData);
    }
  
    public Coordination update(@NotNull @Positive Long id, @Valid Coordination coordination){
        return repository.findById(id)
                .map(recordFound -> {
                    recordFound.setName(coordination.getName());
                    recordFound.setAcronym(coordination.getAcronym());
                    recordFound.setDescription(coordination.getDescription());
                    return repository.save(recordFound);
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@PathVariable @NotNull Long id){
        repository.delete(repository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException(id)));
    }
 
}
