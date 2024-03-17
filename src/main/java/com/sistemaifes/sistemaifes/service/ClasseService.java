package com.sistemaifes.sistemaifes.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.sistemaifes.sistemaifes.dto.request.ClasseRequestDTO;
import com.sistemaifes.sistemaifes.dto.response.ClasseResponseDTO;
import com.sistemaifes.sistemaifes.exception.ItemAlreadyRegisteredException;
import com.sistemaifes.sistemaifes.exception.RecordNotFoundException;
import com.sistemaifes.sistemaifes.model.Classe;
import com.sistemaifes.sistemaifes.repository.ClasseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
public class ClasseService {
    private final ClasseRepository repository;

    public ClasseService(ClasseRepository repository){
        this.repository = repository;
    }

    public List<ClasseResponseDTO> getAll() {
        return repository.findAll().stream().map((ClasseResponseDTO::new)).toList();
    }

    public Classe findById(@PathVariable @NotNull @Positive Long id){
        return repository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public Classe save(ClasseRequestDTO data){
        Classe cData = new Classe(data);
        
        if (verifyIfEquipmentExist(data.name())){
            throw new ItemAlreadyRegisteredException(data.name());
        }

        return repository.save(cData);
    }

    public boolean verifyIfEquipmentExist(String classeName) {
        return repository.existsByNameIgnoreCase(classeName);
    }
    
    public Classe update(@NotNull @Positive Long id, @Valid Classe classe){
        return repository.findById(id)
                .map(recordFound -> {
                    recordFound.setName(classe.getName());
                    return repository.save(recordFound);
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@PathVariable @NotNull Long id){
        repository.delete(repository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException(id)));
    }

    public List<Classe> findEquipmentByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }
}
