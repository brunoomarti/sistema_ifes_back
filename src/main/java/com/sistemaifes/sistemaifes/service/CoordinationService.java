package com.sistemaifes.sistemaifes.service;

import java.util.List;

import com.sistemaifes.sistemaifes.exception.InvalidLengthException;
import com.sistemaifes.sistemaifes.exception.ItemAlreadyRegisteredException;
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

        if (cData.getName().length() > 100 || cData.getName().length() < 3) {
            throw new InvalidLengthException("O nome da classe deve ter no máximo 100 caracteres e no minimo 3 caracteres");
        }

        if (cData.getAcronym().length() > 100 || cData.getAcronym().length() < 3) {
            throw new InvalidLengthException("A sigla da classe deve ter no máximo 10 caracteres e no minimo 1 caractere");
        }

        if (cData.getDescription().length() > 255 || cData.getDescription().length() < 3) {
            throw new InvalidLengthException("A descrição da classe  deve ter no máximo 255 caracteres e no minimo 3 caracteres");
        }

        if (verifyIfCoordinationExist(data.name())){
            throw new ItemAlreadyRegisteredException(data.name());
        }

        return repository.save(cData);
    }

    public boolean verifyIfCoordinationExist(String coordinationName) {
        return repository.existsByNameIgnoreCase(coordinationName);
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
