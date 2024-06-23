package com.sistemaifes.sistemaifes.controller;

import java.util.List;

import com.sistemaifes.sistemaifes.dto.response.StudentLessonScheduleResponseDTO;
import com.sistemaifes.sistemaifes.model.NextLessonTeacher;
import com.sistemaifes.sistemaifes.model.StudentLessonSchedule;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.sistemaifes.sistemaifes.dto.request.LessonRequestDTO;
import com.sistemaifes.sistemaifes.dto.response.LessonResponseDTO;
import com.sistemaifes.sistemaifes.model.Lesson;
import com.sistemaifes.sistemaifes.service.LessonService;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("api/lesson")
public class LessonController {
    private final LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping
    public @ResponseBody List<LessonResponseDTO> getAll(){
        return lessonService.getAll();
    }
    
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Lesson saveLesson(@RequestBody LessonRequestDTO data){
        return lessonService.saveLesson(data);
    }

    @GetMapping("/{id}")
    public Lesson findById(@PathVariable @NotNull @Positive Long id){
        return lessonService.findById(id);
    }

    @PutMapping("/{id}")
    public Lesson update(@PathVariable @NotNull @Positive Long id, @RequestBody Lesson lesson){
        return lessonService.update(id, lesson);
    }

    @PostMapping("/delete-multiple")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteMultiple(@RequestBody List<@NotNull @Positive Long> ids) {
        lessonService.deleteMultiple(ids);
    }

    @DeleteMapping("/removeStudents/{lessonId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void removeStudentFromLesson(@PathVariable @NotNull @Positive Long lessonId, @RequestBody List<@NotNull @Positive Long> studentIds) {
        lessonService.removeStudentFromLesson(studentIds, lessonId);
    }

    @GetMapping("/getLessons/{studentCode}/{semesterId}")
    public List<LessonResponseDTO> findLessonsByStudentCodeAndSemesterId(
            @PathVariable String studentCode,
            @PathVariable Long semesterId
    ){
        return lessonService.findLessonsByStudentCodeAndSemesterId(studentCode, semesterId);
    }

    @GetMapping("/getLessonsT/{teacherCode}/{semesterId}")
    public List<LessonResponseDTO> findLessonsByTeacherCodeAndSemesterId(
            @PathVariable String teacherCode,
            @PathVariable Long semesterId
    ){
        return lessonService.findLessonsByTeacherCodeAndSemesterId(teacherCode, semesterId);
    }

    @GetMapping("/getNextLesson/s/{studentCode}")
    public StudentLessonSchedule findNextLessonByStudentCode(@PathVariable String studentCode){
        return lessonService.findNextLessonByStudentCode(studentCode);
    }

    @GetMapping("/getNextLesson/t/{teacherCode}")
    public NextLessonTeacher findNextLessonByTeacherCode(@PathVariable String teacherCode){
        return lessonService.findNextLessonByTeacherCode(teacherCode);
    }
}
