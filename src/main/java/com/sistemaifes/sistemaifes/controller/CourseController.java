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

import com.sistemaifes.sistemaifes.dto.request.AllocationRequestDTO;
import com.sistemaifes.sistemaifes.dto.request.CourseRequestDTO;
import com.sistemaifes.sistemaifes.dto.response.AllocationResponseDTO;
import com.sistemaifes.sistemaifes.dto.response.CourseResponseDTO;
import com.sistemaifes.sistemaifes.model.Allocation;
import com.sistemaifes.sistemaifes.model.Course;
import com.sistemaifes.sistemaifes.service.CourseService;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("api/course")
public class CourseController {

    private CourseService courseService;

    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }

    @GetMapping
    public @ResponseBody List<CourseResponseDTO> getAll(){
        return courseService.getAll();
    }

    @PostMapping("/new")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Course saveCourse(@RequestBody CourseRequestDTO data){
        return courseService.saveCourse(data);
    }

    @GetMapping("/{id}")
    public Course findById(@PathVariable @NotNull @Positive Long id){
        return courseService.findById(id);
    }

    @PutMapping("/edit/{id}")
    public Course update(@PathVariable @NotNull @Positive Long id, @RequestBody Course course){
        return courseService.update(id, course);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id){
        courseService.delete(id);
    }
    
}
