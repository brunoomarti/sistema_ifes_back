package com.sistemaifes.sistemaifes.service;

import java.util.List;
import java.util.stream.Collectors;

import com.sistemaifes.sistemaifes.model.Allocation;
import com.sistemaifes.sistemaifes.model.Lesson;
import com.sistemaifes.sistemaifes.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.sistemaifes.sistemaifes.dto.request.StudentRequestDTO;
import com.sistemaifes.sistemaifes.dto.response.StudentResponseDTO;
import com.sistemaifes.sistemaifes.exception.RecordNotFoundException;
import com.sistemaifes.sistemaifes.model.Student;
import com.sistemaifes.sistemaifes.model.StudentSchedule;
import com.sistemaifes.sistemaifes.repository.StudentRepository;
import com.sistemaifes.sistemaifes.repository.StudentScheduleRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
public class StudentService {
    private final StudentRepository repository;
    private final StudentScheduleRepository studentScheduleRepository;

    @Autowired
    private LessonRepository lessonRepository;

    public StudentService(StudentRepository repository, StudentScheduleRepository studentScheduleRepository){
        this.repository = repository;
        this.studentScheduleRepository = studentScheduleRepository;
    }

    public List<StudentResponseDTO> getAll() {
        return repository.findAll().stream().map((StudentResponseDTO::new)).toList();
    }

    public Student findById(@PathVariable @NotNull @Positive Long id){
        return repository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public Student idByCode(@PathVariable @NotNull @Positive String studentCode){
        return repository.idByCode(studentCode);
    }

    public Student saveStudent(StudentRequestDTO data){
        Student dataStudent = new Student(data);
        dataStudent.setEstahAtivo(true);
        return repository.save(dataStudent);
    }
    
    public Student update(@NotNull @Positive Long id, @Valid Student student){
        return repository.findById(id)
                .map(recordFound -> {
                    recordFound.setName(student.getName());
                    recordFound.setStudentCode(student.getStudentCode());
                    recordFound.setRegistrationYear(student.getRegistrationYear());
                    recordFound.setCourse(student.getCourse());
                    return repository.save(recordFound);
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@PathVariable @NotNull Long id){
        repository.delete(repository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException(id)));
    }

    public List<StudentSchedule> getStudentSchedule(String studentCode){
        Student s = repository.findByStudentCode(studentCode);
        return studentScheduleRepository.getStudentSchedule(s.get_id());
    }

    public List<Object> getRecordsStudent(Long studentId) {
        List<Lesson> lessons = lessonRepository.findByStudentId(studentId);

        return lessons.stream().collect(Collectors.toList());
    }
}
