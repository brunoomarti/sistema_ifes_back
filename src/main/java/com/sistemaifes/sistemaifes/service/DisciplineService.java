package com.sistemaifes.sistemaifes.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.sistemaifes.sistemaifes.dto.request.DisciplineRequestDTO;
import com.sistemaifes.sistemaifes.dto.response.DisciplineResponseDTO;
import com.sistemaifes.sistemaifes.exception.RecordNotFoundException;
import com.sistemaifes.sistemaifes.model.Discipline;
import com.sistemaifes.sistemaifes.repository.DisciplineRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
public class DisciplineService {
    private DisciplineRepository repository;

    public DisciplineService(DisciplineRepository repository){
        this.repository = repository;
    }

        public List<DisciplineResponseDTO> getAll() {
        return repository.findAll().stream().map((DisciplineResponseDTO::new)).toList();
    }

    public Discipline findById(@PathVariable @NotNull @Positive Long id){
        return repository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public Discipline saveDiscipline(DisciplineRequestDTO data){
        Discipline eqData = new Discipline(data);
        
        return repository.save(eqData);
    }

    public Discipline update(@NotNull @Positive Long id, @Valid Discipline discipline){
        return repository.findById(id)
                .map(recordFound -> {
                    recordFound.setName(discipline.getName());
                    recordFound.setAcronym(discipline.getAcronym());
                    return repository.save(recordFound);
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@PathVariable @NotNull Long id){
        repository.delete(repository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException(id)));
    }

}
