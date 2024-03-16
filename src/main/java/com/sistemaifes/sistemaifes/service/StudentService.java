package com.sistemaifes.sistemaifes.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.sistemaifes.sistemaifes.dto.request.StudentRequestDTO;
import com.sistemaifes.sistemaifes.dto.response.StudentResponseDTO;
import com.sistemaifes.sistemaifes.exception.RecordNotFoundException;
import com.sistemaifes.sistemaifes.model.Student;
import com.sistemaifes.sistemaifes.repository.StudentRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository){
        this.repository = repository;
    }

    public List<StudentResponseDTO> getAll() {
        return repository.findAll().stream().map((StudentResponseDTO::new)).toList();
    }

    public Student findById(@PathVariable @NotNull @Positive Long id){
        return repository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public Student saveStudent(StudentRequestDTO data){
        Student eqData = new Student(data);
        return repository.save(eqData);
    }
    
    public Student update(@NotNull @Positive Long id, @Valid Student student){
        return repository.findById(id)
                .map(recordFound -> {
                    recordFound.setName(student.getName());
                    recordFound.setStudentCode(student.getStudentCode());
                    recordFound.setPerformanceCoefficient(student.getPerformanceCoefficient());
                    recordFound.setSituation(student.isSituation());
                    recordFound.setPeriod(student.getPeriod());
                    return repository.save(recordFound);
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@PathVariable @NotNull Long id){
        repository.delete(repository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
