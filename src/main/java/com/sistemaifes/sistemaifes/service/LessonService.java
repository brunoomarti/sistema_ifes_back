package com.sistemaifes.sistemaifes.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.sistemaifes.sistemaifes.dto.response.StudentLessonScheduleResponseDTO;
import com.sistemaifes.sistemaifes.model.*;
import com.sistemaifes.sistemaifes.repository.NextTeacherLessonRepository;
import com.sistemaifes.sistemaifes.repository.StudentLessonScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.sistemaifes.sistemaifes.dto.request.LessonRequestDTO;
import com.sistemaifes.sistemaifes.dto.response.LessonResponseDTO;
import com.sistemaifes.sistemaifes.exception.RecordNotFoundException;
import com.sistemaifes.sistemaifes.repository.LessonRepository;
import com.sistemaifes.sistemaifes.repository.StudentRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
public class LessonService {
    private final LessonRepository repository;
    private final StudentRepository studentRepository;
    private final StudentLessonScheduleRepository studentLessonShedule;

    @Autowired
    private  NextTeacherLessonRepository nextTeacherLessonRepository;

    public LessonService(LessonRepository repository,
                         StudentRepository studentRepository,
                         StudentLessonScheduleRepository studentLessonShedule
    ){
        this.repository = repository;
        this.studentRepository = studentRepository;
        this.studentLessonShedule = studentLessonShedule;
    }

    public List<LessonResponseDTO> getAll() {
        return repository.findAll().stream().map((LessonResponseDTO::new)).toList();
    }

    public Lesson findById(@PathVariable @NotNull @Positive Long id){
        return repository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public Lesson saveLesson(LessonRequestDTO data){
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
                    recordFound.setWeeklyQuantity(lesson.getWeeklyQuantity());
                    recordFound.setAllocated(lesson.getAllocated());
                    return repository.save(recordFound);
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@PathVariable @NotNull Long id){
        removeAllStudentFromLesson(id);
        repository.delete(repository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException(id)));
    }

    @Transactional
    public void deleteMultiple(List<Long> ids) {
        ids.forEach(this::delete);
    }

    @Transactional
    public void removeStudentFromLesson(List<Long> studentIds, Long lessonId) {
        Lesson lesson = repository.findById(lessonId)
                .orElseThrow(() -> new RecordNotFoundException(lessonId));

        for (Long studentId : studentIds) {
            Student student = studentRepository.findById(studentId)
                    .orElseThrow(() -> new RecordNotFoundException(studentId));

            repository.removeStudentFromLesson(studentId, lessonId);
        }
    }

    public void removeAllStudentFromLesson(Long lessonId){
        repository.removeAllStudentFromLesson(lessonId);
    }

    public List<LessonResponseDTO> findLessonsByStudentCodeAndSemesterId(String studentCode, Long semesterId) {
        return repository.findLessonsByStudentCodeAndSemesterId(studentCode , semesterId)
                .stream().map((LessonResponseDTO::new)).toList();
    }

    public StudentLessonSchedule findNextLessonByStudentCode(String studentCode) {
        return studentLessonShedule.findNextLessonByStudentCode(studentCode);
    }

    public NextLessonTeacher findNextLessonByTeacherCode(String teacherCode){
        return nextTeacherLessonRepository.findNextLessonByTeacherCode(teacherCode);
    }

    public List<LessonResponseDTO> findLessonsByTeacherCodeAndSemesterId(String teacherCode, Long semesterId) {
        return repository.findLessonsByTeacherCodeAndSemesterId(teacherCode , semesterId)
                .stream().map((LessonResponseDTO::new)).toList();
    }
}
