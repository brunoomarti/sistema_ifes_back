package com.sistemaifes.sistemaifes.service;

import java.util.List;
import java.util.stream.Collectors;

import com.sistemaifes.sistemaifes.exception.ItemAlreadyRegisteredException;
import com.sistemaifes.sistemaifes.model.Allocation;
import com.sistemaifes.sistemaifes.model.Lesson;
import com.sistemaifes.sistemaifes.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private LessonRepository lessonRepository;

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

        if (verifyIfEquipmentExist(data.name())){
            throw new ItemAlreadyRegisteredException(data.name());
        }
        
        return repository.save(eqData);
    }

    public boolean verifyIfEquipmentExist(String equipmentName) {
        return repository.existsByNameIgnoreCase(equipmentName);
    }

    public Discipline update(@NotNull @Positive Long id, @Valid Discipline discipline){
        return repository.findById(id)
                .map(recordFound -> {
                    recordFound.setName(discipline.getName());
                    recordFound.setAcronym(discipline.getAcronym());
                    recordFound.setCourse(discipline.getCourse());
                    return repository.save(recordFound);
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@PathVariable @NotNull Long id){
        repository.delete(repository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException(id)));
    }

    public List<Object> getRecordsDiscipline(Long disciplineId) {
        List<Lesson> lessons = lessonRepository.findByDisciplineId(disciplineId);

        return lessons.stream().collect(Collectors.toList());
    }

}
