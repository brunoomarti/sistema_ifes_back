package com.sistemaifes.sistemaifes.service;

import java.util.List;
import java.util.stream.Collectors;

import com.sistemaifes.sistemaifes.controller.AuthenticationController;
import com.sistemaifes.sistemaifes.dto.request.UserRequestDTO;
import com.sistemaifes.sistemaifes.model.*;
import com.sistemaifes.sistemaifes.repository.LessonRepository;
import com.sistemaifes.sistemaifes.repository.UserRepository;
import com.sistemaifes.sistemaifes.util.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.sistemaifes.sistemaifes.dto.request.StudentRequestDTO;
import com.sistemaifes.sistemaifes.dto.response.StudentResponseDTO;
import com.sistemaifes.sistemaifes.exception.RecordNotFoundException;
import com.sistemaifes.sistemaifes.repository.StudentRepository;
import com.sistemaifes.sistemaifes.repository.StudentScheduleRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
public class StudentService {
    private final StudentRepository repository;
    private final StudentScheduleRepository studentScheduleRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationController authenticationController;

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

        dataStudent.setLogin(data.studentCode());
        dataStudent.setPassword("123456!");
        dataStudent.setRole(UserRole.STUDENT);

        registerStudent(dataStudent);

        return dataStudent;
    }

    public ResponseEntity registerStudent(Student s){

        if(this.userRepository.findByLogin(s.getLogin()) != null) return ResponseEntity.badRequest().build();

        if (!authenticationController.validate(s.getPassword())) {
            return ResponseEntity.badRequest().body("A senha deve conter pelo menos um caractere especial e seis caracteres alfanuméricos.");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(s.getPassword());

        s.setEstahAtivo(true);
        s.setPassword(encryptedPassword);

        this.repository.save(s);

        return ResponseEntity.ok().build();
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

    @Transactional
    public void deleteMultiple(List<@NotNull Long> ids) {
        ids.forEach(id -> {
            repository.delete(repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id)));
        });
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
