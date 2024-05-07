package com.sistemaifes.sistemaifes.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.sistemaifes.sistemaifes.dto.request.CourseRequestDTO;
import com.sistemaifes.sistemaifes.dto.response.CourseResponseDTO;
import com.sistemaifes.sistemaifes.exception.RecordNotFoundException;
import com.sistemaifes.sistemaifes.model.Course;
import com.sistemaifes.sistemaifes.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
public class CourseService {
    private final CourseRepository repository;

    public CourseService(CourseRepository repository){
        this.repository = repository;
    }

    public List<CourseResponseDTO> getAll() {
        return repository.findAll().stream().map((CourseResponseDTO::new)).toList();
    }

    public Course findById(@PathVariable @NotNull @Positive Long id){
        return repository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public Course saveCourse(CourseRequestDTO data){
        Course cordData = new Course(data);
        return repository.save(cordData);
    }
 

    public Course update(@NotNull @Positive Long id, @Valid Course course){
        return repository.findById(id)
                .map(recordFound -> {
                    recordFound.setName(course.getName()); 
                    return repository.save(recordFound);
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@PathVariable @NotNull Long id){
        repository.delete(repository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException(id)));
    }
 
}
