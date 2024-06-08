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
 
import com.sistemaifes.sistemaifes.dto.request.SemesterRequestDTO; 
import com.sistemaifes.sistemaifes.dto.response.SemesterResponseDTO; 
import com.sistemaifes.sistemaifes.model.Semester; 
import com.sistemaifes.sistemaifes.service.SemesterService;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("api/semester")
public class SemesterController {
        private final SemesterService semesterService;

    public SemesterController(SemesterService semesterService) {
        this.semesterService = semesterService;
    }

    @GetMapping
    public @ResponseBody List<SemesterResponseDTO> getAll(){
        return semesterService.getAll();
    }
    
    @PostMapping("/new")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Semester saveSchedule(@RequestBody SemesterRequestDTO data){ 
        return semesterService.saveSemester(data);
    }

    @GetMapping("/{id}")
    public Semester findById(@PathVariable @NotNull @Positive Long id){
        return semesterService.findById(id);
    }

    @PutMapping("/edit/{id}")
    public Semester update(@PathVariable @NotNull @Positive Long id, @RequestBody Semester semester){
        return semesterService.update(id, semester);
    }

    @GetMapping("/{id}/records")
    public List<Object> getCourseRecords(@PathVariable @NotNull @Positive Long id) {
        return semesterService.getRecordsLesson(id);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id){
        semesterService.delete(id);
    }
}
