package com.sistemaifes.sistemaifes.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.sistemaifes.sistemaifes.dto.request.LessonRequestDTO;
import com.sistemaifes.sistemaifes.dto.response.LessonResponseDTO;
import com.sistemaifes.sistemaifes.exception.RecordNotFoundException;
import com.sistemaifes.sistemaifes.model.Lesson;
import com.sistemaifes.sistemaifes.model.Student;
import com.sistemaifes.sistemaifes.repository.LessonRepository;
import com.sistemaifes.sistemaifes.repository.StudentRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
public class LessonService {
    private final LessonRepository repository;
    private final StudentRepository studentRepository;

    public LessonService(LessonRepository repository, StudentRepository studentRepository){
        this.repository = repository;
        this.studentRepository = studentRepository;
    }

    public List<LessonResponseDTO> getAll() {
        return repository.findAll().stream().map((LessonResponseDTO::new)).toList();
    }

    public Lesson findById(@PathVariable @NotNull @Positive Long id){
        return repository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public Lesson saveEvent(LessonRequestDTO data){
        Lesson localData = new Lesson(data);
        return repository.save(localData);
    }

    public Lesson update(@NotNull @Positive Long id, @Valid Lesson lesson){
        return repository.findById(id)
                .map(recordFound -> {
                    recordFound.setDiscipline(lesson.getDiscipline());
                    recordFound.setSemester(lesson.getSemester());
                    recordFound.setStudents(lesson.getStudents());
                    recordFound.setTeacher(lesson.getTeacher());
                    recordFound.setAllocated(lesson.getAllocated());
                    return repository.save(recordFound);
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@PathVariable @NotNull Long id){
        repository.delete(repository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException(id)));
    }

    public void removeStudentFromLesson(Long studentId, Long lessonId) {
        Student student =  studentRepository.findById(studentId)
                                    .orElseThrow(() -> new RecordNotFoundException(studentId));

        Lesson lesson = repository.findById(lessonId)
                        .orElseThrow(() -> new RecordNotFoundException(lessonId));                         
        
        repository.removeStudentFromLesson(studentId, lessonId);
        
    }
}
