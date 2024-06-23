package com.sistemaifes.sistemaifes.service;

import java.util.List;
import java.util.stream.Collectors;

import com.sistemaifes.sistemaifes.controller.AuthenticationController;
import com.sistemaifes.sistemaifes.dto.request.NextLessonTeacherRequestDTO;
import com.sistemaifes.sistemaifes.model.Lesson;
import com.sistemaifes.sistemaifes.model.NextLessonTeacher;
import com.sistemaifes.sistemaifes.repository.LessonRepository;
import com.sistemaifes.sistemaifes.repository.NextTeacherLessonRepository;
import com.sistemaifes.sistemaifes.repository.UserRepository;
import com.sistemaifes.sistemaifes.util.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationController authenticationController;

    @Autowired
    private NextTeacherLessonRepository nextTeacherLessonRepository;

    public TeacherService(TeacherRepository repository){
        this.repository = repository;
    }

    public List<TeacherResponseDTO> getAll() {
        return repository.findAll().stream().map((TeacherResponseDTO::new)).toList();
    }

    public Teacher findById(@PathVariable @NotNull @Positive Long id){
        return repository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public Teacher idByCode(@PathVariable @NotNull @Positive String teacherCode){
        return repository.idByCode(teacherCode);
    }

    public Teacher saveTeacher(TeacherRequestDTO data){
        Teacher dataTeacher = new Teacher(data);
        dataTeacher.setEstahAtivo(true);

        dataTeacher.setLogin(data.teacherCode());
        dataTeacher.setPassword("123456!");

        if (dataTeacher.isCoordinator()){
            dataTeacher.setRole(UserRole.COORDINATOR);
        } else {
            dataTeacher.setRole(UserRole.TEACHER);
        }

        registerTeacher(dataTeacher);

        return dataTeacher;
    }

    public ResponseEntity registerTeacher(Teacher t){

        if(this.userRepository.findByLogin(t.getLogin()) != null) return ResponseEntity.badRequest().build();

        if (!authenticationController.validate(t.getPassword())) {
            return ResponseEntity.badRequest().body("A senha deve conter pelo menos um caractere especial e seis caracteres alfanuméricos.");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(t.getPassword());

        t.setEstahAtivo(true);
        t.setPassword(encryptedPassword);

        this.repository.save(t);

        return ResponseEntity.ok().build();
    }
    
    public Teacher update(@NotNull @Positive Long id, @Valid Teacher teacher){
        return repository.findById(id)
                .map(recordFound -> {
                    recordFound.setName(teacher.getName());
                    recordFound.setTeacherCode(teacher.getTeacherCode());
                    recordFound.setEducationLevel(teacher.getEducationLevel());
                    recordFound.setSituation(teacher.isSituation());
                    if (teacher.isCoordinator()){
                        recordFound.setRole(UserRole.COORDINATOR);
                    } else {
                        recordFound.setRole(UserRole.TEACHER);
                    }
                    recordFound.setCoordinator(teacher.isCoordinator());
                    recordFound.setCoordination(teacher.getCoordination());
                    return repository.save(recordFound);
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@PathVariable @NotNull Long id){
        repository.delete(repository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException(id)));
    }

    public List<Object> getRecordsTeacher(Long studentId) {
        List<Lesson> lessons = lessonRepository.findByTeacherId(studentId);

        return lessons.stream().collect(Collectors.toList());
    }
}
