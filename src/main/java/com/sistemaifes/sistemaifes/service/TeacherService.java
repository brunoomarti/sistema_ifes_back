package com.sistemaifes.sistemaifes.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.sistemaifes.sistemaifes.dto.request.TeacherRequestDTO;
import com.sistemaifes.sistemaifes.dto.response.TeacherResponseDTO;
import com.sistemaifes.sistemaifes.exception.RecordNotFoundException;
import com.sistemaifes.sistemaifes.model.Teacher;
import com.sistemaifes.sistemaifes.repository.TeacherRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
public class TeacherService {
    private final TeacherRepository repository;
    public TeacherService(TeacherRepository repository){
        this.repository = repository;
    }

    public List<TeacherResponseDTO> getAll() {
        return repository.findAll().stream().map((TeacherResponseDTO::new)).toList();
    }

    public Teacher findById(@PathVariable @NotNull @Positive Long id){
        return repository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public Teacher saveTeacher(TeacherRequestDTO data){
        Teacher dataTeacher = new Teacher(data);
        dataTeacher.setEstahAtivo(true);
        return repository.save(dataTeacher);
    }
    
    public Teacher update(@NotNull @Positive Long id, @Valid Teacher teacher){
        return repository.findById(id)
                .map(recordFound -> {
                    recordFound.setName(teacher.getName());
                    recordFound.setTeacherCode(teacher.getTeacherCode());
                    recordFound.setCoordinator(teacher.isCoordinator());
                    recordFound.setSpecialty(teacher.getSpecialty());
                    recordFound.setEducationLevel(teacher.getEducationLevel());
                    recordFound.setSituation(teacher.isSituation());
                    recordFound.setCoordination(teacher.getCoordination());
                    return repository.save(recordFound);
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@PathVariable @NotNull Long id){
        repository.delete(repository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
