package com.sistemaifes.sistemaifes.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
    
    @PostMapping("/new")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Lesson saveEvent(@RequestBody LessonRequestDTO data){ 
        return lessonService.saveEvent(data);
    }

    @GetMapping("/{id}")
    public Lesson findById(@PathVariable @NotNull @Positive Long id){
        return lessonService.findById(id);
    }

    @PutMapping("/edit/{id}")
    public Lesson update(@PathVariable @NotNull @Positive Long id, @RequestBody Lesson lesson){
        return lessonService.update(id, lesson);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id){
        lessonService.delete(id);
    }
}
