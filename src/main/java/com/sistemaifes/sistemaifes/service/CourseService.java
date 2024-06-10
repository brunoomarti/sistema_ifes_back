package com.sistemaifes.sistemaifes.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.sistemaifes.sistemaifes.exception.InvalidLengthException;
import com.sistemaifes.sistemaifes.exception.ItemAlreadyRegisteredException;
import com.sistemaifes.sistemaifes.model.*;
import com.sistemaifes.sistemaifes.repository.DisciplineRepository;
import com.sistemaifes.sistemaifes.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.sistemaifes.sistemaifes.dto.request.CourseRequestDTO;
import com.sistemaifes.sistemaifes.dto.response.CourseResponseDTO;
import com.sistemaifes.sistemaifes.exception.RecordNotFoundException;
import com.sistemaifes.sistemaifes.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
public class CourseService {
    private final CourseRepository repository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private DisciplineRepository disciplineRepository;

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

        if (cordData.getName().length() > 100 || cordData.getName().length() < 3) {
            throw new InvalidLengthException("O nome do curso deve ter no máximo 100 caracteres e no minimo 3 caracteres");
        }

        if (verifyIfCourseExist(data.name())){
            throw new ItemAlreadyRegisteredException(data.name());
        }

        return repository.save(cordData);
    }

    public boolean verifyIfCourseExist(String equipmentName) {
        return repository.existsByNameIgnoreCase(equipmentName);
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

    public List<Object> getRecordsCourse(Long courseId) {
        List<User> students = studentRepository.findByCourseId(courseId);
        List<Discipline> teachers = disciplineRepository.findByCourseId(courseId);

        return Stream.concat(students.stream(), teachers.stream())
                .collect(Collectors.toList());
    }
 
}
