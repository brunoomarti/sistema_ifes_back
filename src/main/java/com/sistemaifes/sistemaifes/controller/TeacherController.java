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

import com.sistemaifes.sistemaifes.dto.request.TeacherRequestDTO;
import com.sistemaifes.sistemaifes.dto.response.TeacherResponseDTO;
import com.sistemaifes.sistemaifes.model.Teacher;
import com.sistemaifes.sistemaifes.service.TeacherService;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("api/teacher")
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public @ResponseBody List<TeacherResponseDTO> getAll(){
        return teacherService.getAll();
    }
    
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Teacher saveTeacher(@RequestBody TeacherRequestDTO data){ 
        return teacherService.saveTeacher(data);
    }

    @GetMapping("/{id}")
    public Teacher findById(@PathVariable @NotNull @Positive Long id){
        return teacherService.findById(id);
    }

    @GetMapping("/idByCode/{teacherCode}")
    public Teacher idByCode(@PathVariable @NotNull @Positive String teacherCode){
        return teacherService.idByCode(teacherCode);
    }

    @PutMapping("/{id}")
    public Teacher update(@PathVariable @NotNull @Positive Long id, @RequestBody Teacher teacher){
        return teacherService.update(id, teacher);
    }

    @GetMapping("/{id}/records")
    public List<Object> getRecordsStudent(@PathVariable @NotNull @Positive Long id) {
        return teacherService.getRecordsTeacher(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id){
        teacherService.delete(id);
    }
}
