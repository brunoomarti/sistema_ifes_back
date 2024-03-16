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

import com.sistemaifes.sistemaifes.dto.request.DisciplineRequestDTO;
import com.sistemaifes.sistemaifes.dto.request.EquipmentRequestDTO;
import com.sistemaifes.sistemaifes.dto.response.DisciplineResponseDTO;
import com.sistemaifes.sistemaifes.dto.response.EquipmentResponseDTO;
import com.sistemaifes.sistemaifes.model.Discipline;
import com.sistemaifes.sistemaifes.model.Equipment;
import com.sistemaifes.sistemaifes.repository.DisciplineRepository;
import com.sistemaifes.sistemaifes.service.DisciplineService;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("api/discipline")
public class DisciplineController {
    private DisciplineService disciplineService;

    public DisciplineController(DisciplineService disciplineService){
        this.disciplineService = disciplineService;
    }

    @GetMapping
    public @ResponseBody List<DisciplineResponseDTO> getAll(){
        return disciplineService.getAll();
    }
    
    @PostMapping("/new")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Discipline saveDiscipline(@RequestBody DisciplineRequestDTO data){
        return disciplineService.saveDiscipline(data);
    }

    @GetMapping("/{id}")
    public Discipline findById(@PathVariable @NotNull @Positive Long id){
        return disciplineService.findById(id);
    }

    @PutMapping("/edit/{id}")
    public Discipline update(@PathVariable @NotNull @Positive Long id, @RequestBody Discipline discipline){
        return disciplineService.update(id, discipline);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id){
        disciplineService.delete(id);
    }
}
