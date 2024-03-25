package com.sistemaifes.sistemaifes.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
 
import com.sistemaifes.sistemaifes.dto.request.SemesterRequestDTO; 
import com.sistemaifes.sistemaifes.dto.response.SemesterResponseDTO;
import com.sistemaifes.sistemaifes.exception.RecordNotFoundException; 
import com.sistemaifes.sistemaifes.model.Semester; 
import com.sistemaifes.sistemaifes.repository.SemesterRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
public class SemesterService {
    private final SemesterRepository repository;

    public SemesterService(SemesterRepository repository){
        this.repository = repository;
    }

    public List<SemesterResponseDTO> getAll() {
        return repository.findAll().stream().map((SemesterResponseDTO::new)).toList();
    }

    public Semester findById(@PathVariable @NotNull @Positive Long id){
        return repository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public Semester saveSemester(SemesterRequestDTO data){
        Semester eqData = new Semester(data);
        
        
        return repository.save(eqData);
    }
    
    public Semester update(@NotNull @Positive Long id, @Valid Semester semester){
        return repository.findById(id)
                .map(recordFound -> {
                    recordFound.setSemester(semester.getSemester());
                    recordFound.setStartDate(semester.getStartDate());
                    recordFound.setEndDate(semester.getEndDate());
                    return repository.save(recordFound);
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@PathVariable @NotNull Long id){
        repository.delete(repository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
